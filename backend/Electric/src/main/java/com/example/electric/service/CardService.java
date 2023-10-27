package com.example.electric.service;

import com.example.electric.dto.Payment;
import com.example.electric.dto.PaymentCard;
import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Card;
import com.example.electric.respository.CardRepository;
import com.example.electric.service.inter.CardServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CardService implements CardServiceInter {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(long id) {
        return cardRepository.findById(id);
    }

    public List<Card> getCardByUserId(long userId) {
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
            if (updatedCard.getNumber() != null) {
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

    public boolean checkCardNumber(String cardNumber) {
        if (cardNumber.length() != 16) {
            return false;
        }
        return true;
    }

    public String processPayment(long id, double orderValue, long cardId) {

        List<Card> card = getCardByUserId(id);
        PaymentCard paymentCard = new PaymentCard();
        for (Card c : card) {
            if (c.getId() == cardId) {
                if (c.getNumber().length() != 16) {
                    throw new ObjectNotFoundException(ErrorCode.E2002);
                }
                paymentCard.setCardNumber(c.getNumber());
                paymentCard.setExpiryMonth(c.getExpiry().getMonth());
                paymentCard.setExpiryYear(c.getExpiry().getYear());
                paymentCard.setHolderName(c.getName());
            }
        }

        Payment payment = new Payment(orderValue,"CREDIT_CARD",paymentCard);
        System.out.println(payment.getCardDetails().getCardNumber());
        String URL = "http://3.26.230.241:9090/payment/process";
        Map<String, Object> response = new RestTemplate().postForObject(URL, payment, Map.class);
        return response.get("transactionId").toString();
    }
}
