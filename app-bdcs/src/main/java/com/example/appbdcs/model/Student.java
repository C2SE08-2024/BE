package com.example.appbdcs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.Year;
import java.util.LinkedHashSet;
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
    private Year graduationYear;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new LinkedHashSet<>();

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
