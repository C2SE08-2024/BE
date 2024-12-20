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

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public Boolean getStudentGender() {
        return studentGender;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public String getStudentImg() {
        return studentImg;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public void setStudentGender(Boolean studentGender) {
        this.studentGender = studentGender;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public void setStudentImg(String studentImg) {
        this.studentImg = studentImg;
    }
}

