package com.example.appbdcs.dto.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDTO {
    private Integer businessId;
    private String businessCode;
    private String businessName;
    private String businessEmail;
    private String businessPhone;
    private String businessAddress;
    private String businessImg;
    private String description;
    private Boolean isEnable;
    private String industry;
    private Integer foundedYear;
    private String website;
    private String size;
}
