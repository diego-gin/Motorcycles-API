package com.diegogin.motorcyclesapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "motorcycles")
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "Honda")
    @NotBlank(message = "Brand is required")
    @Column(nullable=false)
    private String brand;

    @Schema(example = "CBR900RR FireBlade")
    @NotBlank(message = "Model is required")
    @Column(nullable=false)
    private String model;

    @Schema(example = "1992")
    @NotNull(message = "Year is required")
    @Min(value = 1915, message = "Year must be greater than 1915")
    @Column(nullable=false)
    private Integer year;

    private String imageUrl;
    private String version;
    private String generation;
    private String category;
    private LocalDate releaseDate;
    private String country;

    @Schema(description = "Seat Height in mm", example = "830")
    @Positive(message = "Seat Height must be greater than zero")
    private Integer seatHeight;

    @Positive(message = "Weight must be greater than zero")
    private BigDecimal weight;

    @Positive(message = "Fuel tank capacity must be greater than zero")
    private BigDecimal fuelTankCapacity;

    private String engineType;
    private String cooling;

    @Positive(message = "Number of cylinders must be greater than zero")
    private Integer cylinders;

    @Positive(message = "Power must be greater than zero")
    private BigDecimal power;

    @Positive(message = "Torque must be greater than zero")
    private BigDecimal torque;

    @Schema(description = "Displacement in CC", example = "893")
    @Positive(message = "Displacement must be greater than zero")
    private Integer displacement;

    @Positive(message = "Top speed must be greater than zero")
    private Integer topSpeed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSeatHeight() {
        return seatHeight;
    }

    public void setSeatHeight(Integer seatHeight) {
        this.seatHeight = seatHeight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(BigDecimal fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getCooling() {
        return cooling;
    }

    public void setCooling(String cooling) {
        this.cooling = cooling;
    }

    public Integer getCylinders() {
        return cylinders;
    }

    public void setCylinders(Integer cylinders) {
        this.cylinders = cylinders;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public BigDecimal getTorque() {
        return torque;
    }

    public void setTorque(BigDecimal torque) {
        this.torque = torque;
    }

    public Integer getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }

    public Integer getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(Integer topSpeed) {
        this.topSpeed = topSpeed;
    }
}
