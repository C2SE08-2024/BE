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
    @Query(value = "UPDATE cart SET receiver_name = :name, receiver_address = :address, receiver_phone = :phone, " +
            "receiver_email = :email WHERE cart_id = :id",
            nativeQuery = true)
    void updateCart(@Param("id") Integer id,
                    @Param("name") String name,
                    @Param("address") String address,
                    @Param("phone") String phone,
                    @Param("email") String email);

    @Query(value = "SELECT c.cart_id, c.receiver_address, c.receiver_email, c.receiver_name, c.receiver_phone " +
            "FROM cart c " +
            "JOIN student s ON s.cart_id = c.cart_id " +
            "JOIN account a ON a.account_id = s.account_id " +
            "WHERE a.username = :username", nativeQuery = true)
    Optional<Cart> findCartByUsername(@Param("username") String username);
}

