package com.example.appbdcs.repository;

import com.example.appbdcs.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select account_id, username, password, email, is_enable " +
            "from account " +
            "where username = :username and is_enable = true", nativeQuery = true)
    Optional<Account> findAccountByUsername(@Param("username") String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Account findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE account SET password = :password " +
            "WHERE (is_enable = true) AND (user_name = :username)", nativeQuery = true)
    void changePassword(@Param("username") String username, @Param("password") String pass);
}
