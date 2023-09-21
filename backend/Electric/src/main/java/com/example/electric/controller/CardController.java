package com.example.electric.controller;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Card;
import com.example.electric.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Card", description = "Get Card using ID",tags = {"Card"})
    public Optional<Card> getCardById(@PathVariable("id") long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.getCardById(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get User's Card", description = "Get a list of User's Card from UserID",tags = {"Card"})
    public Optional<Card> getCardByUser(@PathVariable("userId") long userId) {
        if (!cardService.getCardById(userId).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.getCardByOwner(userId);
    }

    @PostMapping
    @Operation(summary = "Add Card", description = "Add Card using ID",tags = {"Card"})
    public Card addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Card", description = "Update Card using ID",tags = {"Card"})
    public Card updateCard(@RequestBody Card card, long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.updateCard(card, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Card", description = "Delete Card using ID",tags = {"Card"})
    public void deleteCard(@PathVariable("id") long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        cardService.deleteCard(id);
    }
}
