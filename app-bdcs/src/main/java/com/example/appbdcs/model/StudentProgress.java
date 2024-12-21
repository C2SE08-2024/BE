package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class StudentProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer progressId;
    private Boolean progressStatus;
    private Date lastAccessed;
    private Integer completedLessons;
    private Integer totalLesson;
    private Integer progressPercentage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    // Constructor với tham số là đối tượng Student và Course
    public StudentProgress(Student student, Course course) {
        this.student = student;
        this.course = course;
        // Khởi tạo các giá trị mặc định cho các thuộc tính khác
        this.progressPercentage = 0;
        this.progressStatus = false;
    }



}
