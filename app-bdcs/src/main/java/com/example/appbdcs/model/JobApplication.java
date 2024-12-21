package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobApplicationId;
    private LocalDate jobApplicationDate;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_cv_id")
    private StudentCv studentCv;
}
