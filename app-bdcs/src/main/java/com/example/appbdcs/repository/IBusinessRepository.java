package com.example.appbdcs.repository;

import com.example.appbdcs.model.Business;
import com.example.appbdcs.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    // Tìm doanh nghiệp theo ID
    @Query(value = "SELECT * FROM business WHERE business_id = ?1", nativeQuery = true)
    Optional<Business> findBusinessById(Integer businessId);

    // Lấy danh sách tất cả các doanh nghiệp
    @Query(value = "SELECT * FROM business", nativeQuery = true)
    List<Business> findAll();

    // Xóa doanh nghiệp theo id
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM business WHERE business_id = ?1", nativeQuery = true)
    void deleteById(Integer businessId);

    // Kiểm tra sự tồn tại của doanh nghiệp theo id
    @Query(value = "SELECT EXISTS(SELECT 1 FROM business WHERE business_id = ?1)", nativeQuery = true)
    boolean existsById(Integer id);

    // Tìm kiếm doanh nghiệp theo tên
    @Query(value = "SELECT * FROM business WHERE LOWER(business_name) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Business> searchByName(String name);

    // Phân trang danh sách doanh nghiệp
    @Query(value = "SELECT * FROM business LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Business> findBusinessesPaginated(int limit, int offset);
}
