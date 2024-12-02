package com.example.appbdcs.dto.course;

//import com.example.appbdcs.model.Category;
import com.example.appbdcs.model.Instructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Data
public class CourseDetailDTO {
    @NotNull
    @Size(min = 1, max = 255, message = "Tên khóa học không được để trống và phải ít hơn 255 ký tự.")
    private String courseName;

    @NotNull(message = "Giá khóa học không được để trống.")
    private Integer coursePrice;

    @Size(max = 2000, message = "Mô tả khóa học phải ít hơn 2000 ký tự.")
    private String description;

    private String duration;
    private String image;
    private Boolean status;
    private Integer level;
    private String language;
    private Date createAt;
    private Date updateAt;
}

