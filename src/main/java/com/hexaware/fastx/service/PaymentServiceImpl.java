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
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository repo;

    @Autowired
    private BookingRepository bookingRepo;

    @Override
    public List<PaymentDTO> getAllPayments() {
        logger.info("Fetching all payments");
        return repo.getAllPayments().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(int id) {
        logger.info("Fetching payment with ID: {}", id);
        Payment payment = repo.findById(id).orElseThrow(() -> {
            logger.error("Payment not found with ID: {}", id);
            return new PaymentNotFoundException("Payment not found with ID: " + id);
        });
        return toDTO(payment);
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {
        logger.info("Creating payment for booking ID: {}", dto.getBookingId());

        Booking booking = bookingRepo.findById(dto.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + dto.getBookingId()));

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setPaymentTime(dto.getPaymentTime());

        Payment saved = repo.save(payment);
        logger.info("Payment created with ID: {}", saved.getPaymentId());
        return toDTO(saved);
    }

    @Override
    public PaymentDTO updatePayment(int id, PaymentDTO dto) {
        logger.info("Updating payment with ID: {}", id);

        Payment existing = repo.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));

        Booking booking = bookingRepo.findById(dto.getBookingId())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + dto.getBookingId()));

        existing.setBooking(booking);
        existing.setAmount(dto.getAmount());
        existing.setPaymentMethod(dto.getPaymentMethod());
        existing.setPaymentStatus(dto.getPaymentStatus());
        existing.setPaymentTime(dto.getPaymentTime());

        Payment saved = repo.save(existing);
        logger.info("Payment updated with ID: {}", saved.getPaymentId());
        return toDTO(saved);
    }

    @Override
    public void deletePayment(int id) {
        logger.info("Deleting payment with ID: {}", id);
        Payment p = repo.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
        repo.delete(p);
        logger.info("Deleted payment with ID: {}", id);
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(int userId) {
        logger.info("Fetching payments by user ID: {}", userId);
        return repo.findPaymentsByUser_UserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Conversion helper
    private PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setBookingId(payment.getBooking().getBookingId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setPaymentTime(payment.getPaymentTime());
        return dto;
    }

}
