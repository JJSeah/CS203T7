package com.example.electric.service.inter;

import com.example.electric.model.Car;
import com.example.electric.model.Station;
import com.google.maps.model.DistanceMatrix;

public interface DistanceMatrixServiceInter {
    DistanceMatrix getDistanceMatrix(String origin, String destination)
            throws Exception;

    long getDistanceByID(Station station) throws Exception;

    int getDurationByID(Station station) throws Exception;

    String calculateEstimateTimeOfCharging(Car car);

    String calculateCostOfCharging(Car car);
}
