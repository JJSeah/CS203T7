package com.example.electric.service.inter;

import com.example.electric.model.Car;
import com.example.electric.model.Station;
import com.google.maps.model.DistanceMatrix;

public interface DistanceMatrixServiceInter {
    DistanceMatrix getDistanceMatrix(String origin, String destination)
            throws Exception;

    long getDistanceByID(Station station) throws Exception;

    int getDurationByID(Station station) throws Exception;

    int calculateEstimateTimeOfCharging(Car car);

    double calculateCostOfCharging(Car car);
}
