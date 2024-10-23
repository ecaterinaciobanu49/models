package com.models.models.repositories;

import com.models.models.allModels.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {

    Card save(Card card);
}
