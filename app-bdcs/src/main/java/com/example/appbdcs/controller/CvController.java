package com.example.appbdcs.controller;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Cv;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.service.ICvService;
import com.example.appbdcs.service.IStudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
@RequestMapping("/api/v1/cvs")
@CrossOrigin(origins = "http://localhost:4200")
public class CvController {

    @Autowired
    private ICvService cvService;

    @Autowired
    private IStudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<Cv> createCv(@RequestBody CreateCvRequest request) {
        // Lấy thông tin Student từ database (giả sử đã có sẵn StudentService)
        StudentDTO student = studentService.findById(request.getStudentId());
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Tạo CV và lưu vào DB
        Cv cv = cvService.createCvForStudent(
                student,
                request.getCvContent(),
                request.getFilePath(),
                request.getCvType()
        );

        return ResponseEntity.ok(cv);
    }
}

class CreateCvRequest {
    private Integer studentId; // ID của Student
    private String cvContent;  // Nội dung CV
    private String filePath;   // Đường dẫn của file sau khi tạo thành công
    private String cvType;     // Loại CV

    public CreateCvRequest(Integer studentId, String cvContent, String filePath, String cvType) {
        this.studentId = studentId;
        this.cvContent = cvContent;
        this.filePath = filePath;
        this.cvType = cvType;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getCvContent() {
        return cvContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getCvType() {
        return cvType;
    }
}

