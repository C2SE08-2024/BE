package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;
    private String lessonName;
    @Column(name = "lessonContent", length = 2000)
    private String lessonContent;
    @Column(name = "video", length = 2000)
    private String video;
    private String lessonDuration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private Test test;

    @ElementCollection
    @CollectionTable(name = "lesson_completed_students", joinColumns = @JoinColumn(name = "lesson_id"))
    @Column(name = "student_id")
    private Set<Integer> completedByStudentIds = new HashSet<>();

    public boolean isCompletedByStudent(Integer studentId) {
        return this.completedByStudentIds.contains(studentId);
    }
}
