package com.example.electric.controller;

import com.example.electric.respository.CardRepository;
import com.example.electric.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;
}
