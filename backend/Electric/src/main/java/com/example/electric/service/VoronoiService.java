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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Service
public class VoronoiService implements VoronoiServiceInter {
    @Autowired
    private StationService stationService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DistanceMatrixService distanceMatrixService;

    @Autowired
    private UserService userService;

    /**
     * Find Closest Charging Station
     *
     * This method determines the closest available charging station based on a specified location
     * (latitude and longitude) using Voronoi Diagrams. The function first calculates the current time
     * rounded to the nearest 5-minute interval and defines a time window for station availability.
     * It then retrieves a list of stations available during this time period and extracts their
     * coordinates.
     *
     * Next, it uses Delaunay triangulation to create a Voronoi diagram based on the station coordinates.
     * The method calculates the Voronoi cell associated with the input location and identifies the
     * station closest to this cell.
     *
     * @param latitude The latitude coordinate of the user's location.
     * @param longitude The longitude coordinate of the user's location.
     * @return The Station object representing the closest available charging station or null if no
     * stations are available.
     */
    public Station findClosestStation(double latitude, double longitude) {
        //Get current time rounded to 5 minutes
        LocalTime currentTime = LocalTime.now();
        int minute = currentTime.getMinute();
        int roundedMinute = (minute / 5) * 5;

        LocalTime roundedStartTime = currentTime.withMinute(roundedMinute).withSecond(00);
        String startTime = roundedStartTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        //Calculate end time
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = start.plus(3, ChronoUnit.HOURS);
        String endTime = end.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        //Get current date
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //Use Voronoi Diagrams to find closest station
        List<Station> availableStations = appointmentService.getAvailableStationsAndChargers(startTime,endTime,date);
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
        }
        return closestStation;
    }

    /**
     * Find Cell
     *
     * Finds the Voronoi cell associated with a specific station coordinate in the Voronoi diagram.
     *
     * @param diagram The Voronoi diagram as a Geometry object.
     * @param stationCoordinate The coordinate of the station for which to find the cell.
     * @return The Polygon representing the Voronoi cell associated with the station.
     */
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

    /**
     * Find Within Cell
     *
     * Finds the coordinate within a Voronoi cell that is closest to the given coordinates.
     *
     * @param cell The Voronoi cell represented as a Polygon.
     * @param coordinates An array of coordinates to search for within the cell.
     * @return The Coordinate within the cell that is closest to the given coordinates.
     */
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

    /**
     * Auto-book Appointment
     *
     * Automatically books a charging appointment for a user based on location, time, and car details.
     *
     * @param latitude The latitude coordinate of the location.
     * @param longitude The longitude coordinate of the location.
     * @param startTime The start time of the appointment.
     * @param endTime The end time of the appointment.
     * @param dateNow The current date.
     * @param car The Car object for which the appointment is made.
     * @param userEmail The email address of the user making the appointment.
     * @return The Appointment object representing the booked appointment.
     */

    public Appointment autobookAppointment(double latitude, double longitude, String startTime, String endTime, String dateNow, Car car, String userEmail) {
        //Find station
        Station closestStation = this.findClosestStation(latitude,longitude);

        //Find date and time
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        Duration duration = Duration.between(start,end);
        LocalTime timeBetween = LocalTime.MIDNIGHT
                .plusHours(duration.toHours())
                .plusMinutes(duration.toMinutesPart())
                .plusSeconds(duration.toSecondsPart());

        //Find cost
        double cost = distanceMatrixService.calculateCostOfCharging(car);

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

