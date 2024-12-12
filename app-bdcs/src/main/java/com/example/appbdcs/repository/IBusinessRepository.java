package com.example.appbdcs.repository;

import com.example.appbdcs.model.Business;
import com.example.appbdcs.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IBusinessRepository extends JpaRepository<Business, Integer> {

    @Query(value = "select * from business order by `business_code` desc limit 1", nativeQuery = true)
    Business limitBusiness();


    // Tìm doanh nghiệp theo businessCode
    @Query(value = "SELECT * FROM business WHERE business_code = ?1", nativeQuery = true)
    Business findByBusinessCode(String businessCode);

    // Tìm tất cả doanh nghiệp
    @Query(value = "SELECT * FROM business", nativeQuery = true)
    List<Business> findAllBusinesses();

    // Tìm doanh nghiệp theo ID
    @Query(value = "SELECT * FROM business WHERE business_id = ?1", nativeQuery = true)
    Optional<Business> findBusinessById(Integer businessId);

}
