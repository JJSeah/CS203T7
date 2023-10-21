package com.example.electric.dto;

public class PaymentCard {
    private String cardNumber;
    private String holderName;
    private int expiryMonth;
    private int expiryYear;


    public PaymentCard(String cardNumber, String holderName, int expiryMonth, int expiryYear) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }

    public PaymentCard() {

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

}
