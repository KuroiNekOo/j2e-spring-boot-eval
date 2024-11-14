package com.example.gomesdinis.maxime.repository;

import com.example.gomesdinis.maxime.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {

    Optional<CarModel> findOneByModelIgnoreCase(String model);

}
