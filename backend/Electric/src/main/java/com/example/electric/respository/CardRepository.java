package com.example.electric.respository;

import com.example.electric.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    public List<Card> findCardByUserId(long userId);

}
