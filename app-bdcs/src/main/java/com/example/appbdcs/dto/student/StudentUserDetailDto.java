package com.example.appbdcs.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUserDetailDto {
    Integer studentId;
    String studentCode;
    String studentName;
    String studentPhone;
    Boolean studentGender;
    Date dateOfBirth;
    String idCard;
    String studentAddress;
    String studentImg;
    String major;
    Integer graduationYear;
    String username;
    String accountEmail;

    public static StudentUserDetailDto TupleToStudentDto(Tuple tuple) {
        return new StudentUserDetailDto(
                tuple.get("student_id", Integer.class),
                tuple.get("student_code", String.class),
                tuple.get("student_name", String.class),
                tuple.get("student_phone", String.class),
                tuple.get("student_gender", Boolean.class),
                tuple.get("date_of_birth", Date.class),
                tuple.get("id_card", String.class),
                tuple.get("student_address", String.class),
                tuple.get("student_img", String.class),
                tuple.get("major", String.class),
                tuple.get("graduation_year", Integer.class),
                tuple.get("username", String.class),
                tuple.get("email", String.class)
        );
    }
}
