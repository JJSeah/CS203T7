package com.example.payment.service;

import com.example.payment.controller.Payment;
import com.example.payment.models.PaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.payment.respository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentDetails> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<PaymentDetails> getPaymentById(long id) {
        return paymentRepository.findById(id);
    }

    public PaymentDetails getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findPaymentByTransactionId(transactionId);
    }

    public PaymentDetails addPayment(PaymentDetails payment) {
        return paymentRepository.save(payment);
    }

//    update status
    public PaymentDetails updatePayment(PaymentDetails payment) {
        return paymentRepository.save(payment);
    }




}
