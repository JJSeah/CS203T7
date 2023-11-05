package com.example.electric.model.response;

import com.example.electric.model.Car;
import com.example.electric.model.Card;
import com.example.electric.model.User;

import java.util.List;

public class UserCarPaymentResponse {
    private User user;

    private List<Car> car;

    private List<Card> card;

    public UserCarPaymentResponse() {

    }

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

    public List<Card> getCard() {
        return card;
    }

    public void setCard(List <Card> card) {
        this.card = card;
    }


}

