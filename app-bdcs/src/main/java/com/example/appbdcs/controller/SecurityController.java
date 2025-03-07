package com.example.appbdcs.controller;

import com.example.appbdcs.dto.request.LoginRequest;
import com.example.appbdcs.dto.request.SignupRequest;
import com.example.appbdcs.dto.response.JwtResponse;
import com.example.appbdcs.dto.response.MessageResponse;
import com.example.appbdcs.model.*;
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
import java.util.HashSet;
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
    private IInstructorService instructorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IBusinessService businessService;

    @Autowired
    private IRoleService roleService;

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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (accountService.existsByUsername(signupRequest.getUsername())) {
            return new ResponseEntity<>(new MessageResponse("The username existed! Please try again!"), HttpStatus.OK);
        }

        if (accountService.existsByEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>(new MessageResponse("The email existed! Please try again!"), HttpStatus.OK);
        }

        Account account = new Account();
        account.setUsername(signupRequest.getUsername());
        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        account.setEmail(signupRequest.getEmail());
        account.setIsEnable(true);
        accountService.save(account);

        Set<Role> tempRoles = new HashSet<>();
        String userType = signupRequest.getUserType();

        if ("business".equalsIgnoreCase(userType)) {
            Role businessRole = roleService.findByName("ROLE_BUSINESS");
            tempRoles.add(businessRole);
            account.setRoles(tempRoles);

            Business businessLimit = businessService.businessLimit();
            signupRequest.setCode(ConverterMaxCode.generateNextId(businessLimit.getBusinessCode()));

            businessService.save(new Business(
                    signupRequest.getCode(),
                    signupRequest.getName(),
                    signupRequest.getEmail(),
                    signupRequest.getPhone(),
                    signupRequest.getAddress(),
                    true,
                    account
            ));

        } else if ("instructor".equalsIgnoreCase(userType)) {
            Role instructorRole = roleService.findByName("ROLE_INSTRUCTOR");
            tempRoles.add(instructorRole);
            account.setRoles(tempRoles);

            Instructor instructorLimit = instructorService.instructorLimit();
            signupRequest.setCode(ConverterMaxCode.generateNextId(instructorLimit.getInstructorCode()));

            instructorService.save(new Instructor(
                    signupRequest.getCode(),
                    signupRequest.getName(),
                    signupRequest.getEmail(),
                    signupRequest.getPhone(),
                    signupRequest.getGender(),
                    signupRequest.getDateOfBirth(),
                    signupRequest.getIdCard(),
                    signupRequest.getAddress(),
                    true,
                    account
            ));
        } else {
            Role studentRole = roleService.findByName("ROLE_STUDENT");
            tempRoles.add(studentRole);
            account.setRoles(tempRoles);

            Student studentLimit = studentService.studentLimit();
            signupRequest.setCode(ConverterMaxCode.generateNextId(studentLimit.getStudentCode()));

            studentService.save(new Student(
                    signupRequest.getCode(),
                    signupRequest.getName(),
                    signupRequest.getEmail(),
                    signupRequest.getPhone(),
                    signupRequest.getGender(),
                    signupRequest.getDateOfBirth(),
                    signupRequest.getIdCard(),
                    signupRequest.getAddress(),
                    true,
                    account
            ));
        }

        return new ResponseEntity<>(new MessageResponse("Account registration successful!"), HttpStatus.OK);
    }
}