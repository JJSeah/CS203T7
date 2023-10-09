package com.example.electric.service.inter;

import com.example.electric.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardServiceInter {
    List<Card> getAllCards();

    Optional<Card> getCardById(long id);

    Optional<Card> getCardByUserId(long userId);

    Card addCard(Card card);

    Card updateCard(Card updatedCard, long id);

    void deleteCard(long id);
}
