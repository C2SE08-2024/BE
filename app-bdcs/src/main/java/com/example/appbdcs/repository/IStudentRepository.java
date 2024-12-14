package com.example.appbdcs.repository;

import com.example.appbdcs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student order by `student_code` desc limit 1", nativeQuery = true)
    Student limitStudent();

    @Query(value = "SELECT s.* FROM student s JOIN account a ON s.account_id = a.account_id WHERE a.username = ?1",
            nativeQuery = true)
    Student findStudentByUsername(String username);

    @Query(value = "select s.student_id, s.student_code, s.student_name, s.student_phone, s.student_gender, " +
            "s.date_of_birth, s.id_card, s.student_address, s.student_img, s.major, s.graduation_year, " +
            "a.username, a.email " +
            "from student s " +
            "inner join account a on s.account_id = a.account_id " +
            "where (s.is_enable = true) and (a.is_enable = true) and (a.username = :username)", nativeQuery = true)
    Optional<Tuple> findUserDetailByUsername(@Param("username") String username);
}
