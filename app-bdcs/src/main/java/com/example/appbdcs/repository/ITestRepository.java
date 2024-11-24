package com.example.appbdcs.repository;

import com.example.appbdcs.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ITestRepository extends JpaRepository<Test, Integer> {
}
