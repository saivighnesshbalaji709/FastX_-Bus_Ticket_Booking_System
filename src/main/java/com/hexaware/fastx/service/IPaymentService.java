package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.PaymentDTO;
import java.util.List;

public interface IPaymentService {
    List<PaymentDTO> getAllPayments();
    PaymentDTO getPaymentById(int id);
    List<PaymentDTO> getPaymentsByUserId(int userId);
    PaymentDTO updatePayment(int id, PaymentDTO dto);
    void deletePayment(int id);
    PaymentDTO createPayment(PaymentDTO dto);
}
