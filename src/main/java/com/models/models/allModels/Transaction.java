package com.models.models.allModels;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Column(name = "sum")
    private double sum;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
