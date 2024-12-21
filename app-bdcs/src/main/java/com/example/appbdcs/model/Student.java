package com.example.appbdcs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentCode;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private Boolean studentGender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String idCard;
    private String studentAddress;
    private String studentImg;
    private Boolean isEnable;
    private String major;
    private Integer graduationYear;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentCv> studentCvs = new LinkedHashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonBackReference
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cv> cvs;

    public Student(Integer studentId) {
        this.studentId = studentId;
    }

    public Student(String studentCode, String studentName, String studentEmail, String studentPhone,
                   Boolean studentGender, Date dateOfBirth, String idCard, String studentAddress,
                   Boolean isEnable, Account account) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentGender = studentGender;
        this.dateOfBirth = dateOfBirth;
        this.idCard = idCard;
        this.studentAddress = studentAddress;
        this.isEnable = isEnable;
        this.account = account;
    }
}
