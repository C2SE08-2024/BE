package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "cv")
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cvId;

    private String status;
    private String createdBy;
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_cv_id", nullable = false)
    private StudentCv studentCv;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> education = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    public void addSkill(Skill skill) {
        if (this.skills == null) {
            this.skills = new ArrayList<>();
        }
        this.skills.add(skill);
    }

    public void addEducation(Education education) {
        if (this.education == null) {
            this.education = new ArrayList<>();
        }
        this.education.add(education);
    }

    public void addExperience(Experience experience) {
        if (this.experiences == null) {
            this.experiences = new ArrayList<>();
        }
        this.experiences.add(experience);
    }
}