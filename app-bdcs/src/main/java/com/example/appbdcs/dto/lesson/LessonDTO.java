package com.example.appbdcs.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
    private Integer lessonId;
    private String lessonName;
    private String lessonContent;
    private String video;
    private String lessonDuration;
}
