package com.example.appbdcs.dto.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTestDTO {
    private Integer testId;
    private String testName;
    private Integer passScore;
    private Integer progressThreshold;
    private Integer courseId;
    private List<CreateTestQuestionDTO> questions;
}
