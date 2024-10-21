package com.models.models.allModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long borrowerId;

    //TODO: add loan

}
