package com.example.appbdcs.controller;


import com.example.appbdcs.model.Notification;
import com.example.appbdcs.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @PostMapping("/create")
    public void createNotification(@RequestParam String title,
                                   @RequestParam String content,
                                   @RequestParam Integer accountId) {
        notificationService.createNotification(title, content, accountId);
    }

    // Lấy tất cả thông báo của một tài khoản
    @GetMapping("/{accountId}")
    public List<Notification> getNotifications(@PathVariable Integer accountId) {
        return notificationService.getNotificationsByAccount(accountId);
    }

    // Lấy thông báo chưa đọc của một tài khoản
    @GetMapping("/{accountId}/unread")
    public List<Notification> getUnreadNotifications(@PathVariable Integer accountId) {
        return notificationService.getUnreadNotificationsByAccount(accountId);
    }

    // Đánh dấu thông báo là đã đọc
    @PutMapping("/{notificationId}/mark-as-read")
    public void markAsRead(@PathVariable Integer notificationId) {
        notificationService.markAsRead(notificationId);
    }

    // Xóa thông báo
    @DeleteMapping("/{notificationId}")
    public void deleteNotification(@PathVariable Integer notificationId) {
        notificationService.deleteNotification(notificationId);
    }

    // Lấy thông báo mới nhất của một tài khoản
    @GetMapping("/{accountId}/latest")
    public Optional<Notification> getLatestNotification(@PathVariable Integer accountId) {
        return notificationService.getLatestNotification(accountId);
    }
}
