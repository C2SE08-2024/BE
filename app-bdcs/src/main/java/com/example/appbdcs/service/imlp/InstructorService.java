package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.instructor.InstructorUserDetailDto;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.repository.IInstructorRepository;
import com.example.appbdcs.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.Optional;

@Service
public class InstructorService implements IInstructorService {

    @Autowired
    private IInstructorRepository instructorRepository;

    @Override
    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public Optional<Instructor> findInstructorById(Integer instructorId) {
        return instructorRepository.findByInstructorId(instructorId);
    }

    @Override
    public Instructor instructorLimit() {
        return instructorRepository.limitInstructor();
    }

    @Override
    public InstructorUserDetailDto findUserDetailByUsername(String username) {
        Tuple tuple = instructorRepository.findUserDetailByUsername(username).orElse(null);

        if (tuple != null) {
            return InstructorUserDetailDto.TupleToInstructorDto(tuple);
        }

        return null;
    }
}
