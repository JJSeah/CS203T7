package com.example.electric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import com.example.electric.model.Station;
import com.example.electric.respository.*;

import io.jsonwebtoken.lang.Arrays;

@ExtendWith(MockitoExtension.class)
public class StationServiceTest {
    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationService stationService;

    @Test
    public void testCreateStation() {
        Station stationToCreate = new Station(1L, "Station1");

        when(stationRepository.save(stationToCreate)).thenReturn(stationToCreate);
        Station createdStation = stationService.createStation(stationToCreate);

        assertEquals(stationToCreate, createdStation);
        verify(stationRepository).save(stationToCreate);

    }

    @Test
    public void testDeleteStation() {
        Long stationIdToDelete = 1L;

        stationService.deleteStation(stationIdToDelete);

        verify(stationRepository).deleteById(stationIdToDelete);
    }

    @Test
    public void testGetAllStations() {
        List<Station> expectedStations = new ArrayList<Station>();
        expectedStations.add(new Station(1L, "Station1"));
        expectedStations.add(new Station(2L, "Station2"));
        expectedStations.add(new Station(3L, "Station3"));

        when(stationRepository.findAll()).thenReturn(expectedStations);
        List<Station> actualStations = stationService.getAllStations();

        verify(stationRepository, times(1)).findAll();
        assertEquals(expectedStations, actualStations);
    }

    @Test
    public void testGetStationByCoordinate_Found() {
        double latitude = 40.7128; // Example latitude
        double longitude = -74.0060; // Example longitude
        Station expectedStation = new Station(1L, "Station1", latitude, longitude);

        when(stationRepository.findStationByLatitudeAndLongitude(latitude, longitude))
                .thenReturn(Optional.of(expectedStation));

        Station actualStation = stationService.getStationByCoordinate(latitude, longitude);

        assertEquals(expectedStation, actualStation);
        verify(stationRepository, times(1)).findStationByLatitudeAndLongitude(latitude, longitude);

    }

    @Test
    public void testGetStationByCoordinate_NotFound() {
        double latitude = 40.7128; // Example latitude
        double longitude = -74.0060; // Example longitude

        when(stationRepository.findStationByLatitudeAndLongitude(latitude, longitude))
                .thenReturn(Optional.empty());
        Station actualStation = stationService.getStationByCoordinate(latitude, longitude);

        assertNull(actualStation);
        verify(stationRepository, times(1)).findStationByLatitudeAndLongitude(latitude, longitude);
    }

    @Test
    public void testGetStationById_Found() {
        Long stationId = 1L; // Example station ID
        Station expectedStation = new Station(stationId, "Station1");

        when(stationRepository.findById(stationId)).thenReturn(Optional.of(expectedStation));

        Station actualStation = stationService.getStationById(stationId);

        assertEquals(expectedStation, actualStation);
        verify(stationRepository, times(1)).findById(stationId);
    }

    @Test
    public void testGetStationById_NotFound() {
        Long stationId = 2L; // Example station ID that does not exist

        when(stationRepository.findById(stationId)).thenReturn(Optional.empty());
        Station actualStation = stationService.getStationById(stationId);

        verify(stationRepository, times(1)).findById(stationId);
        assertNull(actualStation);
    }

    @Test
    public void testGetStationByName_Found() {
        String stationName = "Station1"; // Example station name
        Station expectedStation = new Station(1L, stationName);

        when(stationRepository.findStationByName(stationName)).thenReturn(Optional.of(expectedStation));

        Station actualStation = stationService.getStationByName(stationName);

        verify(stationRepository, times(1)).findStationByName(stationName);
        assertEquals(expectedStation, actualStation);
    }

    @Test
    public void testGetStationByName_NotFound() {
        String stationName = "NonExistentStation"; // Example station name that does not exist

        when(stationRepository.findStationByName(stationName)).thenReturn(Optional.empty());

        Station actualStation = stationService.getStationByName(stationName);

        assertNull(actualStation);
        verify(stationRepository, times(1)).findStationByName(stationName);
    }

    @Test
    public void testUpdateStation_Found() {
        Long stationId = 1L; // Example station ID
        Station existingStation = new Station(stationId, "Station1");
        Station updatedStation = new Station(stationId, "UpdatedStation");

        when(stationRepository.findById(stationId)).thenReturn(Optional.of(existingStation));

        when(stationRepository.save(updatedStation)).thenReturn(updatedStation);

        Station actualUpdatedStation = stationService.updateStation(stationId, updatedStation);

        assertEquals(updatedStation, actualUpdatedStation);
        verify(stationRepository, times(1)).findById(stationId);
        verify(stationRepository, times(1)).save(updatedStation);

    }

    @Test
    public void testUpdateStationNotFound() {
        Long stationId = 2L; // Example station ID that does not exist
        Station updatedStation = new Station(stationId, "UpdatedStation");

        when(stationRepository.findById(stationId)).thenReturn(Optional.empty());

        Station actualUpdatedStation = stationService.updateStation(stationId, updatedStation);

        assertNull(actualUpdatedStation);
        verify(stationRepository, times(1)).findById(stationId);
        verify(stationRepository, never()).save(updatedStation);

    }
}

