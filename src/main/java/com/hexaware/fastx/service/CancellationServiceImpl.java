package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.Cancellation;
import com.hexaware.fastx.exception.CancellationNotFoundException;
import com.hexaware.fastx.repository.CancellationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancellationServiceImpl implements ICancellationService {

    @Autowired
    private CancellationRepository repo;

    @Override
    public List<Cancellation> getAllCancellations() {
        return repo.getAllCancellations();
    }

    @Override
    public Cancellation getCancellationById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new CancellationNotFoundException("Cancellation not found with ID: " + id));
    }

    @Override
    public Cancellation createCancellation(Cancellation cancellation) {
        return repo.save(cancellation);
    }

    @Override
    public Cancellation updateCancellation(int id, Cancellation updatedCancellation) {
        Cancellation c = getCancellationById(id);
        c.setBookingId(updatedCancellation.getBookingId());
        c.setCancellationDateTime(updatedCancellation.getCancellationDateTime());
        c.setRefundAmount(updatedCancellation.getRefundAmount());
        return repo.save(c);
    }

    @Override
    public void deleteCancellation(int id) {
        Cancellation c = getCancellationById(id);
        repo.delete(c);
    }
}