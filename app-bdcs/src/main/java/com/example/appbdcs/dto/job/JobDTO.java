package com.example.appbdcs.dto.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class JobDTO {

    private Integer jobId;
    private String jobTitle;
    private String jobDescription;
    private String location;
    private String industry;
    private String requirement;
    private String status;
    private String salaryRange;
    private String jobType;
    private java.sql.Date posterDate;
    private Date expiryDate;
    private Integer businessId;  // Thêm businessId để liên kết với Business entity

}
