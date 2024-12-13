package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.instructor.InstructorDTO;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.repository.IInstructorRepository;
import com.example.appbdcs.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public Page<Instructor> findAll(Pageable pageable) {
        return instructorRepository.findAllInstructors(pageable);
    }

    @Override
    public Page<Instructor> searchInstructors(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return instructorRepository.searchInstructors(name, email, pageable);
    }

    @Override
    public Instructor findById(Integer id) {
        // Tìm instructor theo ID
        return instructorRepository.findInstructorById(id);
    }

    @Override
    public void deleteById(Integer id) {
        // Xóa instructor theo ID
        instructorRepository.deleteInstructorById(id);
    }

    @Override
    public Instructor updateInstructor(Integer id, InstructorDTO instructorDTO) {
        // Lấy instructor cũ từ ID
        Instructor instructor = instructorRepository.findInstructorById(id);
        if (instructor == null) {
            throw new RuntimeException("Instructor not found with id: " + id);
        }

        // Cập nhật các trường từ DTO vào instructor
        instructor.setInstructorCode(instructorDTO.getInstructorCode());
        instructor.setInstructorName(instructorDTO.getInstructorName());
        instructor.setInstructorEmail(instructorDTO.getInstructorEmail());
        instructor.setInstructorPhone(instructorDTO.getInstructorPhone());
        instructor.setInstructorGender(instructorDTO.getInstructorGender());
        instructor.setDateOfBirth(instructorDTO.getDateOfBirth());
        instructor.setIdCard(instructorDTO.getIdCard());
        instructor.setInstructorAddress(instructorDTO.getInstructorAddress());
        instructor.setInstructorImg(instructorDTO.getInstructorImg());
        instructor.setIsEnable(instructorDTO.getIsEnable());
        instructor.setAccount(instructorDTO.getAccount());

        // Cập nhật instructor
        instructorRepository.updateInstructor(
                instructor.getInstructorCode(),
                instructor.getInstructorName(),
                instructor.getInstructorEmail(),
                instructor.getInstructorPhone(),
                instructor.getInstructorGender(),
                instructor.getDateOfBirth(),
                instructor.getIdCard(),
                instructor.getInstructorAddress(),
                instructor.getInstructorImg(),
                instructor.getIsEnable(),
                instructor.getAccount().getAccountId(),
                instructor.getInstructorId()
        );

        return instructor;
    }

    @Override
    public Instructor addInstructor(InstructorDTO instructorDTO) {
        // Tạo một đối tượng Instructor từ DTO
        Instructor instructor = new Instructor(
                instructorDTO.getInstructorCode(),
                instructorDTO.getInstructorName(),
                instructorDTO.getInstructorEmail(),
                instructorDTO.getInstructorPhone(),
                instructorDTO.getInstructorGender(),
                instructorDTO.getDateOfBirth(),
                instructorDTO.getIdCard(),
                instructorDTO.getInstructorAddress(),
                instructorDTO.getIsEnable(),
                instructorDTO.getAccount()
        );

        // Thêm mới instructor
        instructorRepository.addInstructor(
                instructor.getInstructorCode(),
                instructor.getInstructorName(),
                instructor.getInstructorEmail(),
                instructor.getInstructorPhone(),
                instructor.getInstructorGender(),
                instructor.getDateOfBirth(),
                instructor.getIdCard(),
                instructor.getInstructorAddress(),
                instructor.getInstructorImg(),
                instructor.getIsEnable(),
                instructor.getAccount().getAccountId()
        );

        return instructor;
    }
}
