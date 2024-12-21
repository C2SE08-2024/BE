package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educationId;
    private String school;
    private String degree;
    private String year;
}
