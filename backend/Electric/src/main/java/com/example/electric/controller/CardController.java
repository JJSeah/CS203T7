package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Card;
import com.example.electric.model.User;
import com.example.electric.service.CardService;
import com.example.electric.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/card")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;

    /**
     * Payment Details with Card Details
     *
     * @param _txId
     * @param _txDate
     * @param _orderValue
     * @param _payType
     * @param _card
     */

    @GetMapping("/status")
    public String status() {
        String URL = "http://localhost:9090/payment/status";
        String obj=  new RestTemplate().getForObject(URL, String.class);
        return obj;
    }

    /**
     * Payment Details with Card Details
     * @param id -> user id
     * @param orderValue -> cost
     * @return -> transaction id
     */
    @GetMapping("/process/{id}/{orderValue}")
    public String process(@PathVariable("id") long id, @PathVariable("orderValue") double orderValue) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.processPayment(id,orderValue);
    }


    /**
     * Retrieves a list of all available cards from the database.
     *
     * This endpoint returns a list of all cards currently stored in the system.
     * If no cards are available, an empty list will be returned.
     *
     * @return A list of cards, which may be empty if no cards are found.
     */
    @GetMapping
    @Operation(summary = "Get All Cards", description = "Get All Cards",tags = {"Card"})
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
    @Operation(summary = "Get Card", description = "Get Card using ID",tags = {"Card"})
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
    @Operation(summary = "Get User's Card", description = "Get a list of User's Card from UserID",tags = {"Card"})
    public Optional<Card> getCardByUser(@PathVariable("userId") long userId) {
        if (!cardService.getCardById(userId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.getCardByUserId(userId);
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
    @PostMapping("/add/{userId}")
    @Operation(summary = "Add Card", description = "Add Card using ID",tags = {"Card"})
    public Card addCard(@PathVariable("userId") long userId,@RequestBody Card card) {
        if(card.getNumber().length() != 16){
            System.out.println(card.getExpiry());
            System.out.println(java.sql.Date.valueOf(LocalDate.now()));
            System.out.println(card.getExpiry().after(java.sql.Date.valueOf(LocalDate.now())));
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        card.setUser(user);
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
    @Operation(summary = "Update Card", description = "Update Card using ID",tags = {"Card"})
    public Card updateCard(@RequestBody Card card, long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        if(card.getNumber().length() != 16 || card.getExpiry().before(java.sql.Date.valueOf(LocalDate.now()))){
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
    @Operation(summary = "Delete Card", description = "Delete Card using ID",tags = {"Card"})
    public void deleteCard(@PathVariable("id") long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        cardService.deleteCard(id);
    }
}
