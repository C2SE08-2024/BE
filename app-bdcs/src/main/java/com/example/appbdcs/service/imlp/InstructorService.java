package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.repository.IInstructorRepository;
import com.example.appbdcs.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Instructor instructorLimit() {
        return instructorRepository.limitInstructor();
    }
}
