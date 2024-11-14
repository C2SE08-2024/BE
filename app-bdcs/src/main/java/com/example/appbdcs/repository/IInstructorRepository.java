package com.example.appbdcs.repository;

import com.example.appbdcs.model.Instructor;
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
public interface IInstructorRepository extends JpaRepository<Instructor, Integer> {

    @Query(value = "select s.instructor_id, s.instructor_address, s.instructor_code, s.instructor_email, s.instructor_gender, " +
            "s.instructor_img, s.instructor_name, s.instructor_phone, s.date_of_birth, s.id_card, s.is_enable, " +
            "c.cart_id, a.account_id " +
            "from instructor s " +
            "inner join cart c on s.cart_id = c.cart_id " +
            "left join account a on s.account_id = a.account_id " +
            "where s.is_enable = true " +
            "order by s.instructor_code",
            countQuery = "select count(s.instructor_id) " +
                    "from instructor s " +
                    "inner join cart c on s.cart_id = c.cart_id " +
                    "left join account a on s.account_id = a.account_id " +
                    "where s.is_enable = true " +
                    "order by s.instructor_code",
            nativeQuery = true)
    Page<Instructor> findAllInstructor(Pageable pageable);


    @Query(value = "select s.instructor_id, s.instructor_address, s.instructor_code, s.instructor_email, s.instructor_gender, " +
            "s.instructor_img, s.instructor_name, s.instructor_phone, s.date_of_birth, s.id_card, s.is_enable, " +
            "st.instructor_type_id, st.instructor_type_name, st.price, a.account_id, r.cart_id " +
            "from instructor s " +
            "inner join instructor_type st on s.instructor_type_id = st.instructor_type_id " +
            "left join account a on s.account_id = a.account_id " +
            "left join cart r on s.cart_id = r.cart_id " +
            "where (st.instructor_type_name like concat('%', :instructor_type, '%') " +
            "and s.instructor_name like concat('%', :instructor_name, '%') " +
            "and s.instructor_address like concat('%', :instructor_address, '%') " +
            "and s.instructor_phone like concat('%', :instructor_phone, '%')) " +
            "and (s.is_enable = true) " +
            "order by s.instructor_code",
            countQuery = "select count(s.instructor_id) " +
                    "from instructor s " +
                    "inner join instructor_type st on s.instructor_type_id = st.instructor_type_id " +
                    "left join account a on s.account_id = a.account_id " +
                    "left join cart r on s.cart_id = r.cart_id " +
                    "where (st.instructor_type_name like concat('%', :instructor_type, '%') " +
                    "and s.instructor_name like concat('%', :instructor_name, '%') " +
                    "and s.instructor_address like concat('%', :instructor_address, '%') " +
                    "and s.instructor_phone like concat('%', :instructor_phone, '%')) " +
                    "and (s.is_enable = true) " +
                    "order by s.instructor_code",
            nativeQuery = true)
    Page<Instructor> searchInstructor(@Param("instructor_type") String type,
                                      @Param("instructor_name") String name,
                                      @Param("instructor_address") String address,
                                      @Param("instructor_phone") String phone,
                                      Pageable pageable);

    @Query(value = "select * from instructor order by `instructor_code` desc limit 1", nativeQuery = true)
    Instructor limitInstructor();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO instructor (instructor_code, instructor_name, instructor_email, instructor_phone, " +
            "instructor_gender, date_of_birth, id_card, instructor_address, instructor_img, is_enable) " +
            "VALUES (:instructor_code, :instructor_name, :instructor_email, :instructor_phone, :instructor_gender, " +
            ":date_of_birth, :id_card, :instructor_address, :instructor_img, :is_enable)", nativeQuery = true)
    void insertInstructor(@Param("instructor_code") String instructor_code,
                          @Param("instructor_name") String instructor_name,
                          @Param("instructor_email") String instructor_email,
                          @Param("instructor_phone") String instructor_phone,
                          @Param("instructor_gender") Boolean instructor_gender,
                          @Param("date_of_birth") Date date_of_birth,
                          @Param("id_card") String id_card,
                          @Param("instructor_address") String instructor_address,
                          @Param("instructor_img") String instructor_img,
                          @Param("is_enable") Boolean is_enable);


    // @Modifying
//    @Query(value = "", nativeQuery = true)
//    void updateInstructorInstructor(@Param("instructor_id") Integer instructor_id,
//                        @Param("instructor_code") String instructor_code,
//                        @Param("instructor_name") String instructor_name,
//                        @Param("instructor_email") String instructor_email,
//                        @Param("instructor_phone") String instructor_phone,
//                        @Param("instructor_gender") Boolean instructor_gender,
//                        @Param("date_of_birth") Date date_of_birth,
//                        @Param("id_card") String id_card,
//                        @Param("instructor_address") String instructor_address,
//                        @Param("instructor_img") String instructor_img,
//                        @Param("is_enable") Boolean is_enable,
//                        @Param("instructor_type_id") CustomerType instructor_type_id,
//                        @Param("account_id") Account account_id,
//                        @Param("cart_id") Cart cart);

//    @Modifying
//    @Query(value = "DELETE FROM instructor WHERE instructor_id = :instructor_id", nativeQuery = true)
//    void deleteInstructorId(@Param("instructor_id") Integer id);


//    @Query(value = "", nativeQuery = true)
//    List<Tuple> findUserDetailByUsername(@Param("username") String username);


}
