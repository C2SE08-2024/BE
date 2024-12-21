package com.example.appbdcs.dto.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CvResponse {
    private Integer cvId;
    private String status;
    private String createdBy;
    private String createdDate;
    private Integer studentId;
}
