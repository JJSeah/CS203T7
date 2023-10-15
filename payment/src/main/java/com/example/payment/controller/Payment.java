package com.example.payment.controller;

import com.example.payment.models.PaymentDetails;
import com.example.payment.models.PaymentStatus;
import com.example.payment.models.PaymentType;
import com.example.payment.service.PaymentService;
import com.example.payment.service.PaymentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class Payment {

    @Autowired
    private PaymentServiceImpl paymentservice;

    @GetMapping("/all")
    public List<PaymentDetails> all() {
        return paymentservice.getAllPayments();
    }

    @GetMapping("/status")
    public String status() {
        return "OK it is working";
    }

    @GetMapping("/status/{referenceNo}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> getStatus(@PathVariable("referenceNo") String _referenceNo,
                                                        HttpServletRequest request) throws Exception {
        HashMap<String,Object> status = new HashMap<String,Object>();
        status.put("Code", 200);
        status.put("Status", true);
        status.put("ReferenceNo", _referenceNo);
        status.put("Message","Payment Pending.");
        return ResponseEntity.ok(status);
    }
    @PostMapping("/process")
    public ResponseEntity<PaymentStatus> processPayments(@RequestBody PaymentDetails _payDetails) {
        System.out.println(_payDetails);
        String transactionId = UUID.randomUUID().toString();
        _payDetails.setTransactionId(transactionId);
        _payDetails.setTransactionDate(LocalDateTime.now());
        try {
            _payDetails.setPaymentStatus("Accepted");
            paymentservice.addPayment(_payDetails);
            PaymentStatus ps = new PaymentStatus(
                    transactionId,
                    LocalDateTime.now(),
                    "Accepted",
                    UUID.randomUUID().toString(),
                    LocalDateTime.now(),
                    PaymentType.CREDIT_CARD);
            return ResponseEntity.ok(ps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/cancel/{referenceNo}")
    public ResponseEntity<Map<String,Object>> cancel(@PathVariable("referenceNo") String _referenceNo) {
        HashMap<String,Object> status = new HashMap<String,Object>();
        status.put("Code", 200);
        status.put("Status", true);
        status.put("ReferenceNo", _referenceNo);
        status.put("Message","Payment Cancelled.");

        PaymentDetails obj = paymentservice.getPaymentByTransactionId(_referenceNo);
        obj.setPaymentStatus("Cancelled");
        paymentservice.updatePayment(obj);

        return ResponseEntity.ok(status);
    }
    @GetMapping("/update/{referenceNo}")
    public ResponseEntity<Map<String,Object>> updatePayment(@PathVariable("referenceNo") String _referenceNo) {
        HashMap<String,Object> status = new HashMap<String,Object>();
        status.put("Code", 200);
        status.put("Status", true);
        status.put("ReferenceNo", _referenceNo);
        status.put("Message","Payment Updated.");

        PaymentDetails obj = paymentservice.getPaymentByTransactionId(_referenceNo);
        System.out.println(obj);
        obj.setPaymentStatus("Updated");
        paymentservice.updatePayment(obj);

        return ResponseEntity.ok(status);
    }
}
