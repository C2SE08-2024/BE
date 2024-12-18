package com.example.appbdcs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    private String title; // Tiêu đề thông báo

    private String content; // Nội dung thông báo

    private LocalDateTime createdAt; // Thời gian tạo thông báo

    private Boolean isRead = false; // Trạng thái đã đọc (mặc định là chưa đọc)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false) // Khóa ngoại liên kết đến Account
    private Account account;

    // Constructor tiện ích để tạo Notification
    public Notification(String title, String content, Account account) {
        this.title = title;
        this.content = content;
        this.account = account;
        this.createdAt = LocalDateTime.now(); // Lấy thời gian hiện tại làm thời gian tạo
    }
}
