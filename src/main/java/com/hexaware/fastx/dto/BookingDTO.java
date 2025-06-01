package com.hexaware.fastx.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookingDTO {

    private int bookingId;
    private int userId;
    private int routeId;

    private LocalDateTime bookingTime;

    @Min(value = 1, message = "At least one seat must be booked")
    private int seatsBooked;

    @NotNull(message = "Seat numbers must not be null")
    @Size(min = 1, message = "At least one seat must be selected")
    private List<String> seatNumbers;

    @Positive(message = "Total amount must be a positive value")
    private double totalAmount;

    private String status;

    public BookingDTO() {
        super();
    }

    public BookingDTO(int bookingId, int userId, int routeId, LocalDateTime bookingTime,
                      int seatsBooked, List<String> seatNumbers, double totalAmount, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.routeId = routeId;
        this.bookingTime = bookingTime;
        this.seatsBooked = seatsBooked;
        this.seatNumbers = seatNumbers;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking Details:\n" +
               "-------------------------\n" +
               "Booking ID   : " + bookingId + "\n" +
               "User ID      : " + userId + "\n" +
               "Route ID     : " + routeId + "\n" +
               "Booking Time : " + bookingTime + "\n" +
               "Seats Booked : " + seatsBooked + "\n" +
               "Seat Numbers : " + seatNumbers + "\n" +
               "Total Amount : " + totalAmount + "\n" +
               "Status       : " + status + "\n";
    }
}
