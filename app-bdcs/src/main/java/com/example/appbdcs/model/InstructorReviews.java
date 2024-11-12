package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class InstructorReviews {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer reviewId;
    private String reviewContent;
    private Integer rating;
    private DateTimeException reviewDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
}
