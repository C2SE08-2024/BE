package com.example.appspringbootbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exerciseId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_id")
    private Lesson lessonId;

    private String exerciseName;
    private String exerciseContent;
    private Integer exerciseOrder;
    private Integer maxScore;
    private Boolean submit;
    private LocalDateTime submitDeadline;


    public Exercise(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }
}
