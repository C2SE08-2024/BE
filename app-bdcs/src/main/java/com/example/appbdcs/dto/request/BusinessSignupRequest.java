package com.example.appbdcs.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.Year;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessSignupRequest {
    private String businessCode;
    private String name;
    private String username;
    private String address;
    private String phone;
    private String email;
    private String password;

}
