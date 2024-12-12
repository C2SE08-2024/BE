package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.repository.IStudentRepository;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student studentLimit() {
        return studentRepository.limitStudent();
    }

    @Override
    public List<Student> findStudentsByCourse(Integer courseId) {
        return studentRepository.findStudentsByCourse(courseId);
    }


    public List<Student> findAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public Student findStudentByCode(String studentCode) {
        return studentRepository.findStudentByCode(studentCode);
    }

    @Override
    public List<Student> findStudentsByTest(Integer testId) {
        return studentRepository.findStudentsByTest(testId);
    }

    public List<Student> getStudentsInCourse(Integer courseId) {
        return studentRepository.findStudentsInCourse(courseId);
    }

    // Tìm sinh viên theo ID và chuyển thành DTO
    public StudentDTO findById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(this::convertToDTO).orElse(null); // Trả về StudentDTO hoặc null
    }
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getStudentId(),
                student.getStudentCode(),
                student.getStudentName(),
                student.getStudentEmail(),
                student.getStudentPhone(),
                student.getStudentGender(),
                student.getStudentAddress(),
                student.getStudentImg()
        );
    }


    @Override
    public Student findStudentByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }
}
