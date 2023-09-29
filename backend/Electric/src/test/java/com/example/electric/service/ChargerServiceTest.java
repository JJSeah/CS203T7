package com.example.electric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.electric.error.ErrorCode;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.respository.ChargerRepository;
import com.example.electric.respository.StationRepository;

@ExtendWith(MockitoExtension.class)
public class ChargerServiceTest {

    @Test
    void testName() {
        
    }

    @Mock
    private ChargerRepository chargerRepository;

    @Mock 
    private StationRepository stationRepository;


    @InjectMocks
    private ChargerService chargerService;

    @Test
    void getAllChargers_ReturnALL() {
        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger((long) 1));
        chargers.add(new Charger((long) 2));

        when(chargerRepository.findAll()).thenReturn(chargers);
        List<Charger> result = chargerService.getAllChargers();

        assertEquals(chargers, result);
        verify(chargerRepository, times(1)).findAll();
    }

    @Test
    void addCharger() {
        Charger charger = new Charger((long) 1);
        when(chargerRepository.save(any(Charger.class))).thenReturn(charger);

        Long idResult = chargerService.addCharger(charger);
        assertEquals(1, idResult);
        verify(chargerRepository).save(charger);
    }

    @Test
    void testDeleteCharger_ChargerExist() {
        Optional<Charger> charger = Optional.of(new Charger((long) 1));
        when(chargerRepository.existsById((long) 1)).thenReturn(true);
        when(chargerRepository.findById((long) 1)).thenReturn(charger);

        Optional<Charger> result = chargerService.deleteCharger(1);

        assertEquals(charger, result);
        verify(chargerRepository).existsById(anyLong());
        verify(chargerRepository).findById((long) 1);
    }

    @Test
    void testDeleteCharger_ChargerNotExist() {

        when(chargerRepository.existsById((long)1)).thenReturn(false);
        Optional<Charger> result = chargerService.deleteCharger(1);
        
        assertEquals(null, result);
        verify(chargerRepository).existsById(anyLong());
        verify(chargerRepository, never()).findById((long)1);
    }


    @Test
    void testGetChargerById_NonExist() {
        when(chargerRepository.findById((long)1)).thenReturn(null);

        Optional<Charger> result = chargerService.getChargerById((long)1);

        assertEquals(null, result);
        verify(chargerRepository).findById((long)1);
    }

    @Test
    void testGetChargerById_Exist() {
        Optional<Charger> charger = Optional.of(new Charger((long) 1));

        when(chargerRepository.findById((long) 1)).thenReturn(charger);

        Optional<Charger> result = chargerService.getChargerById((long) 1);

        assertEquals(charger, result);
        verify(chargerRepository).findById((long) 1);
    }

        public List<Charger> getChargersByStation(long stationId) {
        if(stationRepository.findById(stationId).isEmpty()){
            throw new ObjectNotFoundException(ErrorCode.E1002);
        }
        return chargerRepository.findChargersByStationId(stationId);
    }

    @Test
    void testGetChargersByStation_stationExist() {
        List<Charger> chargers = new ArrayList<Charger>();
        Station station = new Station((long)1, "Station1");
        chargers.add(new Charger((long)1, "Charger1", station));
        chargers.add(new Charger((long)2, "Charger2", station));

        when(stationRepository.findById(anyLong())).thenReturn(Optional.of(station));
        when(chargerRepository.findChargersByStationId(anyLong())).thenReturn(chargers);
        // List<Charger> result = chargerRepository.findChargersByStationId((long)1);
        List<Charger> result = chargerService.getChargersByStation(1L);
        
        assertEquals(chargers, result);
    }

    @Test
    public void testGetChargersByStation_StationNotFound() {
        long stationId = 1L;

        // Mock the behavior of stationRepository.findById
        when(stationRepository.findById(stationId)).thenReturn(Optional.empty());

        // Expect an ObjectNotFoundException to be thrown
        assertThrows(ObjectNotFoundException.class, () -> {
            chargerService.getChargersByStation(stationId);
        });

        // Verify that chargerRepository.findChargersByStationId is not called
        verify(chargerRepository, never()).findChargersByStationId(stationId);
    }



    //Something werid with chargerService.updateChargerMethod!
    // @Test
    // void testUpdateChargerExistID() {
    //     Charger updatedCharger = new Charger((long)2, "ChargerHappy");


    //     when(chargerRepository.existsById((long)1)).thenReturn(true);
    //     when(chargerRepository.save(updatedCharger)).thenReturn(updatedCharger);


    //     Optional<Charger> result = ChargerService.updateCharger(updatedCharger, (long)1);
    //     assertEquals(updatedCharger, result);
    //     verify(chargerRepository).existsById((long)2);
    //     verify(updatedCharger).setId((long)1);
    //     verify(chargerRepository).save(updatedCharger);
    // }
    // @Test
    // void testUpdateChargerNotxistID() {
    //     Charger updatedCharger = new Charger((long)1, "ChargerHappy");


    //     when(chargerRepository.existsById(anyLong())).thenReturn(false);
    //     when(chargerRepository.save(updatedCharger)).thenReturn(updatedCharger);


    //     Optional<Charger> result = ChargerService.updateCharger(updatedCharger, (long)1);
    //     assertNull(result);
    //     verify(chargerRepository).existsById((long)2);
    //     verify(chargerRepository, never()).save(updatedCharger);
    // }
    }


