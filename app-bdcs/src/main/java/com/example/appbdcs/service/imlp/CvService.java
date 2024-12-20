package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Cv;
import com.example.appbdcs.model.StudentCv;
import com.example.appbdcs.repository.ICvRepository;
import com.example.appbdcs.repository.IStudentCvRepository;
import com.example.appbdcs.service.ICvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class CvService implements ICvService {
    @Autowired
    private ICvRepository cvRepository;

    @Autowired
    private IStudentCvRepository studentCvRepository;

    public Cv createCvForStudent(StudentDTO student, String cvContent, String filePath, String cvType) {
        // Tạo StudentCv mới
        StudentCv studentCv = new StudentCv();
        studentCv.setStudent(student);
        studentCv.setStudentCvContent(cvContent);
        studentCv.setUploadDate(LocalDate.now());
        studentCv.setFilePath(filePath);
        studentCv.setStudentCvType(cvType);

        // Lưu StudentCv
        studentCv = studentCvRepository.save(studentCv);

        // Tạo CV mới
        Cv cv = new Cv();
        cv.setStudent(student);
        cv.setStudentCv(studentCv);
        cv.setStatus("Draft");
        cv.setCreatedBy("System");
        cv.setCreatedDate(LocalDate.now());

        // Lưu CV
        return (Cv) cvRepository.save(cv);
    }
}
