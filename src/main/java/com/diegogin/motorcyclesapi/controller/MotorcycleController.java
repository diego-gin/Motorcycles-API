package com.diegogin.motorcyclesapi.controller;

import com.diegogin.motorcyclesapi.model.Motorcycle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MotorcycleController {

    @GetMapping("/api/motorcycles")
    public List<Motorcycle> getMotorcycles() {

        List<Motorcycle> motorcycles = new ArrayList<>();

        return motorcycles;

    }
}
