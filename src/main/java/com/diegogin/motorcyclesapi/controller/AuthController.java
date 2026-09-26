package com.diegogin.motorcyclesapi.controller;

import com.diegogin.motorcyclesapi.dto.AuthStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Catalog")
public class AuthController {

    @GetMapping("/status")
    @Operation(summary = "Authentication status")
    @ApiResponse(
            responseCode = "200",
            description = "Authentication status retrieved successfully"
    )
    public AuthStatus getAuthStatus(Authentication authentication) {

        boolean authenticated = authentication !=
                null && authentication.isAuthenticated();

        return new AuthStatus(authenticated);
    }

}
