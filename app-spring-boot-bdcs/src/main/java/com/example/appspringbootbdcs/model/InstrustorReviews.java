package com.example.appspringbootbdcs.model;

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
    private Instructor instructorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student studentId;

    private String reviewContent;
    private Integer rating;
    private DateTimeException reviewDate;
}
