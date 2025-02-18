package com.example.appbdcs.controller;

import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.model.Enrollments;
import com.example.appbdcs.model.Payment;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IInstructorService instructorService;

    @Autowired
    private IBusinessService businessService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private IPaymentService paymentService;


    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourse() {
        List<Course> courseList = courseService.findAll();
        if (courseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.findCourseById(id), HttpStatus.OK);
    }

    @GetMapping("/paid")
    public ResponseEntity<List<Course>> getPaidCourses() {
        List<Course> paidCourses = courseService.getPaidCourses();
        return new ResponseEntity<>(paidCourses, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<Course>> getFreeCourses() {
        List<Course> freeCourses = courseService.getFreeCourses();
        return new ResponseEntity<>(freeCourses, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (SecurityException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Course> updateCourse(@PathVariable("courseId") Integer courseId,
                                               @RequestBody CourseDTO updatedCourseDTO) {
        try {
            Course existingCourse = courseService.updateCourse(courseId, updatedCourseDTO);
            return new ResponseEntity<>(existingCourse, HttpStatus.OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Integer courseId) {
        try {
            courseService.deleteCourse(courseId);
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/{courseId}/students")
    public List<Student> getStudentsByCourseId(@PathVariable Integer courseId) {
        return courseService.getStudentsByCourseId(courseId);
    }

    @PostMapping("/register/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> registerCourse(@PathVariable("courseId") Integer courseId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Student student = studentService.findStudentByUsername(username);
            if (student == null) {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }

            Course course = courseService.findCourseById(courseId);
            if (course == null || !course.getStatus()) {
                return new ResponseEntity<>("Course not available or inactive", HttpStatus.NOT_FOUND);
            }

            boolean isAlreadyEnrolled = enrollmentService.isStudentEnrolled(courseId, student.getStudentId());
            if (isAlreadyEnrolled) {
                return new ResponseEntity<>("Student is already registered for this course", HttpStatus.CONFLICT);
            }

            Enrollments enrollment = new Enrollments();
            enrollment.setCourse(course);
            enrollment.setStudent(student);
            enrollment.setEnrollmentDay(new Date(System.currentTimeMillis()));
            enrollment.setStatus(true);

            if (course.getCoursePrice() != 0) {
                Payment payment = paymentService.findPaymentByCartAndCourse(student.getCart().getCartId(), courseId);
                if (payment == null || !payment.isPaid()) {
                    return new ResponseEntity<>("Payment required for this course", HttpStatus.PAYMENT_REQUIRED);
                }
            }
            enrollmentService.saveEnrollment(enrollment);

            course.getStudents().add(student);
            student.getCourses().add(course);

            studentService.save(student);
            courseService.save(course);
            return new ResponseEntity<>("Student successfully registered for the course", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during registration: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}