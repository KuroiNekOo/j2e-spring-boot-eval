package com.example.gomesdinis.maxime.service;

import com.example.gomesdinis.maxime.dto.CarDto;
import com.example.gomesdinis.maxime.exception.CarNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootTest
// Permet de détruire le context (bdd, etc...) avant chaque test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarServiceIntegrationtest {

    @Autowired
    private CarService carService;

    @Test
    public void shouldCreateCar() {
        // Given
        CarDto carDto = new CarDto(
                "Renault",
                "clio",
                LocalDate.of(2024, Month.NOVEMBER, 14),
                "on"
        );

        // When
        carService.create(carDto);
        CarDto createdCar = carService.getByModel(carDto.model());

        // Then
        Assertions.assertThat(createdCar.brand()).isEqualTo("Renault");
        Assertions.assertThat(createdCar.model()).isEqualTo("clio");
        Assertions.assertThat(createdCar.releaseDate()).isEqualTo(LocalDate.of(2024, Month.NOVEMBER, 14));
        Assertions.assertThat(createdCar.status()).isEqualTo("on");
    }

    @Test
    public void shouldUpdateCar() {
        // Given
        shouldCreateCar();
        CarDto carDto = new CarDto(
                "Renault",
                "clio",
                LocalDate.of(2024, Month.NOVEMBER, 14),
                "test"
        );

        // When
        carService.update(carDto);
        CarDto updatedCar = carService.getByModel(carDto.model());

        // Then
        Assertions.assertThat(updatedCar.brand()).isEqualTo("Renault");
        Assertions.assertThat(updatedCar.model()).isEqualTo("clio");
        Assertions.assertThat(updatedCar.releaseDate()).isEqualTo(LocalDate.of(2024, Month.NOVEMBER, 14));
        Assertions.assertThat(updatedCar.status()).isEqualTo("test");
    }

    @Test
    public void shouldDeleteCar() {
        // Given
        shouldCreateCar();
        String carModelName = "clio";

        // When
        carService.delete(carModelName);
        List<CarDto> allCars = carService.getAllCars();

        // Then
        Assertions.assertThat(allCars)
                .extracting("model")
                .containsExactly();
    }

    // Cas à la marge (pour les erreurs)
    @Test
    public void shouldFailToDelete_WhenCarDoesNotExist() {
        // Given
        String carModelName = "R123456789";

        // When
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(CarNotFoundException.class, () -> {
            carService.delete(carModelName);
        });

        // Then
        Assertions.assertThat(exception.getMessage()).isEqualTo("Car with model name R123456789 could not be found.");
    }

}
