package com.example.appspringbootbdcs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.sql.Date;
import java.time.Year;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instructorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account accountId;

    private String instructorCode;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private Boolean instructorGender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String idCard;
    private String instructorAddress;
    private String instructorImg;
    private Boolean isEnable;
    private String specialization;
    private Year experienceYear;
    private String bio;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "customer_type_id")
    // private CustomerType customerType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    public Instructor(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Instructor(String instructorCode,Integer accountId, String instructorName, String instructorEmail, String instructorPhone, Boolean instructorGender, Date dateOfBirth, String idCard, String instructorAddress, Boolean isEnable, Account account,String specialization,Year experienceYear, String bio  ) {
        this.instructorCode = instructorCode;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhone = instructorPhone;
        this.instructorGender = instructorGender;
        this.dateOfBirth = dateOfBirth;
        this.idCard = idCard;
        this.instructorAddress = instructorAddress;
        this.isEnable = isEnable;
        this.account = account;
        this.specialization = specialization;
        this.experienceYear = experienceYear;
        this. bio = bio;

    }
}

