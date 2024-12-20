package com.example.appbdcs.controller;

import com.example.appbdcs.dto.instructor.InstructorUserDetailDto;
import com.example.appbdcs.service.IInstructorService;
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
@RequestMapping("/api/v1/instructor")
@CrossOrigin(origins = "http://localhost:4200")
public class InstructorController {

    @Autowired
    private IInstructorService instructorService;

    @GetMapping("/detail")
    public ResponseEntity<InstructorUserDetailDto> getDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        InstructorUserDetailDto instructorUserDetailDto = instructorService.findUserDetailByUsername(username);

        if (instructorUserDetailDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(instructorUserDetailDto, HttpStatus.OK);
    }
}
