package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Student;
import com.example.appbdcs.repository.IStudentRepository;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Student findStudentByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }
}
