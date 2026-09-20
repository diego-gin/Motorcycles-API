package com.diegogin.motorcyclesapi.controller;

import com.diegogin.motorcyclesapi.model.Motorcycle;
import com.diegogin.motorcyclesapi.service.MotorcycleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/motorcycles")
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    public MotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping //ENDPOINT GET ALL
    public List<Motorcycle> getAllMotorcycles() {

        return motorcycleService.getAllMotorcycles();
    }

    @GetMapping("/{id}") //ENDPOINT GET BY ID
    public ResponseEntity<Motorcycle> getMotorcycleById(
            @PathVariable Long id) {

        Optional<Motorcycle> motorcycle =
                motorcycleService.getMotorcycleById(id);

        if (motorcycle.isPresent()) {
            return ResponseEntity.ok(motorcycle.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping //ENDPOINT POST
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody Motorcycle motorcycle) {
        Motorcycle savedMotorcycle = motorcycleService.saveMotorcycle(motorcycle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMotorcycle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable Long id) {

        boolean deleted = motorcycleService.deleteMotorcycle(id);

        if(!deleted) {return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}") //PUT
    public ResponseEntity<Motorcycle> updateMotorcycle(
            @PathVariable Long id,
            @RequestBody Motorcycle motorcycle) {

        Optional<Motorcycle> updatedMotorcycle =
                motorcycleService.updateMotorcycle(id, motorcycle);

        if (updatedMotorcycle.isPresent()) {
            return ResponseEntity.ok(updatedMotorcycle.get());
        }
        return ResponseEntity.notFound().build();

    }


}


