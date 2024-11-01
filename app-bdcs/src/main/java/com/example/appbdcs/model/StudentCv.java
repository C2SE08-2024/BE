package com.example.appbdcs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;

@Entity
@Getter
@Setter
public class StudentCv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentCvId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    private String cvContent;
    private DateTimeException uploadDate;
    private String filePath;
    private String cvType;
}
