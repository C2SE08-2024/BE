package com.example.appbdcs.repository;


import com.example.appbdcs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student order by `student_code` desc limit 1", nativeQuery = true)
    Student limitStudent();

    @Query(value = "SELECT s.* FROM student s JOIN account a ON s.account_id = a.account_id WHERE a.username = ?1",
            nativeQuery = true)
    Student findStudentByUsername(String username);
}
