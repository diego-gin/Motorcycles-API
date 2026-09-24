package com.diegogin.motorcyclesapi.documentation;

public final class OpenApiExamples {

    private OpenApiExamples() {
    }

    public static final String REQUEST = """
            
            {
            "brand": "Honda",
            "model": "CBR900RR FireBlade",
            "year": 1992,
            "category": "Sport",
            "displacement": 893,
            "power": 124,
            "torque": 88,
            "weight": 185,
            "fuelTankCapacity": 16,
            "seatHeight": 800,
            "imageUrl": "https://example.com/images/cbr900rr.jpg",
            "releaseDate": "1992-01-01",
            "country": "Japan",
            "topSpeed": 260,
            "engineType": "Inline-four",
            "cylinders": 4,
            "cooling": "Liquid",
            "generation": "SC28",
            "version": null
            }
            """;

    public static final String RESPONSE = """
            {
            "id": 1,
            "brand": "Honda",
            "model": "CBR900RR FireBlade",
            "year": 1992,
            "category": "Sport",
            "displacement": 893,
            "power": 124,
            "torque": 88,
            "weight": 185,
            "fuelTankCapacity": 16,
            "seatHeight": 800,
            "imageUrl": "https://example.com/images/cbr900rr.jpg",
            "releaseDate": "1992-01-01",
            "country": "Japan",
            "topSpeed": 260,
            "engineType": "Inline-four",
            "cylinders": 4,
            "cooling": "Liquid",
            "generation": "SC28",
            "version": null
            }
            """;

    public static final String VALIDATION_ERROR = """
            {
            "status": 400,
            "errors": {
                "brand": "Brand is required",
                "model": "Model is required",
                "year": "Year must be 1915 or later"
                }
            }
            """;
}