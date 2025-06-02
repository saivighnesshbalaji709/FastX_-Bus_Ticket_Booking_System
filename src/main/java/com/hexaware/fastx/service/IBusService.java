package com.hexaware.fastx.service;

import java.util.List;

import com.hexaware.fastx.dto.BusDTO;

public interface IBusService {
    BusDTO addBus(BusDTO dto);
    List<BusDTO> getAllBuses();
    BusDTO getBusById(int id);
    BusDTO updateBus(int id, BusDTO dto);
    void deleteBus(int id);
}
