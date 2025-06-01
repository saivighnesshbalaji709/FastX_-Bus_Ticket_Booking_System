package com.hexaware.fastx.controller;

import com.hexaware.fastx.entity.Cancellation;
import com.hexaware.fastx.service.ICancellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cancellations")
public class CancellationController {

    @Autowired
    private ICancellationService service;

    @GetMapping
    public List<Cancellation> getAll() {
        return service.getAllCancellations();
    }

    @GetMapping("/{id}")
    public Cancellation getById(@PathVariable int id) {
        return service.getCancellationById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Cancellation> create(@RequestBody Cancellation cancellation) {
        return new ResponseEntity<>(service.createCancellation(cancellation), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public Cancellation update(@PathVariable int id, @RequestBody Cancellation cancellation) {
        return service.updateCancellation(id, cancellation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteCancellation(id);
        return ResponseEntity.noContent().build();
    }
}
