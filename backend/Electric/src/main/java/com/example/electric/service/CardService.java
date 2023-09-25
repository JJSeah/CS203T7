package com.example.electric.service;

import com.example.electric.model.Appointment;
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

    public Optional<Card> getCardByUserId(long userId) {
        return cardRepository.findCardByUserId(userId);
    }

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Card updatedCard, long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            // Update the appointment fields as needed
            if (updatedCard.getName() != null) {
                card.setName(updatedCard.getName());
            }
            if (updatedCard.getNumber() != 0L) {
                card.setNumber(updatedCard.getNumber());
            }
            if (updatedCard.getExpiry() != null) {
                card.setExpiry(updatedCard.getExpiry());
            }
            if (updatedCard.getUser() != null) {
                card.setUser(updatedCard.getUser());
            }
            return cardRepository.save(updatedCard);
        } else {
            return null; // Card not found
        }
    }

    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }
}
