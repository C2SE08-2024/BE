package com.example.appbdcs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;
}
