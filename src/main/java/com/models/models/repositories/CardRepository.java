package com.models.models.repositories;

import com.models.models.allModels.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card save(Card card);
}
