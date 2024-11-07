package com.example.appbdcs.service;

import com.example.appbdcs.dto.instructor.InstructorInfo;

import com.example.appbdcs.model.Cart;
import com.example.appbdcs.model.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IInstructorService {
    void save(Instructor instructor);

    Page<Instructor> findAllInstructor(Pageable pageable);

    Page<Instructor> searchInstructors(String type, String name, String address, String phone, Pageable pageable);

    Instructor instructorLimit();

    void saveInstructor(InstructorInfo instructorInfo);

    Instructor findById(Integer id);

    void updateInstructor(InstructorInfo instructorInfo, Integer id);

    void deleteById(Integer id);

//    InstructorUserDetailDto findUserDetailByUsername(String username);

    Instructor findByCart(Cart cart);
}
