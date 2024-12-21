package com.example.appbdcs.dto.student;

import com.example.appbdcs.model.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO extends Student {
    private Integer studentId;
    private String studentCode;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private Boolean studentGender;
    private String studentAddress;
    private String studentImg;

    public StudentDTO(Integer studentId, String studentCode, String studentName, String studentEmail, String studentPhone, Boolean studentGender, String studentAddress, String studentImg) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentGender = studentGender;
        this.studentImg = studentImg;
        this.studentAddress = studentAddress;
    }
}

