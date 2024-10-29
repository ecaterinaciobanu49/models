package com.models.models.repositories;

import com.models.models.allModels.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card save(Card card);

    Card findByCardId(Long cardId);

    @Query("FROM Card AS card LEFT JOIN card.customer AS c WHERE c.customerId = ?1")
    List<Card> findAllCardsByCustomerId(Long customerId);
}
