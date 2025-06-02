package com.hexaware.restapi.service;

import com.hexaware.fastx.entity.Cancellation;
import java.util.List;

public interface ICancellationService {
    List<Cancellation> getAllCancellations();
    Cancellation getCancellationById(int id);
    Cancellation createCancellation(Cancellation cancellation);
    Cancellation updateCancellation(int id, Cancellation cancellation);
    void deleteCancellation(int id);
}
