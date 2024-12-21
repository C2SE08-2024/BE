package com.example.appbdcs.dto.job;

import com.example.appbdcs.model.Education;
import com.example.appbdcs.model.Experience;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCvRequest {
    private Integer studentId;
    private String name;
    private String position;
    private String summary;
    private List<String> skills;
    private List<Education> education;
    private List<Experience> experiences;
}
