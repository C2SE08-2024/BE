package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Course;
import com.example.appbdcs.repository.ICourseRepository;
import com.example.appbdcs.service.ICourseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Setter
@Service
@Getter
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;



    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> updateCourse(Integer id, Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setCourseName(courseDetails.getCourseName());
            course.setCoursePrice(courseDetails.getCoursePrice());
            course.setDuration(courseDetails.getDuration());
            course.setUpdateAt(courseDetails.getUpdateAt());
            course.setCategory(courseDetails.getCategory());
            course.setInstructor(courseDetails.getInstructor());
            // ... cập nhật các trường khác nếu cần
            courseRepository.save(course);
            return Optional.of(course);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCourse(Integer id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Course findById(Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }


}
