package com.example.gomesdinis.maxime.service;

import com.example.gomesdinis.maxime.dto.CarDto;
import com.example.gomesdinis.maxime.exception.CarAlreadyExistsException;
import com.example.gomesdinis.maxime.exception.CarNotFoundException;
import com.example.gomesdinis.maxime.model.CarModel;
import com.example.gomesdinis.maxime.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream()
                .map(car -> new CarDto(
                        car.getBrand(),
                        car.getModel(),
                        car.getReleaseDate(),
                        car.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public CarDto getByModel(String model) {

        Optional<CarModel> car = carRepository.findOneByModelIgnoreCase(model);

        if (car.isEmpty()) {
            throw new CarNotFoundException(model);
        }

        CarModel carModel = car.get();
        return new CarDto(
                carModel.getBrand(),
                carModel.getModel(),
                carModel.getReleaseDate(),
                carModel.getStatus()
        );
    }

    public CarDto create(CarDto carDto) {

        Optional<CarModel> carToCreate = carRepository.findOneByModelIgnoreCase(carDto.model());

        if (carToCreate.isPresent()) {
            throw new CarAlreadyExistsException(carDto.model());
        }

        CarModel carModel = new CarModel(
                carDto.brand(),
                carDto.model(),
                carDto.releaseDate(),
                carDto.status()
        );

        carRepository.save(carModel);

        return getByModel(carModel.getModel());
    }

    public CarDto update(CarDto carDto) {

        Optional<CarModel> car = carRepository.findOneByModelIgnoreCase(carDto.model());

        if (car.isEmpty()) {
            throw new CarNotFoundException(carDto.model());
        }

        car.get().setBrand(carDto.brand());
        car.get().setModel(carDto.model());
        car.get().setReleaseDate(carDto.releaseDate());
        car.get().setStatus(carDto.status());
        carRepository.save(car.get());

        return getByModel(carDto.model());

    }

    public void delete(String model) {

        Optional<CarModel> carToDelete = carRepository.findOneByModelIgnoreCase(model);

        if (carToDelete.isEmpty()) {
            throw new CarNotFoundException(model);
        }

        carRepository.delete(carToDelete.get());

    }

}
