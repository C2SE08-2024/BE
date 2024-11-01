package com.example.appbdcs.repository;

import com.example.appbdcs.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ICartRepository extends JpaRepository<Cart, Integer> {
    @Modifying
    @Query(value = "INSERT INTO cart (receiver_name, receiver_address, receiver_phone, receiver_email) " +
            "VALUES(:receiver_name, :receiver_address, :receiver_phone, :receiver_email)", nativeQuery = true)
    void insertCart(@Param("receiver_name") String name,
                    @Param("receiver_address") String address,
                    @Param("receiver_phone") String phone,
                    @Param("receiver_email") String email);

//    @Modifying
//    @Query(value = "",
//            nativeQuery = true)
//    void updateCart(@Param("receiver_id") Integer id,
//                    @Param("receiver_name") String name,
//                    @Param("receiver_address") String address,
//                    @Param("receiver_phone") String phone,
//                    @Param("receiver_email") String email);

    @Query(value = "SELECT cart_id, receiver_address, receiver_email, receiver_name, receiver_phone " +
            "FROM cart ORDER BY cart_id DESC LIMIT 1", nativeQuery = true)
    Optional<Cart> findLastCart();

//    @Query(value = "", nativeQuery = true)
//    Optional<Cart> findCartByUsername(@Param("username") String username);
}

