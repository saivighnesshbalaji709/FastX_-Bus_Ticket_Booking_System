package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.Payment;
import java.util.List;

public interface IPaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(int id);
    List<Payment> getPaymentsByUserId(int userId);
    Payment createPayment(Payment payment);
    Payment updatePayment(int id, Payment payment);
    void deletePayment(int id);
}
