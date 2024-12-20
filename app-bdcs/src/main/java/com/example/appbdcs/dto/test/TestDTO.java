package com.example.appbdcs.dto.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    private Integer testId;
    private String testName;
    private Integer progressThreshold;
    private Integer passScore;
}
