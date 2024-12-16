package com.example.appbdcs.controller;


import com.example.appbdcs.dto.instructor.InstructorDTO;
import com.example.appbdcs.dto.instructor.InstructorUserDetailDto;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    // API: Lấy tất cả instructors phân trang
    @GetMapping
    public ResponseEntity<Page<Instructor>> getAllInstructors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Instructor> instructors = instructorService.findAll(pageable);
        return ResponseEntity.ok(instructors);
    }

    // API: Tìm kiếm instructors theo tên hoặc email, phân trang
    @GetMapping("/search")
    public ResponseEntity<Page<Instructor>> searchInstructors(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Instructor> instructors = instructorService.searchInstructors(name, email, page, size);
        return ResponseEntity.ok(instructors);
    }

    // API: Lấy instructor theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id) {
        Instructor instructor = instructorService.findById(id);
        if (instructor != null) {
            return ResponseEntity.ok(instructor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // API: Thêm mới một instructor
    @PostMapping
    public ResponseEntity<Instructor> addInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = instructorService.addInstructor(instructorDTO);
        return ResponseEntity.ok(instructor);
    }

    // API: Cập nhật thông tin instructor theo ID
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(
            @PathVariable Integer id, @RequestBody InstructorDTO instructorDTO) {
        try {
            Instructor updatedInstructor = instructorService.updateInstructor(id, instructorDTO);
            return ResponseEntity.ok(updatedInstructor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // API: Xóa một instructor theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Integer id) {
        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // API: Lấy instructor mới nhất (theo mã instructor)
    @GetMapping("/latest")
    public ResponseEntity<Instructor> getLatestInstructor() {
        Instructor instructor = instructorService.instructorLimit();
        return instructor != null ? ResponseEntity.ok(instructor) : ResponseEntity.notFound().build();
    }
}
