package com.example.electric.service;
import com.example.electric.model.Station;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class VoronoiService {
    @Autowired
    private StationService stationService;

    public Station findClosestStation(double latitude, double longitude) {
        Station[] stations = stationService.getAllStations().toArray(new Station[0]);
        Coordinate[] stationCoordinates = new Coordinate[stations.length];
        //Mapped into stationCoordinates
        for (int i = 0; i < stations.length; i++) {
            Station station = stations[i];
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
        Coordinate nearestStation = findWithinCell(cell, stationCoordinates);
        return stationService.getStationByCoordinate(nearestStation.getX(), nearestStation.getY());
    }

    public Polygon findCell(Geometry diagram, Coordinate stationCoordinate) {
        GeometryFactory factory = new GeometryFactory();
//        Coordinate[] coords = new Coordinate[]{stationCoordinate};
        Geometry stationPoint = factory.createPoint(stationCoordinate);

        // Find the cell associated with the station by finding the closest polygon in the Voronoi diagram
        GeometryCollection polygons = (GeometryCollection) diagram;
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

