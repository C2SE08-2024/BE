package com.example.appbdcs.dto.business;

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
public class BusinessUserDetailDto {

    Integer businessId;
    String businessCode;
    String businessName;
    String businessPhone;
    String businessAddress;
    String businessImg;
    String businessTypeName;
    String username;
    String accountEmail;
    List<CourseDetail> recommendedCourses;

    public static BusinessUserDetailDto TupleToBusinessDto(List<Tuple> tuples) {
        if (tuples != null && !tuples.isEmpty()) {
            Tuple firstTuple = tuples.get(0);

            BusinessUserDetailDto businessUserDetailDto = new BusinessUserDetailDto(
                    ConvertToInteger.convertToInteger(firstTuple.get("business_id")),
                    firstTuple.get("business_code", String.class),
                    firstTuple.get("business_name", String.class),
                    firstTuple.get("business_phone", String.class),
                    firstTuple.get("business_address", String.class),
                    firstTuple.get("business_img", String.class),
                    firstTuple.get("business_type_name", String.class),
                    firstTuple.get("user_name", String.class),
                    firstTuple.get("email", String.class),
                    new ArrayList<>()
            );

        }
        return null;
    }
}
