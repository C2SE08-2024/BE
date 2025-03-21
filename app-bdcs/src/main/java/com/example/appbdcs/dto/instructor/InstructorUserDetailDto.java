package com.example.appbdcs.dto.instructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorUserDetailDto {
    Integer instructorId;
    String instructorCode;
    String instructorName;
    String instructorEmail;
    String instructorPhone;
    Boolean instructorGender;
    Date dateOfBirth;
    String idCard;
    String instructorAddress;
    String instructorImg;
    String specialization;
    Integer experienceYear;
    String bio;
    String username;
    String accountEmail;

    public static InstructorUserDetailDto TupleToInstructorDto(Tuple tuple) {
        return new InstructorUserDetailDto(
                tuple.get("instructor_id", Integer.class),
                tuple.get("instructor_code", String.class),
                tuple.get("instructor_name", String.class),
                tuple.get("instructor_email", String.class),
                tuple.get("instructor_phone", String.class),
                tuple.get("instructor_gender", Boolean.class),
                tuple.get("date_of_birth", Date.class),
                tuple.get("id_card", String.class),
                tuple.get("instructor_address", String.class),
                tuple.get("instructor_img", String.class),
                tuple.get("specialization", String.class),
                tuple.get("experience_year", Integer.class),
                tuple.get("bio", String.class),
                tuple.get("username", String.class),
                tuple.get("email", String.class)
        );
    }
}
