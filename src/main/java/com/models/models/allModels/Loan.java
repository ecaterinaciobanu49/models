package com.models.models.allModels;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "outstanding_amoud")
    private double outstandingAmount;

    @Column(name = "percent")
    private int percent;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
