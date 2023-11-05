package com.example.payment.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.payment.models.PaymentDetails;
import com.example.payment.respository.PaymentRepository;
import com.example.payment.service.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void testGetAllPayments() {
        List<PaymentDetails> payments = new ArrayList<>();
        // Add some test data to the payments list

        when(paymentRepository.findAll()).thenReturn(payments);

        List<PaymentDetails> result = paymentService.getAllPayments();

        assertEquals(payments, result);
    }

    @Test
    public void testGetPaymentById() {
        long paymentId = 1;
        PaymentDetails payment = new PaymentDetails(); // Create a PaymentDetails object with ID 1

        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

        Optional<PaymentDetails> result = paymentService.getPaymentById(paymentId);

        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
    }

    @Test
    public void testGetPaymentByTransactionId() {
        String transactionId = "123456";
        PaymentDetails payment = new PaymentDetails(); // Create a PaymentDetails object with the specified transactionId

        when(paymentRepository.findPaymentByTransactionId(transactionId)).thenReturn(payment);

        PaymentDetails result = paymentService.getPaymentByTransactionId(transactionId);

        assertNotNull(result);
        assertEquals(payment, result);
    }

    @Test
    public void testAddPayment() {
        PaymentDetails payment = new PaymentDetails(); // Create a PaymentDetails object for testing

        when(paymentRepository.save(payment)).thenReturn(payment);

        PaymentDetails result = paymentService.addPayment(payment);

        assertNotNull(result);
        assertEquals(payment, result);
    }

    @Test
    public void testUpdatePayment() {
        PaymentDetails payment = new PaymentDetails(); // Create a PaymentDetails object for testing

        when(paymentRepository.save(payment)).thenReturn(payment);

        PaymentDetails result = paymentService.updatePayment(payment);

        assertNotNull(result);
        assertEquals(payment, result);
    }
}
