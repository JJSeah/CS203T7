package com.example.payment.controller;

import com.example.payment.models.PaymentDetails;
import com.example.payment.models.PaymentType;
import com.example.payment.service.PaymentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        // Initialize any required setup for your tests
    }

    @Test
    public void testProcessPayment() throws Exception {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setOrderValue(100.0); // Set any required fields
        paymentDetails.setPaymentType(PaymentType.CREDIT_CARD);

        mockMvc.perform(MockMvcRequestBuilders.post("/payment/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(paymentDetails)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCancelPayment() throws Exception {
        // First, create a payment and retrieve its referenceNo
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(PaymentType.CREDIT_CARD);

        PaymentDetails createdPayment = paymentService.addPayment(paymentDetails);
        String referenceNo = createdPayment.getTransactionId();

        mockMvc.perform(MockMvcRequestBuilders.get("/payment/cancel/" + referenceNo))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdatePayment() throws Exception {
        // First, create a payment and retrieve its referenceNo
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setOrderValue(100.0); // Set any required fields
        paymentDetails.setPaymentType(PaymentType.CREDIT_CARD);

        PaymentDetails createdPayment = paymentService.addPayment(paymentDetails);
        String referenceNo = createdPayment.getTransactionId();

        mockMvc.perform(MockMvcRequestBuilders.get("/payment/update/" + referenceNo))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Utility method to convert an object to a JSON string
    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
