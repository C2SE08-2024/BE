package com.example.appbdcs.repository;

import com.example.appbdcs.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IInstructorRepository extends JpaRepository<Instructor, Integer> {

    @Query(value = "select * from instructor order by `instructor_code` desc limit 1", nativeQuery = true)
    Instructor limitInstructor();

    @Query(value = "SELECT * FROM instructor i JOIN account a ON i.account_id = a.account_id WHERE a.username = ?1",
            nativeQuery = true)
    Instructor findByUsername(String username);
}
