package com.example.electric.service;

import com.example.electric.model.Car;
import com.example.electric.model.Station;
import com.example.electric.respository.StationRepository;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class DistanceMatrixService {
    @Value("${google.maps.api.key}")
    private String apiKey;

    @Autowired
    private StationRepository stationRepository;

    //JSON File includes distance, time taken
    public DistanceMatrix getDistanceMatrix(String origin, String destination) throws Exception {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        return DistanceMatrixApi.newRequest(context)
                .origins(origin)
                .destinations(destination)
                .await(); // This will make the API request and return the result.
    }

    public long getDistanceByID(Station station) throws Exception{
        Station dest = stationRepository.findById(station.getId()).orElse(null);
        String location1 = Double.toString(station.getLatitude()) + "," + Double.toString(station.getLongitude());
        String location2 = Double.toString(dest.getLatitude()) + "," + Double.toString(dest.getLongitude());

        DistanceMatrixRow distanceMatrix = this.getDistanceMatrix(location1, location2).rows[0];

        return distanceMatrix.elements[0].distance.inMeters;

    }

    public long getDurationByID(Station station) throws Exception {
        Station dest = stationRepository.findById(station.getId()).orElse(null);
        String location1 = Double.toString(station.getLatitude()) + "," + Double.toString(station.getLongitude());
        String location2 = Double.toString(dest.getLatitude()) + "," + Double.toString(dest.getLongitude());

        DistanceMatrixRow distanceMatrix = this.getDistanceMatrix(location1, location2).rows[0];

        return distanceMatrix.elements[0].duration.inSeconds;

    }

    public String calculateEstimateTimeOfCharging(Car car) {
        double time = (car.getBatteryCapacity() * (100 - car.getBatteryPercentage())) / (60 * 60);

        return "" + time;
    }

    public String calculateCostOfCharging(Car car) {
        double cost = (car.getBatteryCapacity() * (100.0 - car.getBatteryPercentage())) / 1 * 0.12;

        return "" + cost;
    }

//    public long getDistanceByName(String latitude,String longitude, String station2) throws Exception {
////        Station s1 = stationRepository.findStationByName(station1).orElse(null);
//        Station s2 = stationRepository.findStationByName(station2).orElse(null);
//        String location1 = (latitude + "," + longitude);
//        String location2 = Double.toString(s2.getLatitude()) + "," + Double.toString(s2.getLongitude());
//
//        DistanceMatrixRow distanceMatrix = this.getDistanceMatrix(location1, location2).rows[0];
//
//        return distanceMatrix.elements[0].distance.inMeters;
//    }

//    public long getDurationByName(String station1, String station2) throws Exception {
//        Station s1 = stationRepository.findStationByName(station1).orElse(null);
//        Station s2 = stationRepository.findStationByName(station2).orElse(null);
//        String location1 = Double.toString(s1.getLatitude()) + "," + Double.toString(s1.getLongitude());
//        String location2 = Double.toString(s2.getLatitude()) + "," + Double.toString(s2.getLongitude());
//
//        DistanceMatrixRow distanceMatrix = this.getDistanceMatrix(location1, location2).rows[0];
//
//        return distanceMatrix.elements[0].duration.inSeconds;
//    }


}
