package com.example.payment.service;

import com.example.payment.models.PaymentDetails;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentDetails> getAllPayments();
    Optional<PaymentDetails> getPaymentById(long id);
    PaymentDetails getPaymentByTransactionId(String transactionId);
    PaymentDetails addPayment(PaymentDetails payment);
    PaymentDetails updatePayment(PaymentDetails payment);


}
