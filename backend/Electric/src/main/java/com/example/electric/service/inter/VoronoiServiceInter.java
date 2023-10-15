package com.example.electric.service.inter;

import com.example.electric.model.Appointment;
import com.example.electric.model.Car;
import com.example.electric.model.Station;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

public interface VoronoiServiceInter {
    Station findClosestStation(double latitude, double longitude)
            throws Exception;
    Polygon findCell(Geometry diagram, Coordinate stationCoordinate);
    Coordinate findWithinCell(Polygon cell, Coordinate[] coordinates);
    Appointment autobookAppointment(double latitude, double longitude, String startTime, String endTime, String dateNow, Car car, String userEmail) throws Exception;



}
