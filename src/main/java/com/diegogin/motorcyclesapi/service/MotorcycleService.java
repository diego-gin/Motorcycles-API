package com.diegogin.motorcyclesapi.service;

import com.diegogin.motorcyclesapi.model.Motorcycle;
import com.diegogin.motorcyclesapi.repository.MotorcycleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    public MotorcycleService(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleRepository.findAll(); //FIND ALL IN REPOSITORY LIST
    }

    public Optional<Motorcycle> getMotorcycleById(Long id) {
        return motorcycleRepository.findById(id); //FIND BY ID > OPTIONAL NULL
    }

    public Motorcycle saveMotorcycle(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle); //SAVE MOTORCYCLE IN REPOSITORY
    }

    public void deleteMotorcycle(Long id){
        motorcycleRepository.deleteById(id); //DELETE BY ID
    }



}
