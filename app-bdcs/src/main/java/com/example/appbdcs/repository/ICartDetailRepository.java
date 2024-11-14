package com.example.appbdcs.repository;

import com.example.appbdcs.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import  java.util.*;

@Repository
@Transactional
public interface ICartDetailRepository extends JpaRepository<CartDetail, Integer> {

//    @Query(value = "", nativeQuery = true)
//    List<CartDetail> findByCartId(@Param("id") Integer id);
//
//    @Query(value = "",
//            nativeQuery = true)
//    Optional<CartDetail> checkAvailable(@Param("customer_type_id") Integer id, @Param("cart_id") Integer cart_id);
//
//    @Modifying
//    @Query(value = "",
//            nativeQuery = true)
//    void insertCartDetail(@Param("customer_type_id") Integer customer_type_id, @Param("cart_id") Integer cart_id);
//
//    @Modifying
//    @Query(value = "",
//            nativeQuery = true)
//    void updateCartDetail(@Param("customer_type_id") Integer customer_type_id,
//                          @Param("quantity") int quantity,
//                          @Param("status") boolean status,
//                          @Param("cart_id") Integer cart_id,
//                          @Param("cart_detail_id") Integer cart_detail_id);
}
