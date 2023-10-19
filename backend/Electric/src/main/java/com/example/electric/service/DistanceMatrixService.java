package com.example.electric.service;

import com.example.electric.model.Car;
import com.example.electric.model.Station;
import com.example.electric.respository.StationRepository;
import com.example.electric.service.inter.DistanceMatrixServiceInter;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class DistanceMatrixService implements DistanceMatrixServiceInter {
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

    public int getDurationByID(Station station) throws Exception {
        Station dest = stationRepository.findById(station.getId()).orElse(null);
        String location1 = Double.toString(station.getLatitude()) + "," + Double.toString(station.getLongitude());
        String location2 = Double.toString(dest.getLatitude()) + "," + Double.toString(dest.getLongitude());

        DistanceMatrixRow distanceMatrix = this.getDistanceMatrix(location1, location2).rows[0];

        long timeInSeconds = distanceMatrix.elements[0].duration.inSeconds;
        long timeInMinutes = timeInSeconds / 60;

        return (int)(timeInMinutes / 5) * 5;
    }

    public int calculateEstimateTimeOfCharging(Car car) {
        //Car parameters
        double batteryCapacity = car.getBatteryCapacity();
        double batteryPercentage = car.getBatteryPercentage();
        double currentStateOfCharge = batteryCapacity * batteryPercentage / 100;

        //Standardise charging power at chargers since this represents the most common charging speed - level 2
        //with a range from 11kW to 19kW in the new models, hence choosing the average of 15kW
        double chargingPower = 15;

        //Calculate time
        double time = (batteryCapacity - currentStateOfCharge) / chargingPower;

        double timeInMinutes = time * 60;

        return (int)(timeInMinutes / 5) * 5;
    }

    public double calculateCostOfCharging(Car car) {
        //Car parameters
        int timeOfCharging = this.calculateEstimateTimeOfCharging(car);
        double timeOfChargingInHour = timeOfCharging / 60;

        //Based on research, charging rate is around $3 per hour for overnight slow charging
        //and $25 for rapid charging. Due to the choice of moderate level of charging rate at level 2,
        //and to encourage the use of EV in Singapore, we have decided to choose the charging cost
        //per hour to be at $10.

        double chargingCostRate = 10;

        //Calculate cost
        return chargingCostRate * timeOfChargingInHour;
    }

    public static double calculateCostOfCharging(long duration) {

        double timeOfChargingInHour = (double) duration / 60;

        //Based on research, charging rate is around $3 per hour for overnight slow charging
        //and $25 for rapid charging. Due to the choice of moderate level of charging rate at level 2,
        //and to encourage the use of EV in Singapore, we have decided to choose the charging cost
        //per hour to be at $10.

        double chargingCostRate = 10;

        //Calculate cost
        return chargingCostRate * timeOfChargingInHour;
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
