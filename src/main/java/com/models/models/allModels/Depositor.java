package com.models.models.allModels;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Depositor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long depositerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private Account account;
}
