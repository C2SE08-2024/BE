package com.example.appbdcs.dto.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTestDTO {
    private Integer testId;
    private List<String> answers;
}
