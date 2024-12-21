package com.example.appbdcs.model;

import com.example.appbdcs.dto.business.BusinessDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;
    private Date requestDate;
    private Boolean isAccepted;
    private Boolean canView = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

}
