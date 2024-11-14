package com.example.gomesdinis.maxime.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "car", schema = "public")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand", length = 50, nullable = false)
    private String brand;

    @Column(name = "model", length = 50, nullable = false)
    private String model;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "status", nullable = false)
    private String status;

    public CarModel() {
    }

    public CarModel(
            String brand,
            String model,
            LocalDate releaseDate,
            String status
    ) {
        this.brand = brand;
        this.model = model;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public CarModel(
            Long id,
            String brand,
            String model,
            LocalDate releaseDate,
            String status
    ) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public LocalDate getReleaseDate() { return releaseDate; }

    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }



}
