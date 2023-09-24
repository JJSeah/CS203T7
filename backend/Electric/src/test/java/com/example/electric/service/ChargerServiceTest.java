package com.example.electric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.charThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.electric.model.Charger;
import com.example.electric.model.Station;
import com.example.electric.respository.ChargerRepository;
import com.example.electric.respository.StationRepository;

@ExtendWith(MockitoExtension.class)
public class ChargerServiceTest {

    @Mock
    private ChargerRepository chargerRepository;


    @InjectMocks
    private ChargerService ChargerService;

    @Test
    void getAllChargers_ReturnALL() {
        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger((long) 1));
        chargers.add(new Charger((long) 2));

        when(chargerRepository.findAll()).thenReturn(chargers);
        List<Charger> result = ChargerService.getAllChargers();

        assertEquals(chargers, result);
        verify(chargerRepository, times(1)).findAll();
    }

    @Test
    void addCharger() {
        Charger charger = new Charger((long) 1);
        when(chargerRepository.save(any(Charger.class))).thenReturn(charger);

        Long idResult = ChargerService.addCharger(charger);
        assertEquals(1, idResult);
        verify(chargerRepository).save(charger);
    }

    @Test
    void testDeleteCharger_ChargerExist() {
        Optional<Charger> charger = Optional.of(new Charger((long) 1));
        when(chargerRepository.existsById((long) 1)).thenReturn(true);
        when(chargerRepository.findById((long) 1)).thenReturn(charger);

        Optional<Charger> result = ChargerService.deleteCharger(1);

        assertEquals(charger, result);
        verify(chargerRepository).existsById(anyLong());
        verify(chargerRepository).findById((long) 1);
    }

    @Test
    void testDeleteCharger_ChargerNotExist() {

        when(chargerRepository.existsById((long)1)).thenReturn(false);
        Optional<Charger> result = ChargerService.deleteCharger(1);
        
        assertEquals(null, result);
        verify(chargerRepository).existsById(anyLong());
        verify(chargerRepository, never()).findById((long)1);
    }


    @Test
    void testGetChargerByIdNonExist() {
        when(chargerRepository.findById((long)1)).thenReturn(null);

        Optional<Charger> result = ChargerService.getChargerById((long)1);

        assertEquals(null, result);
        verify(chargerRepository).findById((long)1);
    }

    @Test
    void testGetChargerByIdExist() {
        Optional<Charger> charger = Optional.of(new Charger((long) 1));

        when(chargerRepository.findById((long) 1)).thenReturn(charger);

        Optional<Charger> result = ChargerService.getChargerById((long) 1);

        assertEquals(charger, result);
        verify(chargerRepository).findById((long) 1);
    }

    public List<Charger> getChargersByStation(long stationId) {
        return chargerRepository.findChargersByStationId(stationId);
    }

    @Test
    void testGetChargersByStation() {
        List<Charger> chargers = new ArrayList<Charger>();
        Station station = new Station((long)1, "Station1");
        chargers.add(new Charger((long)1, "Charger1", station));
        chargers.add(new Charger((long)2, "Charger2", station));

        when(chargerRepository.findChargersByStationId(anyLong())).thenReturn(chargers);
        List<Charger> result = chargerRepository.findChargersByStationId((long)1);
        
        assertEquals(chargers, result);
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


