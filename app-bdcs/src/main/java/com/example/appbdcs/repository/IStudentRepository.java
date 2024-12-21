package com.example.appbdcs.repository;


import com.example.appbdcs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student order by `student_code` desc limit 1", nativeQuery = true)
    Student limitStudent();

    // Truy vấn lấy danh sách học sinh tham gia khóa học với MySQL thuần
    @Query(value = "SELECT * FROM student s " +
            "JOIN course_student cs ON s.student_id = cs.student_id " +
            "WHERE cs.course_id = :courseId", nativeQuery = true)
    List<Student> findStudentsByCourse(Integer courseId);

    // Truy vấn lấy tất cả học sinh
    @Query(value = "SELECT * FROM student", nativeQuery = true)
    List<Student> findAllStudents();

    // Truy vấn lấy học sinh theo mã học sinh
    @Query(value = "SELECT * FROM student WHERE student_code = :studentCode", nativeQuery = true)
    Student findStudentByCode(String studentCode);

    // Truy vấn lấy tất cả học sinh có điểm thi qua một bài kiểm tra cụ thể
    @Query(value = "SELECT s.* FROM student s " +
            "JOIN student_test_result str ON s.student_id = str.student_id " +
            "JOIN test t ON str.test_id = t.test_id " +
            "WHERE t.test_id = :testId", nativeQuery = true)
    List<Student> findStudentsByTest(Integer testId);

    // Tìm tất cả học sinh trong khóa học
    @Query(value = "SELECT s.* FROM student s " +
            "JOIN course_student cs ON s.student_id = cs.student_id " +
            "WHERE cs.course_id = :courseId", nativeQuery = true)
    List<Student> findStudentsInCourse(@Param("courseId") Integer courseId);

    @Query("SELECT s FROM Student s WHERE s.studentId = :studentId")
    Optional<Student> findById(@Param("studentId") Integer studentId);

    @Query(value = "SELECT * FROM student LIMIT :size OFFSET :offset", nativeQuery = true)
    List<Student> findAllWithPagination(@Param("size") int size, @Param("offset") int offset);

    @Query(value = "INSERT INTO student (student_code, student_name, student_email, student_phone, student_gender, student_address, student_img, is_enable, major, graduation_year) " +
            "VALUES (:studentCode, :studentName, :studentEmail, :studentPhone, :studentGender, :studentAddress, :studentImg, :isEnable, :major, :graduationYear)",
            nativeQuery = true)
    void addStudent(@Param("studentCode") String studentCode,
                    @Param("studentName") String studentName,
                    @Param("studentEmail") String studentEmail,
                    @Param("studentPhone") String studentPhone,
                    @Param("studentGender") Boolean studentGender,
                    @Param("studentAddress") String studentAddress,
                    @Param("studentImg") String studentImg,
                    @Param("isEnable") Boolean isEnable,
                    @Param("major") String major,
                    @Param("graduationYear") Integer graduationYear);

    @Query(value = "UPDATE student SET student_code = :studentCode, student_name = :studentName, student_email = :studentEmail, student_phone = :studentPhone, " +
            "student_gender = :studentGender, student_address = :studentAddress, student_img = :studentImg " +
            "WHERE student_id = :studentId", nativeQuery = true)
    void updateStudent(@Param("studentId") Integer studentId,
                       @Param("studentCode") String studentCode,
                       @Param("studentName") String studentName,
                       @Param("studentEmail") String studentEmail,
                       @Param("studentPhone") String studentPhone,
                       @Param("studentGender") Boolean studentGender,
                       @Param("studentAddress") String studentAddress,
                       @Param("studentImg") String studentImg);

    @Query(value = "DELETE FROM student WHERE student_id = :studentId", nativeQuery = true)
    void deleteStudent(@Param("studentId") Integer studentId);

    @Query(value = "select s.student_id, s.student_code, s.student_name, s.student_phone, s.student_gender, " +
            "s.date_of_birth, s.id_card, s.student_address, s.student_img, s.major, s.graduation_year, " +
            "a.username, a.email " +
            "from student s " +
            "inner join account a on s.account_id = a.account_id " +
            "where (s.is_enable = true) and (a.is_enable = true) and (a.username = :username)", nativeQuery = true)
    Optional<Tuple> findUserDetailByUsername(@Param("username") String username);

    @Query(value = "SELECT s.* FROM student s JOIN account a ON s.account_id = a.account_id WHERE a.username = :username",
            nativeQuery = true)
    Optional<Student> findByUsername(@Param("username") String username);

}
