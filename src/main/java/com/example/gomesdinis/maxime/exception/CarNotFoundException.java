package com.example.gomesdinis.maxime.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String model) {
        super("Car with model name " + model + " could not be found.");
    }

}