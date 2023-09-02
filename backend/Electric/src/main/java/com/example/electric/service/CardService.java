package com.example.electric.service;

import com.example.electric.model.Car;
import com.example.electric.model.Card;
import com.example.electric.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(long id) {
        return cardRepository.findById(id);
    }

    public Optional<Card> getCardByOwner(long ownderId) {
        return cardRepository.findCardByOwnerId(ownderId);
    }

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card updatedCard, long id) {
        if (!cardRepository.existsById(id)) {
            return null;
        }

        updatedCard.setId(id);
        return cardRepository.save(updatedCard);
    }

    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }
}
