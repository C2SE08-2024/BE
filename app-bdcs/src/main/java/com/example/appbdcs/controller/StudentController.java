package com.example.appbdcs.controller;

import com.example.appbdcs.dto.student.StudentUserDetailDto;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/detail")
    public ResponseEntity<StudentUserDetailDto> getDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        StudentUserDetailDto studentUserDetailDto = studentService.findUserDetailByUsername(username);

        if (studentUserDetailDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentUserDetailDto, HttpStatus.OK);
    }
}
