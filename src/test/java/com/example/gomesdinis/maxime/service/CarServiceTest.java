package com.example.gomesdinis.maxime.service;

import com.example.gomesdinis.maxime.data.CarModelList;
import com.example.gomesdinis.maxime.dto.CarDto;
import com.example.gomesdinis.maxime.exception.CarNotFoundException;
import com.example.gomesdinis.maxime.repository.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carService = new CarService(carRepository);
    }

    @Test
    public void shouldRetrieveCar() {
        // Given
        String carToRetrieve = "R123";
        Mockito.when(carRepository.findOneByModelIgnoreCase(carToRetrieve)).thenReturn(Optional.of(CarModelList.RENAULT_R123));

        // When
        CarDto retreiveCar = carService.getByModel(carToRetrieve);

        // Then
        Assertions.assertThat(retreiveCar.model())
                .isEqualTo("R123");
    }

    @Test
    public void shouldFailToRetrieve_WhenCarDoesNotExist() {
        // Given
        String unknownCar = "R123456789";
        Mockito.when(carRepository.findOneByModelIgnoreCase(unknownCar)).thenReturn(Optional.empty());

        // When
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(CarNotFoundException.class, () -> {
            carService.getByModel(unknownCar);
        });

        // Then
        Assertions.assertThat(exception.getMessage()).isEqualTo("Car with model name R123456789 could not be found.");
    }

}
