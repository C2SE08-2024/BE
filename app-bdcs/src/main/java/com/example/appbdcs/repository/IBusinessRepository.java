package com.example.appbdcs.repository;


import com.example.appbdcs.model.Account;
import com.example.appbdcs.model.Business;

import com.example.appbdcs.model.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.Date;

public interface IBusinessRepository extends JpaRepository<Business, Integer> {

    @Query(value = "select s.business_id, s.business_address, s.business_code, s.business_email, " +
            "s.business_img, s.business_name, s.business_phone, s.is_enable, " +
            " a.account_id " +
            "from business s " +
            "left join account a on s.account_id = a.account_id " +
            "where s.is_enable = true " +
            "order by s.business_code",
            countQuery = "select count(s.business_id) " +
                    "from business s " +

                    "left join account a on s.account_id = a.account_id " +
                    "where s.is_enable = true " +
                    "order by s.business_code",
            nativeQuery = true)
    Page<Business> findAllBusiness(Pageable pageable);


    @Query(value = "select s.business_id, s.business_address, s.business_code, s.business_email, " +
            "s.business_img, s.business_name, s.business_phone, s.is_enable, " +
            "st.business_type_id, st.business_type_name, a.account_id,  " +
            "from business s " +
            "inner join business_type st on s.business_type_id = st.business_type_id " +
            "left join account a on s.account_id = a.account_id " +

            "where (st.business_type_name like concat('%', :business_type, '%') " +
            "and s.business_name like concat('%', :business_name, '%') " +
            "and s.business_address like concat('%', :business_address, '%') " +
            "and s.business_phone like concat('%', :business_phone, '%')) " +
            "and (s.is_enable = true) " +
            "order by s.business_code",
            countQuery = "select count(s.business_id) " +
                    "from business s " +
                    "inner join business_type st on s.business_type_id = st.business_type_id " +
                    "left join account a on s.account_id = a.account_id " +

                    "where (st.business_type_name like concat('%', :business_type, '%') " +
                    "and s.business_name like concat('%', :business_name, '%') " +
                    "and s.business_address like concat('%', :business_address, '%') " +
                    "and s.business_phone like concat('%', :business_phone, '%')) " +
                    "and (s.is_enable = true) " +
                    "order by s.business_code",
            nativeQuery = true)
    Page<Business> searchBusiness(@Param("business_type") String type,
                                      @Param("business_name") String name,
                                      @Param("business_address") String address,
                                      @Param("business_phone") String phone,
                                      Pageable pageable);

    @Query(value = "select * from business order by `business_code` desc limit 1", nativeQuery = true)
    Business limitBusiness();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO business (business_code, business_name, business_email, business_phone, " +
            "business_address, business_img, is_enable) " +
            "VALUES (:business_code, :business_name, :business_email, :business_phone,  " +
            ":business_address, :business_img, :is_enable)", nativeQuery = true)
    void insertBusiness(@Param("business_code") String business_code,
                          @Param("business_name") String business_name,
                          @Param("business_email") String business_email,
                          @Param("business_phone") String business_phone,
                          @Param("business_address") String business_address,
                          @Param("business_img") String business_img,
                          @Param("is_enable") Boolean is_enable);
}
