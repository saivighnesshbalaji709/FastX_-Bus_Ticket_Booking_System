package com.hexaware.fastx.repository;

import com.hexaware.fastx.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "SELECT * FROM payment", nativeQuery = true)
    List<Payment> getAllPayments();
    List<Payment> findPaymentsByUser_UserId(int userId);
}
