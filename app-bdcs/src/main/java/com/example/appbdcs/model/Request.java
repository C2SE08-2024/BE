package com.example.appbdcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;
    private LocalDateTime requestDate; // Thời gian gửi yêu cầu
    private Boolean isAccepted; // Trạng thái yêu cầu (true: chấp nhận, false: từ chối)
    private String reason; // Lý do (nếu có)
    // Trạng thái yêu cầu xem thông tin: true (được phép xem), false (không được phép)
    private Boolean canView = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
}
