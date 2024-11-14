package com.example.gomesdinis.maxime.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record CarDto(
        @NotBlank(message = "Brand is mandatory.") String brand,
        @NotBlank(message = "Model is mandatory.") String model,
        @NotNull(message = "Release date is mandatory.") @PastOrPresent(message = "Release date must be past or present.") LocalDate releaseDate,
        @NotBlank(message = "Status is mandatory.") String status
) {
}
