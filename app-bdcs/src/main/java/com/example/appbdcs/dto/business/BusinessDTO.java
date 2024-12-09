package com.example.appbdcs.dto.business;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    public BusinessDTO(Integer businessId, String businessCode, String businessName, String businessEmail, String businessPhone, String businessAddress, String businessImg, String description, Boolean isEnable, String industry, Integer foundedYear, String website, String size) {
        this.businessId = businessId;
        this.businessCode = businessCode;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.businessPhone = businessPhone;
        this.businessAddress = businessAddress;
        this.businessImg = businessImg;
        this.description = description;
        this.isEnable = isEnable;
        this.industry = industry;
        this.foundedYear = foundedYear;
        this.website = website;
        this.size = size;
    }
}