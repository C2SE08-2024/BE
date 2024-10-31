package com.example.appspringbootbdcs.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;

@Entity
@Setter
@Getter
public class Evaluate {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer evaluateId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private  Course course;

    private  String evaluateName;
    private String evaluateContent;
    private DateTimeException evaluateDate;
}
