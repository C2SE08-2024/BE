package com.example.appbdcs.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;

@Entity
@Getter
@Setter
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer businessId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    private String businessCode;
    private String businessName;
    private String businessEmail;
    private String businessPhone;
    private String businessAddress;
    private String businessImg;
    private String description;
    private Boolean isEnable;
    private String industry;
    private Year foundedYear;
    private  String website;
    private String size;
}
