package com.example.electric.service;

import com.example.electric.model.Station;
import com.example.electric.respository.StationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DistanceMatrixServiceTest {
    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private DistanceMatrixService distanceMatrixService;

    @Value("${google.maps.api.key}")
    private String apiKey;

    @Test
    public void testGetDistanceMatrix_Success() throws Exception{
//        String origin = "originAddress";
//        String destination = "destinationAddress";
//
//        GeoApiContext.Builder builder = mock(GeoApiContext.Builder.class);
//        GeoApiContext geoApiContext = mock(GeoApiContext.class);
//        DistanceMatrixApi distanceMatrixApi = mock(DistanceMatrixApi.class);
//        DistanceMatrixApiRequest distanceMatrixApiRequest = mock(DistanceMatrixApiRequest.class);
//        DistanceMatrix distanceMatrix = mock(DistanceMatrix.class);
//
//        when(builder.apiKey(apiKey)).thenReturn(builder);
//        when(builder.build()).thenReturn(geoApiContext);
//        when(distanceMatrixApi.newRequest(geoApiContext)).thenReturn(distanceMatrixApiRequest);
//        when(distanceMatrixApiRequest.origins(origin)).thenReturn(distanceMatrixApiRequest);
//        when(distanceMatrixApiRequest.destinations(destination)).thenReturn(distanceMatrixApiRequest);
//        when(distanceMatrixApiRequest.await()).thenReturn(distanceMatrix);
//
//        DistanceMatrix result = distanceMatrixService.getDistanceMatrix(origin, destination);
//
//        assertSame(distanceMatrix, result);
    }

//    @Test
//    public void testGetDistanceMatrix_Failure() throws Exception {
//        String origin = "OriginAddress";
//        String destination = "DestinationAddress";
//
//        GeoApiContext geoApiContext = mock(GeoApiContext.class);
//        DistanceMatrix distanceMatrix = new DistanceMatrix(null,null,null);
//
//        when(DistanceMatrixApi.newRequest(geoApiContext)
//                .origins(origin)
//                .destinations(destination)
//                .await()).thenReturn(distanceMatrix);
//
//        assertThrows(Exception.class, () -> {
//            distanceMatrixService.getDistanceMatrix(origin,destination);
//        });
//    }

//    @Test
//    public void testGetDistanceByID_Success() throws Exception {
//        // Mock stationRepository to return a station
//        Station station = new Station();
//        station.setId(1L);
//        station.setLatitude(12.34);
//        station.setLongitude(56.78);
//        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
//
//        // Mock DistanceMatrixService to return a DistanceMatrix
//        DistanceMatrix distanceMatrix = mock(DistanceMatrix.class);
//        DistanceMatrixRow distanceMatrixRow = mock(DistanceMatrixRow.class);
//        DistanceMatrixElement distanceMatrixElement = new DistanceMatrixElement();
//        distanceMatrixElement.distance = new Distance();
//        distanceMatrixElement.distance.inMeters = 1000;
//        when(distanceMatrixService.getDistanceMatrix(anyString(), anyString())).thenReturn(distanceMatrix);
//        when(distanceMatrix.rows).thenReturn(new DistanceMatrixRow[]{distanceMatrixRow});
//        when(distanceMatrixRow.elements).thenReturn(new DistanceMatrixElement[]{distanceMatrixElement});
//
//        // Call the method under test
//        long result = distanceMatrixService.getDistanceByID(station);
//
//        // Verify that the result is as expected
//        assertEquals(1000, result);
//    }

    @Test
    public void testGetDistanceByID_StationNotFound() throws Exception {
        long id = 1L;
        // Mock stationRepository to return null (station not found)
        when(stationRepository.findById(id)).thenReturn(Optional.empty());

        // Call the method under test, which should throw an exception
        assertThrows(Exception.class, () -> {
            distanceMatrixService.getDistanceByID(new Station());
        });
    }

//    @Test
//    public void testGetDistanceByID_DistanceMatrixError() throws Exception {
//        long id = 1L;
//        Station station = new Station();
//        station.setId(id);
//        // Mock stationRepository to return null (station not found)
//        when(stationRepository.findById(id)).thenReturn(Optional.of(station));
//
//        // Mock DistanceMatrixService to throw an exception
//        when(distanceMatrixService.getDistanceMatrix(anyString(), anyString())).thenThrow(new Exception("API error"));
//
//        // Call the method under test, which should throw an exception
//        assertThrows(Exception.class, () -> {
//            distanceMatrixService.getDistanceByID(station);
//        });
//    }

//    @Test
//    public void testCalculateEstimateTimeOfCharging() {
//        Car car = new Car(1L,"name","Tesla","GH123",80,40,60,null);
//
//        // Calculate estimated time for charging
//        int estimatedTime = distanceMatrixService.calculateEstimateTimeOfCharging(car);
//
//        // Expected result: (60 * (100 - 20)) / (60 * 60) = 1 hour
//        int expectedTime = 60;
//
//        // Assert that the calculated estimated time matches the expected time
//        assertEquals(expectedTime, estimatedTime);
//    }

//    @Test
//    public void testCalculateCostOfCharging() {
//        Car car = new Car(1L,"name","Tesla","GH123",80,40,60,null);
//
//        // Calculate estimated time for charging
//        double estimatedCost = distanceMatrixService.calculateCostOfCharging(car);
//
//        // Expected result: (60 * (100 - 40) / 100) / 15 * 10 = 0.432 USD
//        double expectedCost = 24;
//
//        // Assert that the calculated estimated time matches the expected time
//        assertEquals(expectedCost, estimatedCost);
//    }

}
