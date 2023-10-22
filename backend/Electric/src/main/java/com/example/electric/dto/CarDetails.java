package com.example.electric.dto;

public class CarDetails {
    private long carId;
    private String carModel;
    private double battery;
    //status can be driving,charging,off
    private String status;

    public CarDetails(long carId, String carModel, double battery, String status) {
        this.carId = carId;
        this.carModel = carModel;
        this.battery = battery;
        this.status = status;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
