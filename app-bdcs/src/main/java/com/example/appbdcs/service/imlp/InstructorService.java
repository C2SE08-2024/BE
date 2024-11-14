package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.instructor.InstructorInfo;
import com.example.appbdcs.model.Cart;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.repository.IInstructorRepository;
import com.example.appbdcs.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstructorService implements IInstructorService {

    @Autowired
    private IInstructorRepository instructorRepository;

    @Override
    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public Page<Instructor> findAllInstructor(Pageable pageable) {
        return this.instructorRepository.findAllInstructor(pageable);
    }

    @Override
    public Page<Instructor> searchInstructors(String type, String name, String address, String phone, Pageable pageable) {
        return this.instructorRepository.searchInstructor(type, name, address, phone, pageable);
    }

    @Override
    public Instructor instructorLimit() {
        return instructorRepository.limitInstructor();
    }

    @Override
    public void saveInstructor(InstructorInfo instructorInfo) {

   }

    @Override
    public Instructor findById(Integer id) {
        return null;
    }

    @Override
    public void updateInstructor(InstructorInfo instructorInfo, Integer id) {


    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Instructor findByCart(Cart cart) {
        return null;
    }
}
