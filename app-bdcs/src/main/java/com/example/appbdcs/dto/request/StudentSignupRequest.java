package com.example.appbdcs.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSignupRequest {
    private String studentCode;
    private String name;
    private String username;
    private Boolean gender;
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String idCard;
    private String email;
    private String password;
}
