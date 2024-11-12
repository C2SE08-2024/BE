package com.example.appbdcs.repository;

import com.example.appbdcs.model.Cart;
import com.example.appbdcs.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select s.student_id, s.student_address, s.student_code, s.student_email, s.student_gender, " +
            "s.student_img, s.student_name, s.student_phone, s.date_of_birth, s.id_card, s.is_enable, " +
            "c.cart_id, a.account_id " +
            "from student s " +
            "inner join cart c on s.cart_id = c.cart_id " +
            "left join account a on s.account_id = a.account_id " +
            "where s.is_enable = true " +
            "order by s.student_code",
            countQuery = "select count(s.student_id) " +
                    "from student s " +
                    "inner join cart c on s.cart_id = c.cart_id " +
                    "left join account a on s.account_id = a.account_id " +
                    "where s.is_enable = true " +
                    "order by s.student_code",
            nativeQuery = true)
    Page<Student> findAllStudent(Pageable pageable);


    @Query(value = "select s.student_id, s.student_address, s.student_code, s.student_email, s.student_gender, " +
            "s.student_img, s.student_name, s.student_phone, s.date_of_birth, s.id_card, s.is_enable, " +
            "st.student_type_id, st.student_type_name, st.price, a.account_id, r.cart_id " +
            "from student s " +
            "inner join student_type st on s.student_type_id = st.student_type_id " +
            "left join account a on s.account_id = a.account_id " +
            "left join cart r on s.cart_id = r.cart_id " +
            "where (st.student_type_name like concat('%', :student_type, '%') " +
            "and s.student_name like concat('%', :student_name, '%') " +
            "and s.student_address like concat('%', :student_address, '%') " +
            "and s.student_phone like concat('%', :student_phone, '%')) " +
            "and (s.is_enable = true) " +
            "order by s.student_code",
            countQuery = "select count(s.student_id) " +
                    "from student s " +
                    "inner join student_type st on s.student_type_id = st.student_type_id " +
                    "left join account a on s.account_id = a.account_id " +
                    "left join cart r on s.cart_id = r.cart_id " +
                    "where (st.student_type_name like concat('%', :student_type, '%') " +
                    "and s.student_name like concat('%', :student_name, '%') " +
                    "and s.student_address like concat('%', :student_address, '%') " +
                    "and s.student_phone like concat('%', :student_phone, '%')) " +
                    "and (s.is_enable = true) " +
                    "order by s.student_code",
            nativeQuery = true)
    Page<Student> searchStudent(@Param("student_type") String type,
                                @Param("student_name") String name,
                                @Param("student_address") String address,
                                @Param("student_phone") String phone,
                                Pageable pageable);

    @Query(value = "select * from student order by `student_code` desc limit 1", nativeQuery = true)
    Student limitStudent();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO student (student_code, student_name, student_email, student_phone, " +
            "student_gender, date_of_birth, id_card, student_address, student_img, is_enable, cart_id) " +
            "VALUES (:student_code, :student_name, :student_email, :student_phone, :student_gender, " +
            ":date_of_birth, :id_card, :student_address, :student_img, :is_enable, :cart_id)", nativeQuery = true)
    void insertStudent(@Param("student_code") String student_code,
                        @Param("student_name") String student_name,
                        @Param("student_email") String student_email,
                        @Param("student_phone") String student_phone,
                        @Param("student_gender") Boolean student_gender,
                        @Param("date_of_birth") Date date_of_birth,
                        @Param("id_card") String id_card,
                        @Param("student_address") String student_address,
                        @Param("student_img") String student_img,
                        @Param("is_enable") Boolean is_enable,
                        @Param("cart_id") Integer cart_id);

  // @Modifying
//    @Query(value = "", nativeQuery = true)
//    void updateStudent(@Param("student_id") Integer student_id,
//                        @Param("student_code") String student_code,
//                        @Param("student_name") String student_name,
//                        @Param("student_email") String student_email,
//                        @Param("student_phone") String student_phone,
//                        @Param("student_gender") Boolean student_gender,
//                        @Param("date_of_birth") Date date_of_birth,
//                        @Param("id_card") String id_card,
//                        @Param("student_address") String student_address,
//                        @Param("student_img") String student_img,
//                        @Param("is_enable") Boolean is_enable,
//                        @Param("student_type_id") CustomerType student_type_id,
//                        @Param("account_id") Account account_id,
//                        @Param("cart_id") Cart cart);

    @Modifying
    @Query(value = "DELETE FROM student WHERE student_id = :student_id", nativeQuery = true)
    void deleteStudentId(@Param("student_id") Integer id);


//    @Query(value = "", nativeQuery = true)
//    List<Tuple> findUserDetailByUsername(@Param("username") String username);

    Student findByCart(Cart cart);
}
