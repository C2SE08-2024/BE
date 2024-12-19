package com.example.appbdcs.repository;

import com.example.appbdcs.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.util.Optional;

@Repository
@Transactional
public interface IBusinessRepository extends JpaRepository<Business, Integer> {

    @Query(value = "SELECT * FROM business WHERE business_id = :id", nativeQuery = true)
    Optional<Business> findBusinessById(@Param("id") Integer id);

    @Query(value = "select * from business order by `business_code` desc limit 1", nativeQuery = true)
    Business limitBusiness();

    @Query(value = "select b.business_id, b.business_code, b.business_name, b.business_phone, b.business_address, " +
            "b.business_img, b.`description`, b.industry, b.founded_year, b.website, b.size, a.username, a.email " +
            "from business b " +
            "inner join account a on b.account_id = a.account_id " +
            "where (b.is_enable = true) and (a.is_enable = true) and (a.username = :username)", nativeQuery = true)
    Optional<Tuple> findUserDetailByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM business b " +
            "INNER JOIN account a ON b.account_id = a.account_id " +
            "WHERE (b.is_enable = true) and (a.is_enable = true) and (a.username = :username)", nativeQuery = true)
    Optional<Business> findByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Business SET business_code = :businessCode, business_name = :businessName, " +
            "business_email = :businessEmail, business_phone = :businessPhone, " +
            "business_address = :businessAddress, business_img = :businessImg, " +
            "description = :description, is_enable = :isEnable, industry = :industry, " +
            "founded_year = :foundedYear, website = :website, size = :size " +
            "WHERE business_id = :businessId", nativeQuery = true)
    void updateBusiness(@Param("businessId") Integer businessId,
                        @Param("businessCode") String businessCode,
                        @Param("businessName") String businessName,
                        @Param("businessEmail") String businessEmail,
                        @Param("businessPhone") String businessPhone,
                        @Param("businessAddress") String businessAddress,
                        @Param("businessImg") String businessImg,
                        @Param("description") String description,
                        @Param("isEnable") Boolean isEnable,
                        @Param("industry") String industry,
                        @Param("foundedYear") Integer foundedYear,
                        @Param("website") String website,
                        @Param("size") String size);
}
