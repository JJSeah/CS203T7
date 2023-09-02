package com.example.electric.controller;

import com.example.electric.model.Card;
import com.example.electric.model.User;
import com.example.electric.respository.CardRepository;
import com.example.electric.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Optional<Card> getCardById(@PathVariable("id") long id) {
        return cardService.getCardById(id);
    }

    @GetMapping("/user/{userId}")
    public Optional<Card> getCardByUser(@PathVariable("userId") long userId) {
        return cardService.getCardByOwner(userId);
    }

    @PostMapping("/add")
    public Card addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @PutMapping("/update/{id}")
    public Card updateCard(@RequestBody Card card, long id) {
        return cardService.updateCard(card, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCard(@PathVariable("id") long id) {
        cardService.deleteCard(id);
    }
}
