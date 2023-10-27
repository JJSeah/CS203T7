package com.example.electric.service;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Charger;
import com.example.electric.respository.ChargerRepository;
import com.example.electric.respository.StationRepository;
import com.example.electric.service.inter.ChargerServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargerService implements ChargerServiceInter {
    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired
    public StationRepository stationRepository;

    /**
     * Get All Chargers
     *
     * This method retrieves a list of all chargers stored in the system.
     *
     * @return A list of all chargers in the system.
     */
    public List<Charger> getAllChargers() {
        return chargerRepository.findAll();
    }

    /**
     * Get Chargers by Station
     *
     * This method retrieves a list of chargers associated with a specific charging station based on the station's unique identifier.
     * If the station with the given ID is not found, it throws an ObjectNotFoundException.
     *
     * @param stationId The unique identifier of the charging station.
     * @return A list of chargers associated with the station.
     * @throws ObjectNotFoundException If the station with the specified ID is not found.
     */
    public List<Charger> getChargersByStation(long stationId) {
        if(stationRepository.findById(stationId).isEmpty()){
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return chargerRepository.findChargersByStationId(stationId);
    }

    /**
     * Get Charger by ID
     *
     * This method retrieves a charger by its unique identifier (ID).
     *
     * @param id The unique identifier of the charger to retrieve.
     * @return An optional containing the charger, or empty if the charger is not found.
     */
    public Optional<Charger> getChargerById(long id) {
        return chargerRepository.findById(id);
    }

    /**
     * Add Charger
     *
     * This method allows the addition of a new charger to the system. It returns the unique identifier of the newly added charger.
     *
     * @param charger The charger to be added to the system.
     * @return The unique identifier of the newly added charger.
     */

    public long addCharger(Charger charger) {
        chargerRepository.save(charger);
        return charger.getId();
    }

    /**
     * Update Charger
     *
     * This method allows the update of an existing charger based on the provided updated charger details.
     * It identifies the charger by its unique identifier (ID) and updates its fields as needed.
     *
     * @param updatedCharger The updated charger details.
     * @param chargerId The unique identifier of the charger to update.
     * @return The updated charger after the changes are applied, or null if the charger is not found.
     */
    public Charger updateCharger(Charger updatedCharger, long chargerId) {
        Optional<Charger> OptionalCharger = chargerRepository.findById(chargerId);

        if (OptionalCharger.isPresent()) {
            Charger exisitinCharger = OptionalCharger.get();
            if(updatedCharger.getCharId() != null){
                exisitinCharger.setCharId(updatedCharger.getCharId());
            }
            if(updatedCharger.getName() != null){
                exisitinCharger.setName(updatedCharger.getName());
            }
            if(updatedCharger.getAvail() != exisitinCharger.getAvail()){
                exisitinCharger.setCharId(updatedCharger.getCharId());
            }
            if(updatedCharger.getType() != null){
                exisitinCharger.setType(updatedCharger.getType());
            }            
            if(updatedCharger.getChargingRate() != 0.0){
                exisitinCharger.setChargingRate(updatedCharger.getChargingRate());
            }
            if(updatedCharger.getStation() != null){
                exisitinCharger.setStation(updatedCharger.getStation());
            }
            return chargerRepository.save(exisitinCharger);
        } else {
            return null; // charger not found
        }
    }

    /**
     * Delete Charger
     *
     * This method allows the deletion of a charger based on its unique identifier (ID). It returns an optional containing
     * the deleted charger, or null if the charger is not found.
     *
     * @param chargerId The unique identifier of the charger to delete.
     * @return An optional containing the deleted charger or null if the charger is not found.
     */
    public Optional<Charger> deleteCharger(long chargerId) {
        if (!chargerRepository.existsById(chargerId)) {
            return null;
        }
        Optional<Charger> deleted = chargerRepository.findById(chargerId);
        chargerRepository.deleteById(chargerId);
        return deleted;
    }


}
