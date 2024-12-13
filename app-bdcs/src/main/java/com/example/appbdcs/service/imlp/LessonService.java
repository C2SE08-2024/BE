package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.lesson.LessonDTO;
import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.repository.ILessonRepository;
import com.example.appbdcs.service.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private ILessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;  // Assuming CourseService exists

    @Autowired
    private TestService testService;  // Assuming TestService exists

    @Override
    public List<Lesson> getLessonsByCourseId(Integer courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    // Tạo mới một bài học
    @Override
    public Optional<Lesson> createLesson(LessonDTO lessonDTO) {
        // Chèn bài học vào DB
        lessonRepository.createLesson(
                lessonDTO.getLessonName(),
                lessonDTO.getLessonContent(),
                lessonDTO.getVideo(),
                lessonDTO.getLessonDuration(),
                lessonDTO.getCourseId(),
                lessonDTO.getTestId()
        );

        // Truy vấn lại bài học vừa được tạo (bạn có thể truy vấn bài học bằng tên hoặc ID)
        // Giả sử bạn có thể truy vấn bài học theo tên (hoặc ID) để lấy bài học vừa tạo.
        // Lưu ý: Nếu `lessonDTO.getLessonName()` có thể không phải là duy nhất, bạn có thể thay thế bằng `lessonId`.
        return lessonRepository.findByLessonId(lessonDTO.getLessonId());  // Hoặc phương thức tương ứng
    }


    // Cập nhật bài học
    @Override
    public Lesson updateLesson(Integer lessonId, LessonDTO lessonDTO) {
        lessonRepository.updateLesson(
                lessonDTO.getLessonName(),
                lessonDTO.getLessonContent(),
                lessonDTO.getVideo(),
                lessonDTO.getLessonDuration(),
                lessonDTO.getCourseId(),
                lessonDTO.getTestId(),
                lessonId
        );
        // Sau khi cập nhật, bạn có thể truy vấn lại bài học vừa được cập nhật để trả về cho client
        return lessonRepository.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    // Xóa bài học
    @Override
    public void deleteLesson(Integer lessonId) {
        lessonRepository.deleteLessonById(lessonId);
    }

    public Optional<Lesson> findByLessonId(Integer lessonId) {
        return lessonRepository.findByLessonId(lessonId);
    }

    // Lấy bài học theo ID
    public Optional<Lesson> getLessonById(Integer lessonId) {
        return lessonRepository.findByLessonId(lessonId);
    }
}
