package com.example.payment.service;

import com.example.payment.controller.Payment;
import com.example.payment.models.PaymentDetails;
import com.example.payment.respository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.payment.models.PaymentType.CREDIT_CARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;


    @Test
    void getAllPayments() {
        List<PaymentDetails> payments = new ArrayList<>();
        payments.add(new PaymentDetails(1L, "1234567890123456", LocalDateTime.now(), 22.0, CREDIT_CARD, "COMPLETED"));
        payments.add(new PaymentDetails(2L, "2224567890123456", LocalDateTime.now(), 22.0, CREDIT_CARD, "COMPLETED"));
        when(paymentRepository.findAll()).thenReturn(payments);

        List<PaymentDetails> result = paymentService.getAllPayments();

        assertEquals(payments, result);
    }

    @Test
    void addPayment() {
        PaymentDetails payment = new PaymentDetails(1L, "1234567890123456", LocalDateTime.now(), 22.0, CREDIT_CARD, "COMPLETED");
        when(paymentRepository.save(payment)).thenReturn(payment);

        PaymentDetails result = paymentService.addPayment(payment);

        verify(paymentRepository).save(payment);
        assertEquals(payment, result);
    }

    @Test
    void updatePayment() {
        PaymentDetails payment = new PaymentDetails(1L, "1234567890123456", LocalDateTime.now(), 22.0, CREDIT_CARD, "COMPLETED");
        when(paymentRepository.save(payment)).thenReturn(payment);

        PaymentDetails result = paymentService.updatePayment(payment);

        verify(paymentRepository).save(payment);
        assertEquals(payment, result);
    }

    @Test
    void getPaymentByTransactionId() {
        String transactionId = "1234567890123456";
        PaymentDetails payment = new PaymentDetails(1L, "1234567890123456", LocalDateTime.now(), 22.0, CREDIT_CARD, "COMPLETED");
        when(paymentRepository.findPaymentByTransactionId(transactionId)).thenReturn(payment);

        PaymentDetails result = paymentService.getPaymentByTransactionId(transactionId);

        assertEquals(payment, result);
    }
}