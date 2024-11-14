package com.example.gomesdinis.maxime.data;

import com.example.gomesdinis.maxime.model.CarModel;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class CarModelList {

    public static CarModel RENAULT_R123 = new CarModel(
            "Renault",
            "R123",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "on"
    );

    public static CarModel RENAULT_R456 = new CarModel(
            "Renault",
            "R456",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "off"
    );

    public static CarModel FIAT_500 = new CarModel(
            "Fiat",
            "500",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "on"
    );

    public static CarModel FIAT_2000 = new CarModel(
            "Fiat",
            "2000",
            LocalDate.of(2024, Month.NOVEMBER, 14),
            "off"
    );

    public static List<CarModel> ALL = Arrays.asList(RENAULT_R123, RENAULT_R456, FIAT_500, FIAT_2000);

}
