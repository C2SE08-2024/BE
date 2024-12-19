package com.example.appbdcs.controller;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Request;
import com.example.appbdcs.dto.student.StudentUserDetailDto;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.model.StudentTestResult;
import com.example.appbdcs.service.IStudentService;
import com.example.appbdcs.service.IStudentTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStudentTestResultService studentTestResultService;

    // Lấy tất cả học sinh
    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    // Lấy kết quả bài kiểm tra của học sinh theo ID học sinh
    @GetMapping("/{studentId}/test-results")
    public ResponseEntity<List<StudentTestResult>> getStudentTestResults(@PathVariable Integer studentId) {
        List<StudentTestResult> results = studentTestResultService.findResultsByStudentId(studentId);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

    // Lấy kết quả bài kiểm tra cho học sinh cụ thể và bài kiểm tra cụ thể
    @GetMapping("/{studentId}/test/{testId}/result")
    public ResponseEntity<StudentTestResult> getResultByStudentAndTest(@PathVariable Integer studentId,
                                                                       @PathVariable Integer testId) {
        StudentTestResult result = studentTestResultService.findResultByStudentAndTest(studentId, testId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    // Lấy tất cả sinh viên với phân trang
    @GetMapping("/page")
    public ResponseEntity<List<StudentDTO>> getAllStudentsWithPagination(
            @RequestParam int size, @RequestParam int page) {
        int offset = (page - 1) * size; // Tính toán offset
        List<Student> students = studentService.findAllWithPagination(size, offset);
        List<StudentDTO> studentDTOs = students.stream()
                .map(studentService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    // Tìm kiếm sinh viên theo mã
    @GetMapping("/{studentCode}")
    public ResponseEntity<StudentDTO> getStudentByCode(@PathVariable String studentCode) {
        Student student = studentService.findStudentByCode(studentCode);
        if (student != null) {
            return new ResponseEntity<>(studentService.convertToDTO(student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Lấy thông tin sinh viên theo ID
    @GetMapping("/id/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer studentId) {
        StudentDTO studentDTO = studentService.findById(studentId);
        if (studentDTO != null) {
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới sinh viên
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    // Cập nhật thông tin sinh viên
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer studentId,
                                                    @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentDTO);
        if (updatedStudent != null) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa sinh viên
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Lấy danh sách sinh viên tham gia khóa học
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentDTO>> getStudentsInCourse(@PathVariable Integer courseId) {
        List<Student> students = studentService.getStudentsInCourse(courseId);
        List<StudentDTO> studentDTOs = students.stream()
                .map(studentService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    // Lấy danh sách sinh viên tham gia bài kiểm tra
    @GetMapping("/test/{testId}")
    public ResponseEntity<List<StudentDTO>> getStudentsByTest(@PathVariable Integer testId) {
        List<Student> students = studentService.findStudentsByTest(testId);
        List<StudentDTO> studentDTOs = students.stream()
                .map(studentService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
    }

    // Lấy sinh viên cuối cùng (theo student_code)
    @GetMapping("/limit")
    public ResponseEntity<StudentDTO> getLastStudent() {
        Student student = studentService.studentLimit();
        if (student != null) {
            return new ResponseEntity<>(studentService.convertToDTO(student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<StudentUserDetailDto> getDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        StudentUserDetailDto studentUserDetailDto = studentService.findUserDetailByUsername(username);

        if (studentUserDetailDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentUserDetailDto, HttpStatus.OK);
    }
}
