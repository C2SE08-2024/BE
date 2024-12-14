package com.example.appbdcs.dto.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserDetailDto {
    Integer businessId;
    String businessCode;
    String businessName;
    String businessPhone;
    String businessAddress;
    String businessImg;
    String description;
    String industry;
    Integer foundedYear;
    String website;
    String size;
    String username;
    String accountEmail;

    public static BusinessUserDetailDto TupleToBusinessDto(Tuple tuple) {
        return new BusinessUserDetailDto(
                tuple.get("business_id", Integer.class),
                tuple.get("business_code", String.class),
                tuple.get("business_name", String.class),
                tuple.get("business_phone", String.class),
                tuple.get("business_address", String.class),
                tuple.get("business_img", String.class),
                tuple.get("description", String.class),
                tuple.get("industry", String.class),
                tuple.get("founded_year", Integer.class),
                tuple.get("website", String.class),
                tuple.get("size", String.class),
                tuple.get("username", String.class),
                tuple.get("email", String.class)
        );
    }
}
