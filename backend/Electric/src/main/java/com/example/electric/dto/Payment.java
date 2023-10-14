package com.example.electric.dto;

public class Payment {

    private double orderValue;
    private String paymentType;
    private PaymentCard cardDetails;

    public Payment(double orderValue, String paymentType, PaymentCard cardDetails) {
        this.orderValue = orderValue;
        this.paymentType = paymentType;
        this.cardDetails = cardDetails;
    }


    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    public PaymentCard getCardDetails() {
        return cardDetails;
    }
    public void setCardDetails(PaymentCard cardDetails) {
        this.cardDetails = cardDetails;
    }
}

