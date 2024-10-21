package com.models.models.allModels;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "balance")
    private double balance;
}
