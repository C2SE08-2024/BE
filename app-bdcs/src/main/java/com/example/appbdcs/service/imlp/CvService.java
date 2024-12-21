package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.*;
import com.example.appbdcs.repository.ICvRepository;
import com.example.appbdcs.repository.IStudentRepository;
import com.example.appbdcs.service.ICvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CvService implements ICvService {
    @Autowired
    private ICvRepository cvRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Cv createCv(
            Integer studentId,
            String name,
            String position,
            String summary,
            List<String> skills,
            List<Education> education,
            List<Experience> experiences
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        StudentCv studentCv = new StudentCv();
        studentCv.setStudent(student);
        studentCv.setStudentCvContent(summary);
        studentCv.setStudentCvType("Default");
        studentCv.setUploadDate(LocalDate.now());

        Cv cv = new Cv();
        cv.setStudent(student);
        cv.setStudentCv(studentCv);
        cv.setCreatedBy("System");
        cv.setCreatedDate(Date.valueOf(LocalDate.now()));
        cv.setStatus("Draft");

        for (String skillName : skills) {
            Skill skill = new Skill();
            skill.setSkillName(skillName);
            cv.addSkill(skill);
        }

        for (Education edu : education) {
            cv.addEducation(edu);
        }

        for (Experience exp : experiences) {
            cv.addExperience(exp);
        }

        return cvRepository.save(cv);
    }
}