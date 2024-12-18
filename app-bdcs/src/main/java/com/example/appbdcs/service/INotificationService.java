package com.example.appbdcs.service;

import com.example.appbdcs.model.Notification;

import java.util.List;
import java.util.Optional;

public interface INotificationService {

    void createNotification(String title, String content, Integer accountId);

    List<Notification> getNotificationsByAccount(Integer accountId);

    List<Notification> getUnreadNotificationsByAccount(Integer accountId);

    void markAsRead(Integer notificationId);

    void deleteNotification(Integer notificationId);

    Optional<Notification> getLatestNotification(Integer accountId);
}
