package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "student_cv")
public class StudentCv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentCvId;
    private String studentCvContent;
    private LocalDate uploadDate;
    private String filePath;
    private String studentCvType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

}
