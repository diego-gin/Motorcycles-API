package com.diegogin.motorcyclesapi.dto;

public record MotorcycleSummary(
        Long id,
        String brand,
        String model,
        Integer year
) {

}