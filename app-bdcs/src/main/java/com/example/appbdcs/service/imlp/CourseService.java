package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.dto.course.CourseWithInstructorDTO;
import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.dto.student.StudentsByCourseDTO;
import com.example.appbdcs.error.NotFoundById;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.repository.ICourseRepository;
import com.example.appbdcs.repository.IInstructorRepository;
import com.example.appbdcs.repository.IStudentRepository;
import com.example.appbdcs.service.ICourseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IInstructorRepository instructorRepository;

    @Autowired
    private IStudentRepository studentRepository;


    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @SneakyThrows
    @Override
    public Course findCourseById(Integer id) {
        Optional<Course> course = courseRepository.findCourseById(id);
        if (course.isPresent()) {
            return course.get();
        }
        throw new NotFoundById("Could not find any courses with code: " + id);
    }

    @Override
    public List<Course> getPaidCourses() {
        return courseRepository.findPaidCourses();
    }

    @Override
    public List<Course> getFreeCourses() {
        return courseRepository.findFreeCourses();
    }

    @Override
    public List<PopularCourseDTO> getMostPopularCourses() {
        List<Object[]> rawResults = courseRepository.findMostPopularCourses();
        List<PopularCourseDTO> popularCourses = new ArrayList<>();

        for (Object[] row : rawResults) {
            PopularCourseDTO popularCourseDTO = new PopularCourseDTO(
                    (Integer) row[0],
                    (String) row[1],
                    (Integer) row[2],
                    (String) row[3],
                    (Boolean) row[4],
                    ((Number) row[5]).longValue()
            );
            popularCourses.add(popularCourseDTO);
        }
        return popularCourses;
    }

    @Override
    public List<Course> searchCoursesByName(String courseName) {
        return courseRepository.findCoursesByName(courseName);
    }

    @Override
    public Optional<CourseWithInstructorDTO> getCourseWithInstructor(Integer courseId) {
        Optional<Course> course = courseRepository.findCourseById(courseId);
        if (course.isPresent()) {
            Course c = course.get();
            CourseWithInstructorDTO dto = new CourseWithInstructorDTO(
                    c.getCourseId(),
                    c.getCourseName(),
                    c.getCoursePrice(),
                    c.getImage(),
                    c.getStatus(),
                    c.getInstructor()
            );
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public List<CourseWithInstructorDTO> getAllCoursesWithInstructor() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> new CourseWithInstructorDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getCoursePrice(),
                course.getImage(),
                course.getStatus(),
                course.getInstructor()
        )).collect(Collectors.toList());
    }

    @Override
    public List<StudentsByCourseDTO> getStudentsByCourse(Integer courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course does not exist"));

        List<Student> students = new ArrayList<>(course.getStudents());

        List<StudentsByCourseDTO> studentDTOList = new ArrayList<>();
        for (Student student : students) {
            studentDTOList.add(new StudentsByCourseDTO(student.getStudentName(), student.getStudentImg()));
        }
        return studentDTOList;
    }

    @Override
    @Transactional
    public Course createCourse(Course course) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails.getAuthorities().stream().anyMatch(
                role -> role.getAuthority().equals("ROLE_INSTRUCTOR") || role.getAuthority().equals("ROLE_ADMIN"))) {

            if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_INSTRUCTOR"))) {
                Instructor instructor = instructorRepository.findByUsername(userDetails.getUsername());
                if (instructor != null) {
                    course.setInstructor(instructor);
                } else {
                    throw new SecurityException("Instructor not found");
                }
            } else {
                course.setInstructor(null);
            }
            return courseRepository.save(course);
        } else {
            throw new SecurityException("You do not have permission to create a course");
        }
    }

    @SneakyThrows
    @Override
    @Transactional
    public Course updateCourse(Integer courseId, CourseDTO updatedCourseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails.getAuthorities().stream().anyMatch(
                role -> role.getAuthority().equals("ROLE_INSTRUCTOR") || role.getAuthority().equals("ROLE_ADMIN"))) {
            Optional<Course> existingCourseOptional = courseRepository.findById(courseId);
            if (existingCourseOptional.isPresent()) {
                Course existingCourse = existingCourseOptional.get();

                if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_INSTRUCTOR"))
                        && !existingCourse.getInstructor().getAccount().getUsername().equals(userDetails.getUsername())) {
                    throw new SecurityException("You can only edit courses you created");
                }

                courseRepository.updateCourse(courseId,
                        updatedCourseDTO.getCourseName(),
                        updatedCourseDTO.getCoursePrice(),
                        updatedCourseDTO.getImage(),
                        true,
                        userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))
                                ? updatedCourseDTO.getInstructorId() : existingCourse.getInstructor().getInstructorId());

                return courseRepository.findById(courseId).orElseThrow(() -> new NotFoundById("Course not found"));
            } else {
                throw new NotFoundById("Could not find any courses with code: " + courseId);
            }
        } else {
            throw new SecurityException("You do not have permission to update a course");
        }
    }

    @SneakyThrows
    @Override
    @Transactional
    public void deleteCourse(Integer courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails.getAuthorities().stream().anyMatch(
                role -> role.getAuthority().equals("ROLE_INSTRUCTOR") || role.getAuthority().equals("ROLE_ADMIN"))) {

            Optional<Course> existingCourseOptional = courseRepository.findById(courseId);

            if (existingCourseOptional.isPresent()) {
                Course existingCourse = existingCourseOptional.get();

                if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_INSTRUCTOR"))
                        && !existingCourse.getInstructor().getAccount().getUsername().equals(userDetails.getUsername())) {
                    throw new SecurityException("You can only delete courses you created");
                }

                courseRepository.deleteCourseById(courseId);
            } else {
                throw new NotFoundById("Could not find any courses with code: " + courseId);
            }
        } else {
            throw new SecurityException("You do not have permission to delete a course");
        }
    }

    @Override
    public List<Student> getStudentsByCourseId(Integer courseId) {
        Course course = courseRepository.findByCourseId(courseId);

        if (course == null) {
            throw new RuntimeException("Course not found");
        }

        return new ArrayList<>(course.getStudents());
    }

    public Course getCourseById(Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new RuntimeException("Course not found with id: " + courseId);
        }
    }

}
