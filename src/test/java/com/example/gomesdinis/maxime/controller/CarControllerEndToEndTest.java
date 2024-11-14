package com.example.gomesdinis.maxime.controller;

import com.example.gomesdinis.maxime.dto.CarDto;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// Créer un port random pour éviter les conflits avec d'autres tests ou applications en cours de lancement
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarControllerEndToEndTest {

    // Récupérer le port random pour ce context
    @LocalServerPort
    private int port;

    // Créer des requêtes de manière programmatique (avec du code)
    @Autowired
    private TestRestTemplate restTemplate;

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
        String url = "http://localhost:" + port + "/cars";
        HttpEntity<CarDto> request = new HttpEntity<>(carDto);
        ResponseEntity<CarDto> carResponseEntity = this.restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                CarDto.class
        );

        // Then
        Assertions.assertThat(carResponseEntity.getBody().model()).isEqualTo("clio");
    }

    @Test
    public void shouldFailToCreateCar_WhenCarToCreateIsInvalid() {
        // Given
        CarDto carDto = new CarDto(
                "Renault",
                null,
                LocalDate.of(2024, Month.NOVEMBER, 14),
                "on"
        );

        // When
        String url = "http://localhost:" + port + "/cars";
        HttpEntity<CarDto> request = new HttpEntity<>(carDto);
        ResponseEntity<CarDto> carResponseEntity = this.restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                CarDto.class
        );

        // Then
        Assertions.assertThat(carResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
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
        String url = "http://localhost:" + port + "/cars";
        HttpEntity<CarDto> request = new HttpEntity<>(carDto);
        ResponseEntity<CarDto> carResponseEntity = this.restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                CarDto.class
        );

        // Then
        Assertions.assertThat(carResponseEntity.getBody().model()).isEqualTo("clio");
        Assertions.assertThat(carResponseEntity.getBody().status()).isEqualTo("test");
    }

    @Test
    public void shouldDeleteCar() {
        // Given / When
        String url = "http://localhost:" + port + "/cars";
        this.restTemplate.exchange(
                url + "/clio",
                HttpMethod.DELETE,
                null,
                CarDto.class
        );

        ResponseEntity<List<CarDto>> carResponseEntity = this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarDto>>() {}
        );

        // Then
        Assertions.assertThat(carResponseEntity.getBody())
                .extracting("model")
                .containsExactly();
    }

}
