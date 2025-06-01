package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.Payment;
import com.hexaware.fastx.exception.PaymentNotFoundException;
import com.hexaware.fastx.repository.PaymentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository repo;

    @Override
    public List<Payment> getAllPayments() {
        logger.info("Fetching all payments from database");
        return repo.getAllPayments();
    }

    @Override
    public Payment getPaymentById(int id) {
        logger.info("Fetching payment with ID: {}", id);
        return repo.findById(id).orElseThrow(() -> {
            logger.error("Payment not found with ID: {}", id);
            return new PaymentNotFoundException("Payment not found with ID: " + id);
        });
    }

    @Override
    public Payment createPayment(Payment payment) {
        logger.info("Creating new payment for booking ID: {}", 
                    payment.getBookingId() != null ? payment.getBookingId().getBookingId() : "N/A");
        Payment savedPayment = repo.save(payment);
        logger.info("Payment created with ID: {}", savedPayment.getPaymentId());
        return savedPayment;
    }

    @Override
    public Payment updatePayment(int id, Payment updatedPayment) {
        logger.info("Updating payment with ID: {}", id);
        Payment p = getPaymentById(id);

        p.setBooking(updatedPayment.getBookingId());
        p.setAmount(updatedPayment.getAmount());
        p.setPaymentMethod(updatedPayment.getPaymentMethod());
        p.setPaymentStatus(updatedPayment.getPaymentStatus());
        p.setPaymentTime(updatedPayment.getPaymentTime());

        Payment savedPayment = repo.save(p);
        logger.info("Payment with ID: {} updated successfully", id);
        return savedPayment;
    }

    @Override
    public void deletePayment(int id) {
        logger.info("Deleting payment with ID: {}", id);
        Payment p = getPaymentById(id);
        repo.delete(p);
        logger.info("Payment with ID: {} deleted successfully", id);
    }

    @Override
    public List<Payment> getPaymentsByUserId(int userId) {
        logger.info("Fetching payments by user ID: {}", userId);
        return repo.findPaymentsByUser_UserId(userId);
    }
}
