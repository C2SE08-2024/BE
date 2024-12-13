package com.example.appbdcs.repository;

import com.example.appbdcs.model.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public interface IInstructorRepository extends JpaRepository<Instructor, Integer> {

    @Query(value = "select * from instructor order by `instructor_code` desc limit 1", nativeQuery = true)
    Instructor limitInstructor();

    @Query(value = "SELECT * FROM instructor i JOIN account a ON i.account_id = a.account_id WHERE a.username = ?1",
            nativeQuery = true)
    Instructor findByUsername(String username);

    @Query(value = "SELECT * FROM instructor i JOIN account a ON i.account_id = a.account_id WHERE i.instructor_name LIKE %?1% OR i.instructor_email LIKE %?2%",
            countQuery = "SELECT count(*) FROM instructor i JOIN account a ON i.account_id = a.account_id WHERE i.instructor_name LIKE %?1% OR i.instructor_email LIKE %?2%",
            nativeQuery = true)
    Page<Instructor> searchInstructors(String name, String email, Pageable pageable);

    @Query(value = "SELECT * FROM instructor i JOIN account a ON i.account_id = a.account_id",
            countQuery = "SELECT count(*) FROM instructor i JOIN account a ON i.account_id = a.account_id",
            nativeQuery = true)
    Page<Instructor> findAllInstructors(Pageable pageable);

    @Query(value = "SELECT * FROM instructor WHERE instructor_id = ?1", nativeQuery = true)
    Instructor findInstructorById(Integer id);

    @Query(value = "INSERT INTO instructor (instructor_code, instructor_name, instructor_email, instructor_phone, instructor_gender, date_of_birth, id_card, instructor_address, instructor_img, is_enable, account_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11)", nativeQuery = true)
    void addInstructor(String instructorCode, String instructorName, String instructorEmail, String instructorPhone, Boolean instructorGender, Date dateOfBirth, String idCard, String instructorAddress, String instructorImg, Boolean isEnable, Integer accountId);

    @Query(value = "UPDATE instructor SET instructor_code = ?1, instructor_name = ?2, instructor_email = ?3, instructor_phone = ?4, instructor_gender = ?5, date_of_birth = ?6, id_card = ?7, instructor_address = ?8, instructor_img = ?9, is_enable = ?10, account_id = ?11 WHERE instructor_id = ?12", nativeQuery = true)
    void updateInstructor(String instructorCode, String instructorName, String instructorEmail, String instructorPhone, Boolean instructorGender, Date dateOfBirth, String idCard, String instructorAddress, String instructorImg, Boolean isEnable, Integer accountId, Integer instructorId);

    @Query(value = "DELETE FROM instructor WHERE instructor_id = ?1", nativeQuery = true)
    void deleteInstructorById(Integer id);
}
