package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.SeatDTO;
import com.hexaware.fastx.entity.Seat;
import java.util.List;

public interface ISeatService {
    List<Seat> getAllSeats();
    Seat getSeatById(int id);
    Seat createSeat(Seat seat);
    Seat updateSeat(int id, Seat seat);
    void deleteSeat(int id);
	Seat addSeat(SeatDTO seatDTO);
    List<Seat> getSeatsByBusId(int busId);
}
