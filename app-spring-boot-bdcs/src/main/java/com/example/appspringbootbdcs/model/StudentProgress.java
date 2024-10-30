package com.example.appspringbootbdcs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;

@Entity
@Getter
@Setter
public class StudentProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer progressId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course courseId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student studentId;

    private String progressStatus;
    private DateTimeException lastAccessed;
    private Integer completedLesson;
    private Integer totalLesson;
}
