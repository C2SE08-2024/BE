package com.example.appbdcs.repository;


import com.example.appbdcs.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IRequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "SELECT * FROM request WHERE business_id = :businessId", nativeQuery = true)
    List<Request> findBusinessById(Integer businessId);
    @Query(value = "SELECT * FROM request WHERE business_id = :businessId", nativeQuery = true)
    List<Request> findByBusiness_BusinessId(Integer businessId);
}
