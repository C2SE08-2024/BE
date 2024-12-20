package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "cv")
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cvId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "student_cv_id", nullable = false)
    private StudentCv studentCv;

    private String status; // Ví dụ: "Draft", "Published", "Pending", v.v.
    private String createdBy; // Người tạo CV
    private LocalDate createdDate; // Ngày tạo CV
}
