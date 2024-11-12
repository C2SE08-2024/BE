package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.student.StudentInfo;
import com.example.appbdcs.model.Cart;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.repository.ICartRepository;
import com.example.appbdcs.repository.IStudentRepository;
import com.example.appbdcs.service.IStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;
    private final ICartRepository cartRepository;
    @Autowired
    public StudentService(IStudentRepository studentRepository,ICartRepository cartRepository){
        this.studentRepository = studentRepository;
        this.cartRepository = cartRepository;

    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }


    @Override
    public Page<Student> findAllStudents(Pageable pageable) {
        return this.studentRepository.findAllStudent(pageable);
    }

    @Override
    public Page<Student> searchStudents(String type, String name, String address, String phone, Pageable pageable) {
        return this.studentRepository.searchStudent(type, name, address, phone, pageable);
    }

    @Override
    public Student studentLimit() {
        return studentRepository.limitStudent();
    }

    @Override
    public void saveStudent(StudentInfo studentInfo) {
        cartRepository.insertCart(studentInfo.getStudentName(), studentInfo.getStudentAddress(),
                studentInfo.getStudentPhone(),studentInfo.getStudentEmail());
        Cart cart = cartRepository.findLastCart().orElse(null);
        if (cart != null) {
            studentRepository.insertStudent(studentInfo.getStudentCode(),studentInfo.getStudentName(),studentInfo.getStudentEmail(),
                    studentInfo.getStudentPhone(), studentInfo.getStudentGender(),studentInfo.getDateOfBirth(),studentInfo.getIdCard(),
                    studentInfo.getStudentAddress(),studentInfo.getStudentImg(),true,cart.getCartId());
        }
    }

    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public void updateStudent(StudentInfo studentInfo, Integer id) {

    }

    @Override
    public void deleteById(Integer id) {

    }

//    @Override
//    public StudentUserDetailDto findUserDetailByUsername(String username) {
//        return null;
//    }

    @Override
    public Student findByCart(Cart cart) {
        return null;
    }
}
