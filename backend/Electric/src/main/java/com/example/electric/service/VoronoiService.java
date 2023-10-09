package com.example.electric.service;

import com.example.electric.model.*;
import com.example.electric.service.inter.VoronoiServiceInter;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class  VoronoiService implements VoronoiServiceInter {
    @Autowired
    private StationService stationService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DistanceMatrixService distanceMatrixService;

    @Autowired
    private UserService userService;


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

//            LocalTime start = LocalTime.parse(startTime);
//            LocalTime end = LocalTime.parse(endTime);
//            Duration duration = Duration.between(start,end);
//            LocalTime timeBetween = LocalTime.MIDNIGHT
//                    .plusHours(duration.toHours())
//                    .plusMinutes(duration.toMinutesPart())
//                    .plusSeconds(duration.toSecondsPart());
//
//            Appointment appointment = new Appointment(1,
//                    Time.valueOf(timeBetween),
//                    Time.valueOf(start),
//                    Time.valueOf(end),
//                    java.sql.Date.valueOf(dateNow),);
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

    public Appointment autobookAppointment(double latitude, double longitude, String startTime, String endTime, String dateNow, Car car, String userEmail) {
        //Find station
        Station closestStation = this.findClosestStation(latitude,longitude,startTime,endTime,dateNow);

        //Find date and time
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        Duration duration = Duration.between(start,end);
        LocalTime timeBetween = LocalTime.MIDNIGHT
                .plusHours(duration.toHours())
                .plusMinutes(duration.toMinutesPart())
                .plusSeconds(duration.toSecondsPart());

        //Find cost
        Double cost = Double.parseDouble(distanceMatrixService.calculateCostOfCharging(car));

        //Find user
        User user = userService.getUserByEmail(userEmail);

        //Find charger
        List<Charger> chargers = closestStation.getChargers();

        //Appointment object
        Appointment appointment = new Appointment(1,
                Time.valueOf(timeBetween),
                Time.valueOf(start),
                Time.valueOf(end),
                java.sql.Date.valueOf(dateNow),
                cost,
                "Active",
                closestStation,
                user,
                chargers.get(0));

        return appointmentService.addAppointment(appointment);
    }

}

