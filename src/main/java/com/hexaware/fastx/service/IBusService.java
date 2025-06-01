package com.hexaware.fastx.service;

import java.util.List;

import com.hexaware.fastx.dto.BusDTO;
import com.hexaware.fastx.entity.Bus;

public interface IBusService {
    List<Bus> getAllBuses();
    Bus getBusById(int id);
    Bus updateBus(int id, Bus updatedBus);
    void deleteBus(int id);
	Bus addBus(BusDTO dto);
}