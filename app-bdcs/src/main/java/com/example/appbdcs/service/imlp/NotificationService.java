package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Notification;
import com.example.appbdcs.repository.INotificationRepository;
import com.example.appbdcs.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class NotificationService implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;

    // Tạo thông báo mới
    public void createNotification(String title, String content, Integer accountId) {
        LocalDateTime createdAt = LocalDateTime.now();
        Boolean isRead = false;
        notificationRepository.insertNotification(title, content, createdAt, isRead, accountId);
    }

    // Lấy tất cả thông báo của một tài khoản
    public List<Notification> getNotificationsByAccount(Integer accountId) {
        return notificationRepository.findAllNotificationsByAccountId(accountId);
    }

    // Lấy thông báo chưa đọc của một tài khoản
    public List<Notification> getUnreadNotificationsByAccount(Integer accountId) {
        return notificationRepository.findUnreadNotificationsByAccountId(accountId);
    }

    // Đánh dấu thông báo là đã đọc
    public void markAsRead(Integer notificationId) {
        notificationRepository.markNotificationAsRead(notificationId);
    }

    // Xóa thông báo
    public void deleteNotification(Integer notificationId) {
        notificationRepository.deleteNotificationById(notificationId);
    }

    // Lấy thông báo mới nhất của một tài khoản
    public Optional<Notification> getLatestNotification(Integer accountId) {
        return notificationRepository.findLatestNotificationByAccountId(accountId);
    }
}
