package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;
    private Integer coursePrice;
    @Column(name = "image", length = 2000)
    private String image;
    private Boolean status = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new LinkedHashSet<>();
}
