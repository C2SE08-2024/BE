package com.example.appbdcs.dto.instructor;

import com.example.appbdcs.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class InstructorDTO {

    private Integer instructorId;

    private String instructorCode;

    private String instructorName;

    private String instructorEmail;

    private String instructorPhone;

    private Boolean instructorGender;

    private Date dateOfBirth;

    private String idCard;

    private String instructorAddress;

    private String instructorImg;

    private Boolean isEnable;

    private Account account;

}
