package com.example.appbdcs.dto.course;

import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetail {
    Integer userDataId;
    String activityLevel;
    Integer age;
    Double bmi;
    String gender;
    String trainingGoals;
    String trainingHistory;
    Boolean recommendedStatus;
    Integer courseId;
    String courseName;
    String description;
    String duration;
    String image;
    Boolean status;
    String courseTypeName;
}
