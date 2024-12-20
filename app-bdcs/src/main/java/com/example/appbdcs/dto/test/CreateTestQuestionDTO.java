package com.example.appbdcs.dto.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTestQuestionDTO {
    private Integer questionId;
    private String questionContent;
    private String correctAnswer;
}
