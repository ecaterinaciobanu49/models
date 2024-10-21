package com.models.models.allModels;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column(name = "name")
    private String name;
    @Column(name = "sur_name")
    private String surName;
    @Column(name = "customer_city")
    private String customerCity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private Account account;

}
