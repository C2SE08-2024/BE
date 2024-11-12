package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;
    private String lessonName;
    private String lessonContent;
    private Integer lessonOrder;
    private Integer lessonDuration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;
}
