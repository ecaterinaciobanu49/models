package com.models.models.allModels;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "cvv")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
