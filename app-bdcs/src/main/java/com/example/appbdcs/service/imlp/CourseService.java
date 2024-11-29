package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.course.CourseDetailDTO;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.repository.ICourseRepository;
import com.example.appbdcs.service.ICourseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Setter
@Service
@Getter
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseDetailDTO courseDetailDTO) {
        Course course = mapDtoToCourse(courseDetailDTO);
        course.setCreateAt(new Date()); // Gán ngày tạo hiện tại
        course.setUpdateAt(new Date()); // Gán ngày cập nhật hiện tại
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Integer id, CourseDetailDTO courseDetailDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course không tồn tại với ID: " + id));
        course = mapDtoToCourse(courseDetailDTO, course);
        course.setUpdateAt(new Date()); // Cập nhật ngày update
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Integer id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Course không tồn tại với ID: " + id);
        }
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course không tồn tại với ID: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Private method để map DTO sang Course
    private Course mapDtoToCourse(CourseDetailDTO dto) {
        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setCoursePrice(dto.getCoursePrice());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setImage(dto.getImage());
        course.setStatus(dto.getStatus());
        course.setLevel(dto.getLevel());
        course.setLanguage(dto.getLanguage());
        // Thêm các trường dữ liệu khác nếu cần
        return course;
    }

    // Overload method để cập nhật Course từ DTO
    private Course mapDtoToCourse(CourseDetailDTO dto, Course course) {
        course.setCourseName(dto.getCourseName());
        course.setCoursePrice(dto.getCoursePrice());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setImage(dto.getImage());
        course.setStatus(dto.getStatus());
        course.setLevel(dto.getLevel());
        course.setLanguage(dto.getLanguage());
        // Thêm các trường dữ liệu khác nếu cần
        return course;
    }


}
