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

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Optional<Card> getCardById(@PathVariable("id") long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.getCardById(id);
    }

    @GetMapping("/user/{userId}")
    public Optional<Card> getCardByUser(@PathVariable("userId") long userId) {
        return cardService.getCardByOwner(userId);
    }

    @PostMapping
    public Card addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @PutMapping("/{id}")
    public Card updateCard(@RequestBody Card card, long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return cardService.updateCard(card, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable("id") long id) {
        if (!cardService.getCardById(id).isPresent()) {
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        cardService.deleteCard(id);
    }
}
