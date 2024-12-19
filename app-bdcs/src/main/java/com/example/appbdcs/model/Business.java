package com.example.appbdcs.model;

import com.example.appbdcs.dto.business.BusinessDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Business extends BusinessDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    public Business(String businessCode, String businessName, String businessEmail, String businessPhone,
                    String businessAddress, Boolean isEnable, Account account) {
        this.businessCode = businessCode;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.businessPhone = businessPhone;
        this.businessAddress = businessAddress;
        this.isEnable = isEnable;
        this.account = account;
    }
}
