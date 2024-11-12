package com.example.appbdcs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.Year;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instructorId;
    private String instructorCode;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private Boolean instructorGender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String idCard;
    private String instructorAddress;
    private String instructorImg;
    private Boolean isEnable;
    private String specialization;
    private Year experienceYear;
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
}

