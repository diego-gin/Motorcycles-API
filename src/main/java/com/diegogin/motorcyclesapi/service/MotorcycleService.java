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

    //FIND ALL IN REPOSITORY LIST
    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    //FIND BY ID > OPTIONAL NULL
    public Optional<Motorcycle> getMotorcycleById(Long id) {
        return motorcycleRepository.findById(id);
    }

    //SAVE MOTORCYCLE IN REPOSITORY
    public Motorcycle saveMotorcycle(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    //DELETE BY ID
    public boolean deleteMotorcycle(Long id) {
        if (!motorcycleRepository.existsById(id)) {
            return false;
        }
        motorcycleRepository.deleteById(id);
        return true;
    }

    //PUT
    public Optional<Motorcycle> updateMotorcycle(Long id, Motorcycle updatedMotorcycle) {

        Optional<Motorcycle> existingMotorcycle = motorcycleRepository.findById(id);

        if (existingMotorcycle.isEmpty()) {
            return Optional.empty();
        }

        Motorcycle motorcycle = existingMotorcycle.get();
        motorcycle.setBrand(updatedMotorcycle.getBrand());
        motorcycle.setModel(updatedMotorcycle.getModel());
        motorcycle.setYear(updatedMotorcycle.getYear());
        motorcycle.setImageUrl(updatedMotorcycle.getImageUrl());
        motorcycle.setVersion(updatedMotorcycle.getVersion());
        motorcycle.setGeneration(updatedMotorcycle.getGeneration());
        motorcycle.setCategory(updatedMotorcycle.getCategory());
        motorcycle.setReleaseDate(updatedMotorcycle.getReleaseDate());
        motorcycle.setCountry(updatedMotorcycle.getCountry());
        motorcycle.setSeatHeight(updatedMotorcycle.getSeatHeight());
        motorcycle.setWeight(updatedMotorcycle.getWeight());
        motorcycle.setFuelTankCapacity(updatedMotorcycle.getFuelTankCapacity());
        motorcycle.setEngineType(updatedMotorcycle.getEngineType());
        motorcycle.setCooling(updatedMotorcycle.getCooling());
        motorcycle.setCylinders(updatedMotorcycle.getCylinders());
        motorcycle.setPower(updatedMotorcycle.getPower());
        motorcycle.setTorque(updatedMotorcycle.getTorque());
        motorcycle.setDisplacement(updatedMotorcycle.getDisplacement());
        motorcycle.setTopSpeed(updatedMotorcycle.getTopSpeed());

        return Optional.of(motorcycleRepository.save(motorcycle));
    }

}
