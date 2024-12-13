package com.example.appbdcs.controller;

import com.example.appbdcs.model.Business;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.model.Request;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.service.IBusinessService;
import com.example.appbdcs.service.ICourseService;
import com.example.appbdcs.service.IRequestService;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/requests")
@CrossOrigin(origins = "http://localhost:4200")
public class RequestController {

    @Autowired
    private IRequestService requestService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IBusinessService businessService;

    // API gửi yêu cầu xem thông tin học viên
    @PostMapping("/send-request")
    public ResponseEntity<String> sendRequestToViewStudentInfo(
            @RequestParam Integer businessId,
            @RequestParam Integer courseId
    ) {
        // Tìm doanh nghiệp và khóa học
        Business business = businessService.findById(businessId);
        Course course = courseService.findCourseById(courseId);

        if (business == null || course == null) {
            return ResponseEntity.badRequest().body("Business or Course not found");
        }
// Tạo yêu cầu cho từng sinh viên trong khóa học
        // Lấy danh sách sinh viên từ khóa học
        Set<Student> studentsSet = course.getStudents(); // Đây là kiểu Set<Student>
        List<Student> studentsList = new ArrayList<>(studentsSet); // Chuyển Set thành List

        // Tạo yêu cầu cho từng sinh viên trong khóa học
        for (Student student : studentsList) {
            Request request = new Request();
            request.setBusiness(business);
            request.setStudent(student);
            request.setRequestDate(java.time.LocalDateTime.now());
            request.setCanView(false); // Mặc định yêu cầu chưa được chấp nhận
            requestService.saveRequest(request);
        }

        return ResponseEntity.ok("Request sent to view students' info.");
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<Request>> getRequestsByBusiness(@PathVariable Integer businessId) {
        List<Request> requests = requestService.getRequestsByBusinessId(businessId);
        return ResponseEntity.ok(requests);
    }

    // API để doanh nghiệp cập nhật trạng thái yêu cầu
    @PutMapping("/accept-request/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable Integer requestId) {
        Request request = requestService.getRequestById(requestId);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }
        request.setIsAccepted(true);
        request.setCanView(true); // Chấp nhận yêu cầu xem thông tin
        requestService.saveRequest(request);
        return ResponseEntity.ok("Request accepted. Business can now view student's info.");
    }

    @PutMapping("/reject-request/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Integer requestId) {
        Request request = requestService.getRequestById(requestId);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }
        request.setIsAccepted(false);
        request.setCanView(false); // Từ chối yêu cầu xem thông tin
        requestService.saveRequest(request);
        return ResponseEntity.ok("Request rejected.");
    }
}
