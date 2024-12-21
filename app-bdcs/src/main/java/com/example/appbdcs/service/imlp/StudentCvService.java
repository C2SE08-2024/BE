package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.StudentCv;
import com.example.appbdcs.repository.IStudentCvRepository;
import com.example.appbdcs.service.IStudentCvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCvService implements IStudentCvService {

    @Autowired
    private IStudentCvRepository studentCvRepository;

    // Lấy CV theo ID
    public StudentCv getCvById(Integer cvId) {
        return studentCvRepository.findById(cvId)
                .orElseThrow(() -> new RuntimeException("CV not found"));
    }

    // Lấy tất cả CV của một Student
    public List<StudentCv> getAllCvsByStudentId(Integer studentId) {
        return studentCvRepository.findAllByStudentId(studentId);
    }

    // Lấy tất cả CV đã nộp vào một Business
    public List<StudentCv> getAllCvsByBusinessId(Integer businessId) {
        return studentCvRepository.findAllByBusinessId(businessId);
    }

    // Lấy tất cả CV theo loại
    public List<StudentCv> getAllCvsByType(String type) {
        return studentCvRepository.findAllByType(type);
    }
}
