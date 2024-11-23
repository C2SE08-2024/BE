package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.error.NotFoundById;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.repository.ICourseRepository;
import com.example.appbdcs.service.ICourseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @SneakyThrows
    @Override
    public Course findCourseById(Integer id) {
        Optional<Course> course = courseRepository.findCourseById(id);
        if (course.isPresent()) {
            return course.get();
        }
        throw new NotFoundById("Could not find any courses with code: " + id);
    }

    @Override
    public List<PopularCourseDTO> getMostPopularCourses() {
        List<Object[]> rawResults = courseRepository.findMostPopularCourses();
        List<PopularCourseDTO> popularCourses = new ArrayList<>();

        for (Object[] row : rawResults) {
            PopularCourseDTO popularCourseDTO = new PopularCourseDTO(
                    (Integer) row[0],
                    (String) row[1],
                    (Integer) row[2],
                    (String) row[3],
                    (Boolean) row[4],
                    ((Number) row[5]).longValue()
            );
            popularCourses.add(popularCourseDTO);
        }
        return popularCourses;
    }
}
