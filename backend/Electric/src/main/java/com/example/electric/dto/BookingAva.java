package com.example.electric.dto;

public class BookingAva {
    private Long stationId;
    private Long chargerId;
    private String name;
    private double chargingRate;
    private double latitude;
    private double longitude;
    private String address;

    public BookingAva(Long stationId, Long chargerId, String name, double chargingRate, double latitude, double longitude, String address) {
        this.stationId = stationId;
        this.chargerId = chargerId;
        this.name = name;
        this.chargingRate = chargingRate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}
