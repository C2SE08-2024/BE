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
    private Integer courseId;
    private Integer testId;

    public LessonDTO(Integer lessonId, String lessonName, String lessonContent, String video, String lessonDuration) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonContent = lessonContent;
        this.video = video;
        this.lessonDuration = lessonDuration;
    }
}
