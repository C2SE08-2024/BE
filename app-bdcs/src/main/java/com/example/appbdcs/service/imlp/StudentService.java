package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.dto.student.StudentUserDetailDto;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.model.StudentCv;
import com.example.appbdcs.repository.IStudentCvRepository;
import com.example.appbdcs.repository.IStudentRepository;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IStudentCvRepository studentCvRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student); // JPA tự xử lý insert hoặc update
    }

    @Override
    public Student studentLimit() {
        return studentRepository.limitStudent(); // Truy vấn lấy sinh viên cuối cùng
    }

//    @Override
//    public List<Student> findStudentsByCourse(Integer courseId) {
//        return studentRepository.findStudentsByCourse(courseId); // Lấy danh sách sinh viên theo khóa học
//    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAllStudents(); // Lấy tất cả sinh viên
    }

    @Override
    public List<Student> findAllWithPagination(int size, int offset) {
        return studentRepository.findAllWithPagination(size, offset); // Lấy tất cả sinh viên với phân trang
    }

    @Override
    public Student findStudentByCode(String studentCode) {
        return studentRepository.findStudentByCode(studentCode); // Lấy sinh viên theo mã sinh viên
    }

    @Override
    public List<Student> findStudentsByTest(Integer testId) {
        return studentRepository.findStudentsByTest(testId); // Lấy danh sách sinh viên tham gia bài kiểm tra
    }

    @Override
    public List<Student> getStudentsInCourse(Integer courseId) {
        return studentRepository.findStudentsInCourse(courseId); // Lấy sinh viên trong khóa học
    }

    @Override
    public StudentDTO findById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(this::convertToDTO).orElse(null); // Chuyển đổi sinh viên thành DTO hoặc null
    }

    public StudentDTO convertToDTO(Student student) {
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
    public boolean existsById(Integer studentId) {
        return studentRepository.existsById(studentId);
    }


    @Override
    public Student findStudentByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }

    @Override
    public StudentUserDetailDto findUserDetailByUsername(String username) {
        Tuple tuple = studentRepository.findUserDetailByUsername(username).orElse(null);

        if (tuple != null) {
            return StudentUserDetailDto.TupleToStudentDto(tuple);
        }

        return null;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        studentRepository.save(student); // Lưu sinh viên vào cơ sở dữ liệu
        return convertToDTO(student); // Trả về DTO sau khi tạo
    }

    @Override
    public StudentDTO updateStudent(Integer studentId, StudentDTO studentDTO) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            // Cập nhật thông tin sinh viên
            student.setStudentCode(studentDTO.getStudentCode());
            student.setStudentName(studentDTO.getStudentName());
            student.setStudentEmail(studentDTO.getStudentEmail());
            student.setStudentPhone(studentDTO.getStudentPhone());
            student.setStudentGender(studentDTO.getStudentGender());
            student.setStudentAddress(studentDTO.getStudentAddress());
            student.setStudentImg(studentDTO.getStudentImg());
            studentRepository.save(student); // Lưu thông tin đã cập nhật
            return convertToDTO(student); // Trả về DTO của sinh viên đã cập nhật
        }
        return null; // Trả về null nếu không tìm thấy sinh viên
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentRepository.deleteStudent(studentId); // Xóa sinh viên theo ID
    }

    // Chuyển đổi StudentDTO thành Student entity
    private Student convertToEntity(StudentDTO studentDTO) {
        return new Student(
                studentDTO.getStudentCode(),
                studentDTO.getStudentName(),
                studentDTO.getStudentEmail(),
                studentDTO.getStudentPhone(),
                studentDTO.getStudentGender(),
                null, // Giả sử bạn không truyền vào DateOfBirth trong DTO
                null, // Giả sử bạn không truyền vào ID card trong DTO
                studentDTO.getStudentAddress(),
                true, // Giả sử mặc định "isEnable" là true khi tạo
                null // Giả sử bạn không truyền vào "major" trong DTO
        );
    }

    // Lấy tất cả CV của một Student
    public List<StudentCv> getAllCvsByStudent(Integer studentId) {
        return studentCvRepository.findAllByStudentId(studentId);
    }
}

