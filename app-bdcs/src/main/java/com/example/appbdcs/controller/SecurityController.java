package com.example.appbdcs.controller;

import com.example.appbdcs.dto.request.LoginRequest;
import com.example.appbdcs.dto.request.StudentSignupRequest;
import com.example.appbdcs.dto.response.JwtResponse;
import com.example.appbdcs.dto.response.MessageResponse;
import com.example.appbdcs.model.Account;
import com.example.appbdcs.model.Role;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.security.jwt.JwtUtility;

import com.example.appbdcs.security.userprinciple.UserPrinciple;
import com.example.appbdcs.service.*;
import com.example.appbdcs.utils.ConverterMaxCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        roles
                )
        );
    }

    @PostMapping("/student/signup")
    public ResponseEntity<?> registerStudent(@RequestBody StudentSignupRequest studentSignupRequest) {
        if (accountService.existsByUsername(studentSignupRequest.getUsername())) {
            return new ResponseEntity<>(new MessageResponse("The username existed! Please try again!"), HttpStatus.OK);
        }

        if (accountService.existsByEmail(studentSignupRequest.getEmail())) {
            return new ResponseEntity<>(new MessageResponse("The email existed! Please try again!"), HttpStatus.OK);
        }

        Account account = new Account();
        account.setUsername(studentSignupRequest.getUsername());
        account.setPassword(passwordEncoder.encode(studentSignupRequest.getPassword()));
        account.setEmail(studentSignupRequest.getEmail());
        account.setIsEnable(true);
        accountService.save(account);

        Role role = new Role(1, "ROLE_STUDENT");
        Set<Role> tempRoles = account.getRoles();
        tempRoles.add(role);
        account.setRoles(tempRoles);

        /**
         Chỉ khi có trước 1 hàng dữ liệu trong databse thì sử dụng 2 dòng code này
         * */
        Student studentLimit = studentService.studentLimit();
        studentSignupRequest.setStudentCode(ConverterMaxCode.generateNextId(studentLimit.getStudentCode()));

        studentService.save(new Student(
                studentSignupRequest.getStudentCode(),
                studentSignupRequest.getName(),
                studentSignupRequest.getEmail(),
                studentSignupRequest.getPhone(),
                studentSignupRequest.getGender(),
                studentSignupRequest.getDateOfBirth(),
                studentSignupRequest.getIdCard(),
                studentSignupRequest.getAddress(),
                true,
                account
        ));

        return new ResponseEntity<>(new MessageResponse("Account registration successful!"), HttpStatus.OK);
    }
}
