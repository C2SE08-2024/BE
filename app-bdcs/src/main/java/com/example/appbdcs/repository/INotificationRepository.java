package com.example.appbdcs.repository;

import com.example.appbdcs.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface INotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM notification WHERE account_id = :accountId", nativeQuery = true)
    List<Notification> findAllNotificationsByAccountId(@Param("accountId") Integer accountId);

    @Query(value = "SELECT * FROM notification WHERE account_id = :accountId AND is_read = false", nativeQuery = true)
    List<Notification> findUnreadNotificationsByAccountId(@Param("accountId") Integer accountId);

    @Modifying
    @Query(value = "UPDATE notification SET is_read = true WHERE notification_id = :notificationId", nativeQuery = true)
    void markNotificationAsRead(@Param("notificationId") Integer notificationId);

    @Modifying
    @Query(value = "DELETE FROM notification WHERE notification_id = :notificationId", nativeQuery = true)
    void deleteNotificationById(@Param("notificationId") Integer notificationId);

    @Modifying
    @Query(value = "INSERT INTO notification (title, content, created_at, is_read, account_id) VALUES (:title, :content, :createdAt, :isRead, :accountId)", nativeQuery = true)
    void insertNotification(@Param("title") String title,
                            @Param("content") String content,
                            @Param("createdAt") LocalDateTime createdAt,
                            @Param("isRead") Boolean isRead,
                            @Param("accountId") Integer accountId);

    @Query(value = "SELECT * FROM notification WHERE notification_id = :notificationId", nativeQuery = true)
    Optional<Notification> findNotificationById(@Param("notificationId") Integer notificationId);

    @Query(value = "SELECT * FROM notification WHERE account_id = :accountId ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Optional<Notification> findLatestNotificationByAccountId(@Param("accountId") Integer accountId);

}
