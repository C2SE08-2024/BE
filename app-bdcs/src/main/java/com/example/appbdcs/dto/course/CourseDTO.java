package com.example.appbdcs.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private Integer coursePrice;
    private String image;
    private Boolean status;
    private Integer instructorId;
    private String instructorName;

    public CourseDTO(Integer courseId, String courseName, Integer coursePrice, String image, Boolean status, Integer instructorId, String instructorName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.image = image;
        this.status = status;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
    }

}
