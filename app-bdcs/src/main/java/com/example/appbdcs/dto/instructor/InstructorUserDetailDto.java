package com.example.appbdcs.dto.instructor;

import com.example.appbdcs.dto.course.CourseDetail;
import com.example.appbdcs.utils.ConvertToInteger;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorUserDetailDto {
    Integer instructorId;
    String instructorCode;
    String instructorName;
    String instructorPhone;
    Boolean instructorGender;
    Date dateOfBirth;
    String idCard;
    String instructorAddress;
    String instructorImg;
    String instructorTypeName;
    String username;
    String accountEmail;
    List<CourseDetail> recommendedCourses;

    public static InstructorUserDetailDto TupleToInstructorDto(List<Tuple> tuples) {
        if (tuples != null && !tuples.isEmpty()) {
            Tuple firstTuple = tuples.get(0);

            InstructorUserDetailDto instructorUserDetailDto = new InstructorUserDetailDto(
                    ConvertToInteger.convertToInteger(firstTuple.get("instructor_id")),
                    firstTuple.get("instructor_code", String.class),
                    firstTuple.get("instructor_name", String.class),
                    firstTuple.get("instructor_phone", String.class),
                    firstTuple.get("instructor_gender", Boolean.class),
                    firstTuple.get("date_of_birth", Date.class),
                    firstTuple.get("id_card", String.class),
                    firstTuple.get("instructor_address", String.class),
                    firstTuple.get("instructor_img", String.class),
                    firstTuple.get("instructor_type_name", String.class),
                    firstTuple.get("user_name", String.class),
                    firstTuple.get("email", String.class),
                    new ArrayList<>()
            );

//            List<CourseDetail> recommendedCourses = tuples.stream()
//                    .map(tuple -> new CourseDetail(
//                            ConvertToInteger.convertToInteger(tuple.get("user_data_id")),
//                            tuple.get("activity_level", String.class),
//                            ConvertToInteger.convertToInteger(tuple.get("age")),
//                            tuple.get("bmi", Double.class),
//                            tuple.get("gender", String.class),
//                            tuple.get("training_goals", String.class),
//                            tuple.get("training_history", String.class),
//                            tuple.get("recommended_status", Boolean.class),
//                            ConvertToInteger.convertToInteger(tuple.get("course_id")),
//                            tuple.get("course_name", String.class),
//                            tuple.get("description", String.class),
//                            tuple.get("duration", String.class),
//                            tuple.get("image", String.class),
//                            tuple.get("status", Boolean.class),
//                            tuple.get("course_type_name", String.class)
//                    ))
//                    .collect(Collectors.toList());
//
//            InstructorUserDetailDto.setRecommendedCourses(recommendedCourses);
//
//            return instructorUserDetailDto;
//        }
//

        }
        return null;
    }
}