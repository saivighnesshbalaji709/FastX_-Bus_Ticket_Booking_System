package com.hexaware.fastx.service;

import com.hexaware.fastx.dto.SeatDTO;
import java.util.List;

public interface ISeatService {
    List<SeatDTO> getAllSeats();
    SeatDTO addSeat(SeatDTO seatDTO);
    SeatDTO getSeatById(int id);
    SeatDTO updateSeat(int id, SeatDTO seatDTO);
    void deleteSeat(int id);
    List<SeatDTO> getSeatsByBusId(int busId);
}
