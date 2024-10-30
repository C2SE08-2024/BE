package com.example.appspringbootbdcs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private Integer cartId;
    private Integer totalAmount;

    @OneToMany
    private Set<CartDetail> cartDetails = new LinkedHashSet<>();
    private String tnxRef;

    private boolean isPaid;

    @JsonBackReference
    @ManyToMany(mappedBy = "payments")
    private Set<CartDetail> cartDetail = new LinkedHashSet<>();

    public Payment(Integer paymentId, Integer cartId, Integer totalAmount, String tnxRef, boolean isPaid) {
        this.paymentId = paymentId;
        this.cartId = cartId;
        this.totalAmount = totalAmount;
        this.tnxRef = tnxRef;
        this.isPaid = isPaid;

    }
}
