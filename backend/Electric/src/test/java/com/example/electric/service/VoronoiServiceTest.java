// package com.example.electric.service;

// import com.example.electric.model.Station;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.locationtech.jts.geom.*;
// import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.util.Arrays;
// import java.util.Collections;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// public class VoronoiServiceTest {
//     @InjectMocks
//     private  VoronoiService voronoiService;

//     @Mock
//     private StationService stationService;

//     @Test
//     public void testFindClosestStation() {
//         double latitude = 1.2851899088339263;
//         double longitude = 103.85175465425507;

//         Station station1 = new Station(1L,"Station 1",null,1.2851899088339263, 103.85175465425507,true,null);
//         Station station2 = new Station(2L,"Station 2",null,1.2868078503897311, 103.827125448267,true,null);
//         Station station3 = new Station(3L,"Station 3",null,1.2818433518638552, 103.84478091081829,true,null);

//         when(stationService.getAllStations()).thenReturn(Arrays.asList(station1, station2, station3));
//         when(stationService.getStationByCoordinate(1.2851899088339263,103.85175465425507)).thenReturn(station1);

//         Station result = voronoiService.findClosestStation(latitude, longitude);

//         assertEquals(station1,result);
//     }

//     @Test
//     public void testFindClosestStationNoStationsAvailable() {
//         // Mock the behavior of the StationService to return an empty list of stations
//         when(stationService.getAllStations()).thenReturn(Collections.emptyList());

//         // Test with input coordinates
//         double latitude = 1.0;
//         double longitude = 1.0;

//         // Call the method under test
//         Station closestStation = voronoiService.findClosestStation(latitude, longitude);

//         // Verify the result
//         assertNull(closestStation, "No stations available, should return null");
//     }

//     @Test
//     public void testFindCellWithGeometryCollection() {
//         // Create a Geometry that represents a single polygon
//         GeometryFactory factory = new GeometryFactory();
//         Coordinate[] polygonCoordinates = new Coordinate[] {
//                 new Coordinate(0, 0),
//                 new Coordinate(1, 0),
//                 new Coordinate(1, 1),
//                 new Coordinate(0, 1),
//                 new Coordinate(0, 0)
//         };
//         Polygon singlePolygon = factory.createPolygon(polygonCoordinates);
//         GeometryCollection diagram = factory.createGeometryCollection(new Geometry[]{singlePolygon});

//         // Create a stationCoordinate that is inside the single polygon
//         Coordinate stationCoordinate = new Coordinate(0.5, 0.5);

//         // Call the findCell method with the single polygon and stationCoordinate
//         Polygon result = voronoiService.findCell(diagram, stationCoordinate);

//         // Assert that the result should be the same as the single polygon
//         assertEquals(singlePolygon, result);
//     }

//     @Test
//     public void testFindCellWithNonGeometryCollection() {
//         // Create a mock object that simulates a non-GeometryCollection input
//         Geometry diagram = mock(Geometry.class);

//         // Create a stationCoordinate
//         Coordinate stationCoordinate = new Coordinate(1.0, 1.0);

//         // Call the findCell method with the mock diagram
//         Polygon result = voronoiService.findCell(diagram, stationCoordinate);

//         // Assert that the result should be null or handle it accordingly
//         assertEquals(null, result);
//     }

//     @Test
//     public void testFindWithinCell_WhenStationIsInsideCell() {
//         Polygon cell = mock(Polygon.class);
//         Coordinate[] coordinates = {
//                 new Coordinate(1, 2),
//                 new Coordinate(3, 4)
//         };

//         when(cell.contains(new GeometryFactory().createPoint(new Coordinate(1,2)))).thenReturn(false);
//         when(cell.contains(new GeometryFactory().createPoint(new Coordinate(3,4)))).thenReturn(true);
//         when(cell.distance(any())).thenReturn(0.0);

//         Coordinate result = voronoiService.findWithinCell(cell, coordinates);

//         assertEquals(new Coordinate(3, 4), result);
//     }

//     @Test
//     public void testFindWithinCell_WhenStationIsOutsideCell() {
//         Polygon cell = mock(Polygon.class);
//         Coordinate[] coordinates = {
//                 new Coordinate(5, 6),
//                 new Coordinate(7, 8)
//         };

//         when(cell.contains(any())).thenReturn(false);

//         Coordinate result = voronoiService.findWithinCell(cell, coordinates);

//         // Assert that null is returned when no station is inside the cell
//         assertNull(result);
//     }
// }
