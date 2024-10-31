package com.example.appspringbootbdcs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Cart cart;
    private Integer totalAmount;

    @OneToMany
    private Set<CartDetail> cartDetails = new LinkedHashSet<>();
    private String tnxRef;

    private boolean isPaid;

    @JsonBackReference
    @ManyToMany(mappedBy = "payments")
    private Set<CartDetail> cartDetail = new LinkedHashSet<>();

}
