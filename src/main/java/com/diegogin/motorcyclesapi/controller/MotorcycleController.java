package com.diegogin.motorcyclesapi.controller;

import com.diegogin.motorcyclesapi.dto.MotorcycleSummary;
import com.diegogin.motorcyclesapi.model.Motorcycle;
import com.diegogin.motorcyclesapi.service.MotorcycleService;
import com.diegogin.motorcyclesapi.documentation.OpenApiExamples;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "Catalog",
        description = "Operations for managing the catalog"
)
@RestController
@RequestMapping("/api/motorcycles")
public class MotorcycleController {

    private final MotorcycleService motorcycleService;

    public MotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    //ENDPOINT GET LIST ALL ++++++++++++++++++++++++++++++++++++++++++++++++++
    @Operation(summary = "List")
    @GetMapping
    public List<MotorcycleSummary> getAllMotorcycles() {

        return motorcycleService.getAllMotorcycles();
    }

    //ENDPOINT GET BY ID +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Operation(summary = "Details")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Motorcycle.class),
                            examples = @ExampleObject(
                                    value = OpenApiExamples.RESPONSE
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(
            @PathVariable Long id) {

        Optional<Motorcycle> motorcycle =
                motorcycleService.getMotorcycleById(id);

        if (motorcycle.isPresent()) {
            return ResponseEntity.ok(motorcycle.get());
        }
        return ResponseEntity.notFound().build();
    }

    //ENDPOINT POST ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Operation(summary = "Create")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Motorcycle.class),
                            examples = @ExampleObject(
                                    value = OpenApiExamples.RESPONSE
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = OpenApiExamples.VALIDATION_ERROR
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Motorcycle> createMotorcycle(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data for the new resource",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = OpenApiExamples.REQUEST
                            )
                    )
            )
            @RequestBody Motorcycle motorcycle) {
        Motorcycle savedMotorcycle = motorcycleService.saveMotorcycle(motorcycle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMotorcycle);
    }

    //PUT = UPDATE ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Operation(summary = "Update")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Motorcycle.class),
                            examples = @ExampleObject(
                                    value = OpenApiExamples.RESPONSE
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = OpenApiExamples.VALIDATION_ERROR
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Motorcycle> updateMotorcycle(
            @PathVariable Long id,
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated resource data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = OpenApiExamples.REQUEST
                            )
                    )
            )
            @RequestBody Motorcycle motorcycle) {
        Optional<Motorcycle> updatedMotorcycle =
                motorcycleService.updateMotorcycle(id, motorcycle);

        if (updatedMotorcycle.isPresent()) {
            return ResponseEntity.ok(updatedMotorcycle.get());
        }
        return ResponseEntity.notFound().build();
    }

    //ENDPOINT DELETE +++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Operation(summary = "Delete")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable Long id) {

        boolean deleted = motorcycleService.deleteMotorcycle(id);

        if(!deleted) {return ResponseEntity.notFound().build();}

        return ResponseEntity.noContent().build();
    }

}


