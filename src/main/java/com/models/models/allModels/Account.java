package com.models.models.allModels;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "balance")
    private double balance;

    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
