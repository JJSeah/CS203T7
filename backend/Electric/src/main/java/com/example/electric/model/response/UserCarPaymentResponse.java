package com.example.electric.model.response;

import com.example.electric.model.Car;
import com.example.electric.model.Card;
import com.example.electric.model.User;

import java.util.List;
import java.util.Optional;

public class UserCarPaymentResponse {
    private User user;
    private List<Car> car;
    private Optional<Card> card;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public Optional<Card> getCard() {
        return card;
    }

    public void setCard(Optional<Card> card) {
        this.card = card;
    }

    // Constructors, getters, setters
}

