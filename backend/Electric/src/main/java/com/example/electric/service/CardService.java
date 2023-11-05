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

    /**
     * Get All Cards
     *
     * This method retrieves a list of all cards stored in the system.
     *
     * @return A list of all cards in the system.
     */
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    /**
     * Get Card by ID
     *
     * This method retrieves a card by its unique identifier (ID).
     *
     * @param id The unique identifier of the card to retrieve.
     * @return An optional containing the card, or empty if the card is not found.
     */
    public Optional<Card> getCardById(long id) {
        return cardRepository.findById(id);
    }

    /**
     * Get Cards by User ID
     *
     * This method retrieves a list of cards associated with a specific user based on their unique user identifier.
     *
     * @param userId The unique identifier of the user.
     * @return A list of cards associated with the user.
     */
    public List<Card> getCardByUserId(long userId) {
        return cardRepository.findCardByUserId(userId);
    }

    /**
     * Add Card
     *
     * This method allows the addition of a new card to the system.
     *
     * @param card The card to be added to the system.
     * @return The newly added card.
     */
    public Card addCard(Card card) {
        return cardRepository.save(card);
    }

    /**
     * Update Card
     *
     * This method allows the update of an existing card based on the provided updated card details.
     * It identifies the card by its unique identifier (ID) and updates its fields as needed.
     *
     * @param updatedCard The updated card details.
     * @param id The unique identifier of the card to update.
     * @return The updated card after the changes are applied, or null if the card is not found.
     */
    public Card updateCard(Card updatedCard, long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            // Update the appointment fields as needed
            if (updatedCard.getName() != null) card.setName(updatedCard.getName());
            if (updatedCard.getNumber() != null)card.setNumber(updatedCard.getNumber());
            if (updatedCard.getExpiry() != null) card.setExpiry(updatedCard.getExpiry());
            if (updatedCard.getUser() != null)card.setUser(updatedCard.getUser());
            cardRepository.save(card);
            return card;
        } else {
            return null; // Card not found
        }
    }

    /**
     * Delete Card
     *
     * This method allows the deletion of a card based on its unique identifier (ID).
     *
     * @param id The unique identifier of the card to delete.
     */
    public void deleteCard(long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
        }
    }

    /**
     * Check Card Number Validity
     *
     * This method checks the validity of a card number. It checks if the card number has the required length.
     *
     * @param cardNumber The card number to validate.
     * @return true if the card number has the required length; otherwise, false.
     */
    public boolean checkCardNumber(String cardNumber) {
        if (cardNumber.length() != 16) {
            return false;
        }
        return true;
    }

    /**
     * Process Payment
     *
     * This method processes a payment using a specified card and order value. It validates the card details,
     * constructs a payment object, and triggers a payment process via an external API.
     *
     * @param id The unique identifier of the user making the payment.
     * @param orderValue The order value to be charged.
     * @param cardId The unique identifier of the card to use for payment.
     * @return The transaction ID of the processed payment.
     * @throws ObjectNotFoundException If the card number is invalid.
     */
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
        String URL = "http://13.239.86.17:9090/payment/process";
        Map<String, Object> response = new RestTemplate().postForObject(URL, payment, Map.class);
        return response.get("transactionId").toString();
    }
}
