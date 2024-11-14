package com.example.gomesdinis.maxime.exception;

public class CarAlreadyExistsException extends RuntimeException {

    public CarAlreadyExistsException(String model) {
        super("Car with model name " + model + " already exists.");
    }

}