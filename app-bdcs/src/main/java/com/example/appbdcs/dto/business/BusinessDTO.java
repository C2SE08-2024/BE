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

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }



    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public void setBusinessImg(String businessImg) {
        this.businessImg = businessImg;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setSize(String size) {
        this.size = size;
    }
}