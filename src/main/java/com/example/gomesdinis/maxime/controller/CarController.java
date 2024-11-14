package com.example.gomesdinis.maxime.controller;

import com.example.gomesdinis.maxime.dto.CarDto;
import com.example.gomesdinis.maxime.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Tag(name = "Cars API")
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Operation(summary = "Finds cars", description = "Finds cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars list.",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CarDto.class)))})
    })
    @GetMapping
    public List<CarDto> list() {
        return carService.getAllCars();
    }

    @Operation(summary = "Finds a car", description = "Finds a car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class))}),
            @ApiResponse(responseCode = "404", description = "Car with specified model was not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.example.gomesdinis.maxime.exception.Error.class))})
    })
    @GetMapping("/{model}")
    public CarDto getByModel(@PathVariable("model") String model) {
        return carService.getByModel(model);
    }

    @Operation(summary = "Creates a car", description = "Creates a car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created car.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class))}),
            @ApiResponse(responseCode = "400", description = "Car with specified model already exists.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.example.gomesdinis.maxime.exception.Error.class))})
    })
    @PostMapping
    public CarDto createCar(@RequestBody @Valid CarDto carDto) {
        return carService.create(carDto);
    }

    @Operation(summary = "Updates a car", description = "Updates a car with model name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated car.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class))}),
            @ApiResponse(responseCode = "404", description = "Car with specified model was not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.example.gomesdinis.maxime.exception.Error.class))})
    })
    @PutMapping
    public CarDto updateCar(@RequestBody @Valid CarDto carDto) {
        return carService.update(carDto);
    }

    @Operation(summary = "Deletes a car", description = "Deletes a car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car has been deleted."),
            @ApiResponse(responseCode = "404", description = "Car with specified model was not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.example.gomesdinis.maxime.exception.Error.class))})
    })
    @DeleteMapping("/{model}")
    public void deleteCarByModel(@PathVariable("model") String model) {
        carService.delete(model);
    }

    @GetMapping("populate")
    public void populate() {

        CarDto carDto1 = new CarDto(
                "Renault",
                "clio",
                LocalDate.of(2024, Month.NOVEMBER, 14),
                "on"
        );
        carService.create(carDto1);

        CarDto carDto2 = new CarDto(
                "Renault",
                "felo",
                LocalDate.of(2024, Month.NOVEMBER, 14),
                "on"
        );
        carService.create(carDto2);

        CarDto carDto3 = new CarDto(
                "Fiat",
                "500",
                LocalDate.of(2024, Month.NOVEMBER, 14),
                "off"
        );
        carService.create(carDto3);

        CarDto carDto4 = new CarDto(
                "Fiat",
                "2000",
                LocalDate.of(2024, Month.NOVEMBER, 14),
                "on"
        );
        carService.create(carDto4);

    }
}
