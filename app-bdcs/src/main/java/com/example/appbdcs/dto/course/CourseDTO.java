package com.example.appbdcs.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private Integer coursePrice;
    private String image;
    private Boolean status;
    private Integer instructorId;
    private Integer businessId;
}
