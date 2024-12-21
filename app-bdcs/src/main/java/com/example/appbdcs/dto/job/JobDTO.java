package com.example.appbdcs.dto.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date posterDate;
    private Date expiryDate;
    private Integer businessId;

}
