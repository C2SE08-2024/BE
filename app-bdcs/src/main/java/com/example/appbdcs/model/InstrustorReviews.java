package com.example.appbdcs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;

@Entity
@Getter
@Setter
public class InstrustorReviews {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    private String reviewContent;
    private Integer rating;
    private DateTimeException reviewDate;
}
