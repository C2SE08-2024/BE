package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.business.BusinessDTO;
import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.dto.course.CourseDetailDTO;
import com.example.appbdcs.model.Business;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.repository.IBusinessRepository;
import com.example.appbdcs.repository.ICourseRepository;
import com.example.appbdcs.repository.IInstructorRepository;
import com.example.appbdcs.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusinessService implements IBusinessService {

    @Autowired
    private IBusinessRepository businessRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IInstructorRepository instructorRepository;

    @Override
    public void save(Business business) {
    }

    @Override
    public Business businessLimit() {
        return null;
    }

    @Override
    public Business findById(Integer businessId) {
        return businessRepository.findById(businessId).orElse(null);
    }

    @Override
    public List<BusinessDTO> getAllBusinesses() {
        List<Business> businesses = businessRepository.findAll();
        return businesses.stream()
                .map(this::convertToBusinessDTO)
                .collect(Collectors.toList());
    }


    @Override
    public BusinessDTO getBusinessByCode(String businessCode) {
        Business business = businessRepository.findByBusinessCode(businessCode);
        if (business != null) {
            return convertToBusinessDTO(business);
        }
        return null;
    }

    public BusinessDTO findBusinessById(Integer businessId) {
        Optional<Business> business = businessRepository.findBusinessById(businessId);
        return business.map(this::convertToBusinessDTO).orElse(null); // Trả về BusinessDTO hoặc null
    }

    // Chuyển đổi từ Business sang BusinessDTO
    private BusinessDTO convertToBusinessDTO(Business business) {
        return new BusinessDTO(
                business.getBusinessId(),
                business.getBusinessCode(),
                business.getBusinessName(),
                business.getBusinessEmail(),
                business.getBusinessPhone(),
                business.getBusinessAddress(),
                business.getBusinessImg(),
                business.getDescription(),
                business.getIsEnable(),
                business.getIndustry(),
                business.getFoundedYear(),
                business.getWebsite(),
                business.getSize()
        );
    }
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> new CourseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getCoursePrice(),
                course.getImage(),
                course.getStatus(),
                course.getInstructor().getInstructorId() // hoặc bất kỳ thuộc tính nào từ Instructor
        )).collect(Collectors.toList());
    }

    public CourseDTO createCourse(CourseDTO courseDTO, Integer businessId) {
        // Kiểm tra xem doanh nghiệp có tồn tại không
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        // Tạo khóa học từ dữ liệu nhận được từ DTO
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setCoursePrice(courseDTO.getCoursePrice());
        course.setImage(courseDTO.getImage());
        course.setStatus(courseDTO.getStatus());

        // Giả sử bạn muốn gán giảng viên cho khóa học (bạn cần xử lý thêm phần này nếu có giảng viên)
        Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        course.setInstructor(instructor);

        // Lưu khóa học mới vào database
        Course savedCourse = courseRepository.save(course);

        // Chuyển đổi khóa học đã lưu sang DTO và trả về
        return new CourseDTO(
                savedCourse.getCourseId(),
                savedCourse.getCourseName(),
                savedCourse.getCoursePrice(),
                savedCourse.getImage(),
                savedCourse.getStatus(),
                savedCourse.getInstructor().getInstructorId()  // hoặc tên giảng viên
        );
    }
}
