package com.diegogin.motorcyclesapi.repository;

import com.diegogin.motorcyclesapi.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {


}
