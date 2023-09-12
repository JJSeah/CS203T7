package com.example.electric.service;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceMatrixService {
    @Value("${google.maps.api.key}")
    private String apiKey;

    public DistanceMatrix getDistance(String origin, String destination) throws Exception {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        return DistanceMatrixApi.newRequest(context)
                .origins(origin)
                .destinations(destination)
                .await(); // This will make the API request and return the result.
    }
}
