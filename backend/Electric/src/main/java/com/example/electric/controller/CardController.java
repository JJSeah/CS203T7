package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Card;
import com.example.electric.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * Retrieves a list of all available cards from the database.
     *
     * This endpoint returns a list of all cards currently stored in the system.
     * If no cards are available, an empty list will be returned.
     *
     * @return A list of cards, which may be empty if no cards are found.
     */
    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    /**
     * Retrieve a card by its unique identifier.
     *
     * This endpoint retrieves a card from the system based on its unique identifier (ID).
     * If a card with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id The unique identifier of the card to retrieve.
     * @return An Optional containing the retrieved card, or an empty Optional if the card is not found.
     * @throws ObjectNotFoundException If no card with the given ID is found.
     */
    @GetMapping("/{id}")
    public Optional<Card> getCardById(@PathVariable("id") long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.getCardById(id);
    }
    /**
     * Retrieve a card owned by a specific user.
     *
     * This endpoint retrieves a card that is owned by a user with the specified user ID.
     * If a card owned by the user is not found, an empty Optional will be returned.
     *
     * @param userId The unique identifier of the user who owns the card.
     * @return An Optional containing the card owned by the user, or an empty Optional if not found.
     */
    @GetMapping("/user/{userId}")
    public Optional<Card> getCardByUser(@PathVariable("userId") long userId) {
        return cardService.getCardByOwner(userId);
    }

    /**
     * Add a new card to the system.
     *
     * This endpoint allows the addition of a new card to the system. The provided card object
     * should contain the necessary details for creating the card. Upon successful addition,
     * the newly created card is returned.
     *
     * @param card The card object to be added.
     * @return The newly created card.
     */
    @PostMapping
    public Card addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }
    /**
     * Update an existing card with the provided information.
     *
     * This endpoint allows the update of an existing card identified by its unique identifier (ID).
     * The provided card object should contain the updated details for the card. If a card with
     * the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id The unique identifier of the card to update.
     * @param card The updated card object containing new information.
     * @return The updated card.
     * @throws ObjectNotFoundException If no card with the given ID is found.
     */
    @PutMapping("/{id}")
    public Card updateCard(@RequestBody Card card, long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.updateCard(card, id);
    }
    /**
     * Delete a card by its unique identifier.
     *
     * This endpoint allows the deletion of a card from the system based on its unique identifier (ID).
     * If a card with the specified ID is not found, it will result in an ObjectNotFoundException.
     *
     * @param id The unique identifier of the card to delete.
     * @throws ObjectNotFoundException If no card with the given ID is found.
     */
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable("id") long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        cardService.deleteCard(id);
    }
}
