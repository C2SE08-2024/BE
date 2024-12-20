package com.example.appbdcs.repository;

import com.example.appbdcs.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IBusinessRepository extends JpaRepository<Business, Integer> {

    @Query(value = "select * from business order by `business_code` desc limit 1", nativeQuery = true)
    Business limitBusiness();
}
