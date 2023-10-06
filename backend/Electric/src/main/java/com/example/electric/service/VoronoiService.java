package com.example.electric.service;

import com.example.electric.model.Station;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VoronoiService {
    @Autowired
    private StationService stationService;

    @Autowired
    private AppointmentService appointmentService;

    public Station findClosestStation(double latitude, double longitude, String startTime, String endTime, String dateNow) {
//        Station[] stations = stationService.getAllStations().toArray(new Station[0]);
        List<Station> availableStations = appointmentService.getAvailableStationsAndChargers(startTime,endTime,dateNow);
        Coordinate[] stationCoordinates = new Coordinate[availableStations.size()];
        //Mapped into stationCoordinates
        for (int i = 0; i < availableStations.size(); i++) {
            Station station = availableStations.get(i);
            Coordinate coordinate = new Coordinate(station.getLatitude(), station.getLongitude());
            stationCoordinates[i] = coordinate;
        }

        // Create a DelaunayTriangulationBuilder
        DelaunayTriangulationBuilder delaunayBuilder = new DelaunayTriangulationBuilder();
        delaunayBuilder.setSites(Arrays.asList(stationCoordinates));

        // Get the Delaunay triangulation
        Geometry delaunayTriangulation = delaunayBuilder.getTriangles(new GeometryFactory());

        // Create a VoronoiDiagramBuilder
        VoronoiDiagramBuilder voronoiBuilder = new VoronoiDiagramBuilder();
        voronoiBuilder.setSites(delaunayTriangulation);

        Geometry diagram = voronoiBuilder.getDiagram(new GeometryFactory());

        Polygon cell = findCell(diagram, new Coordinate(latitude, longitude));
        Coordinate closestStationCoordinates = findWithinCell(cell, stationCoordinates);
        Station closestStation = null;

        try {
            closestStation = stationService.getStationByCoordinate(closestStationCoordinates.getX(), closestStationCoordinates.getY());
        } catch (NullPointerException e) {
            System.out.println("No stations available, should return null");
        } finally {
            return closestStation;
        }
    }

    public Polygon findCell(Geometry diagram, Coordinate stationCoordinate) {
        GeometryFactory factory = new GeometryFactory();
//        Coordinate[] coords = new Coordinate[]{stationCoordinate};
        Geometry stationPoint = factory.createPoint(stationCoordinate);
        GeometryCollection polygons = factory.createGeometryCollection();

        // Find the cell associated with the station by finding the closest polygon in the Voronoi diagram
        try {
            polygons = (GeometryCollection) diagram;
        } catch (ClassCastException e) {
            System.out.println("diagram is not a GeometryCollection");
        }

        double minDistance = Double.POSITIVE_INFINITY;
        Polygon nearestCell = null;

        for (int i = 0; i < polygons.getNumGeometries(); i++) {
            Polygon cell = (Polygon) polygons.getGeometryN(i);
            double distance = DistanceOp.distance(stationPoint, cell);
            if (distance < minDistance) {
                minDistance = distance;
                nearestCell = cell;
            }
        }

        return nearestCell;
    }

    public Coordinate findWithinCell(Polygon cell, Coordinate[] coordinates) {
        Coordinate nearestStation = null;
        double minDistance = Double.MAX_VALUE;

        for (Coordinate coordinate : coordinates) {
            // Check if the hospital coordinate is inside the Voronoi cell
            if (cell.contains(new GeometryFactory().createPoint(coordinate))) {
                // Calculate the distance between the station and the hospital
                double distance = cell.distance(new GeometryFactory().createPoint(coordinate));
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestStation = coordinate;
                }
            }
        }

        return nearestStation;
    }

}

