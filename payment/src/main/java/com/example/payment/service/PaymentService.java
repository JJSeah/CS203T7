package com.example.payment.service;

import com.example.payment.models.PaymentDetails;

import java.util.List;

public interface PaymentService {
    List<PaymentDetails> getAllPayments();
    PaymentDetails addPayment(PaymentDetails payment);
    PaymentDetails updatePayment(PaymentDetails payment);
    PaymentDetails getPaymentByTransactionId(String transactionId);


}
