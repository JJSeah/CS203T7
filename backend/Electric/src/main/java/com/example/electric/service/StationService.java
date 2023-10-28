package com.example.electric.service;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.respository.StationRepository;
import com.example.electric.service.inter.StationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class StationService implements StationServiceInter {

    private final StationRepository stationRepository;

    /**
     * Station Service Constructor
     *
     * Constructor for the StationService class, which is responsible for managing station-related operations.
     *
     * @param stationRepository The repository for accessing and managing station data.
     */
    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    /**
     * Get All Stations
     *
     * Retrieves a list of all available stations in the system.
     *
     * @return A list of Station objects representing all available stations.
     */
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    /**
     * Get Station by ID
     *
     * Retrieves a station by its unique identifier (ID).
     *
     * @param id The unique identifier of the station to retrieve.
     * @return The Station object corresponding to the specified ID, or null if not found.
     */
    public Station getStationById(Long id) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        return optionalStation.orElse(null);
    }

    /**
     * Get Station by Coordinates
     *
     * Retrieves a station based on its geographical coordinates (latitude and longitude).
     *
     * @param latitude The latitude coordinate of the station's location.
     * @param longitude The longitude coordinate of the station's location.
     * @return The Station object corresponding to the specified coordinates, or null if not found.
     */
    public Station getStationByCoordinate(double latitude, double longitude) {
        Optional<Station> optionalStation = stationRepository.findStationByLatitudeAndLongitude(latitude, longitude);
        if (optionalStation.isPresent()) {
            return optionalStation.get();
        } else {
            return null;
        }
//        return optionalStation.orElse(null);
    }

    /**
     * Get Slowest and Available Charger
     *
     * Retrieves the slowest and available charger for a given station and time constraints.
     *
     * @param station The station for which the charger is to be selected.
     * @param startTime The start time of the charging appointment.
     * @param endTime The end time of the charging appointment.
     * @param date The date of the charging appointment.
     * @return The Charger object representing the selected charger, or null if no charger is available.
     */

    public Charger getSlowestAndAvailableCharger(Station station, LocalTime startTime,
                                                 LocalTime endTime, LocalDate date) {
        return stationRepository.findSlowestAvailableChargerForStation(station, startTime, endTime, date);
    }

//
//    public Charger getSlowestAndAvailableCharger(Station station) {
//        List<Charger> chargerList = station.getChargers();
//
//        Charger result = null;
//        double slowestChargingRate = Double.MAX_VALUE;
//
//        for (Charger charger : chargerList) {
//            if (charger.getChargingRate() < slowestChargingRate) {
//                slowestChargingRate = charger.getChargingRate();
//                result = charger;
//            }
//        }
//
//        return result;
//    }

    /**
     * Create Station
     *
     * Creates a new station by saving the provided station object to the database.
     *
     * @param station The Station object to be created.
     * @return The newly created Station object with updated information, including its unique identifier.
     */
    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    /**
     * Update Station
     *
     * Updates an existing station's information based on its unique identifier.
     *
     * @param id The unique identifier of the station to be updated.
     * @param updatedStation The updated Station object with new information.
     * @return The updated Station object after applying the changes, or null if the station is not found.
     */

    public Station updateStation(Long id, Station updatedStation) {
        Optional<Station> optionalStation = stationRepository.findById(id);
        if (optionalStation.isPresent()) {
            Station station = optionalStation.get();
    
            // Update the station fields only if they are not null or have non-default values
            if (updatedStation.getName() != null) station.setName(updatedStation.getName());
            if (updatedStation.getLatitude() != 0.0) station.setLongitude(updatedStation.getLongitude());
            if (updatedStation.getChargers() != null) station.setChargers(updatedStation.getChargers());
            if (updatedStation.isAvail() != false) station.setAvail(updatedStation.isAvail());
            
            // Save the updated station entity
            return stationRepository.save(station);
        } else {
            return null; // Station not found
        }
    }

    /**
     * Delete Station
     *
     * Deletes a station from the database based on its unique identifier.
     *
     * @param id The unique identifier of the station to be deleted.
     */
    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }

    /**
     * Get Station by Name
     *
     * Retrieves a station by its name.
     *
     * @param name The name of the station to retrieve.
     * @return The Station object corresponding to the specified name, or null if not found.
     */
    public Station getStationByName(String name) {
        Optional<Station> optionalStation = stationRepository.findStationByName(name);
        return optionalStation.orElse(null);
    }
}