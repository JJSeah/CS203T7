package com.example.payment.respository;

import com.example.payment.models.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {
    public PaymentDetails findPaymentByTransactionId(String transactionId);
}
