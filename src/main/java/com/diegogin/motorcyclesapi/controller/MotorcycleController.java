package com.diegogin.motorcyclesapi.controller;

import com.diegogin.motorcyclesapi.model.Motorcycle;
import com.diegogin.motorcyclesapi.service.MotorcycleService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "Catalog",
        description = "Operations for managing the catalog"
)
@RestController
@RequestMapping("api/motorcycles")
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    public MotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @Operation(summary = "List all")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping //ENDPOINT GET ALL
    public List<Motorcycle> getAllMotorcycles() {

        return motorcycleService.getAllMotorcycles();
    }

    @Operation(summary = "Find by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "Not found")
    })
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

    @Operation(summary = "Create")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping //ENDPOINT POST
    public ResponseEntity<Motorcycle> createMotorcycle(@Valid  @RequestBody Motorcycle motorcycle) {
        Motorcycle savedMotorcycle = motorcycleService.saveMotorcycle(motorcycle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMotorcycle);
    }

    @Operation(summary = "Delete")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable Long id) {

        boolean deleted = motorcycleService.deleteMotorcycle(id);

        if(!deleted) {return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Update")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PutMapping("/{id}") //PUT = UPDATE
    public ResponseEntity<Motorcycle> updateMotorcycle(
            @PathVariable Long id,
            @Valid @RequestBody Motorcycle motorcycle) {

        Optional<Motorcycle> updatedMotorcycle =
                motorcycleService.updateMotorcycle(id, motorcycle);

        if (updatedMotorcycle.isPresent()) {
            return ResponseEntity.ok(updatedMotorcycle.get());
        }
        return ResponseEntity.notFound().build();
    }

}


