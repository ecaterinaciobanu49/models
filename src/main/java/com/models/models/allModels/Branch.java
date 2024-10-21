package com.models.models.allModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long branchId;

    private String branchName;

    private Double assets;
}
