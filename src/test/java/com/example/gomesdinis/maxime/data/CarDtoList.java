package com.example.gomesdinis.maxime.data;

import com.example.gomesdinis.maxime.dto.CarDto;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class CarDtoList {

    public static CarDto RENAULT_R123 = new CarDto(
            "Renault",
            "R123",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "on"
    );

    public static CarDto RENAULT_R456 = new CarDto(
            "Renault",
            "R456",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "off"
    );

    public static CarDto FIAT_500 = new CarDto(
            "Fiat",
            "500",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "on"
    );

    public static CarDto FIAT_2000 = new CarDto(
            "Fiat",
            "2000",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "off"
    );

    public static List<CarDto> ALL = Arrays.asList(RENAULT_R123, RENAULT_R456, FIAT_500, FIAT_2000);

}
