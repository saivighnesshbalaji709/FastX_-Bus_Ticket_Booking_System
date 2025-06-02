package com.hexaware.fastx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hexaware.fastx.dto.PaymentDTO;
import com.hexaware.fastx.service.IPaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> addPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO saved = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable int paymentId,
                                                    @Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updated = paymentService.updatePayment(paymentId, paymentDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable int paymentId) {
        PaymentDTO payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserId(@PathVariable int userId) {
        List<PaymentDTO> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }
}
