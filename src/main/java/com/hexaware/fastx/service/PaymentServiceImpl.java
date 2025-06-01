package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.PaymentDTO;
import com.hexaware.fastx.entity.Booking;
import com.hexaware.fastx.entity.Payment;
import com.hexaware.fastx.exception.PaymentNotFoundException;
import com.hexaware.fastx.repository.BookingRepository;
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
    
    @Autowired
    private BookingRepository bookingRepo;
    

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
    public Payment createPayment(PaymentDTO dto) {
        logger.info("Creating payment for booking ID: {}", dto.getBookingId());

        // Validate and fetch the associated Booking entity
        Booking booking = bookingRepo.findById(dto.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + dto.getBookingId()));

        // Create and populate the Payment entity
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setPaymentTime(dto.getPaymentTime());

        // Save the Payment
        Payment saved = repo.save(payment);
        logger.info("Payment created with ID: {}", saved.getPaymentId());

        return saved;
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
