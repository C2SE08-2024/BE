package com.example.appbdcs.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DateTimeException;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private String industry;
    private String requirement;
    private String status;
    private String salaryRange;
    private String jobType;
    private DateTimeException posterDate;
    private DateTimeException expiryDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private Business business;
}
