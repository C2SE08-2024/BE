package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.student.StudentUserDetailDto;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.repository.IStudentRepository;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;

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

    @Override
    public StudentUserDetailDto findUserDetailByUsername(String username) {
        Tuple tuple = studentRepository.findUserDetailByUsername(username).orElse(null);

        if (tuple != null) {
            return StudentUserDetailDto.TupleToStudentDto(tuple);
        }

        return null;
    }
}
