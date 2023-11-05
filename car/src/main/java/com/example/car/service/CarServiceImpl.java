package com.example.car.service;

import com.example.car.models.CarDetails;
import com.example.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDetails> getAllCars() {
        return carRepository.findAll();
    }

    public CarDetails getCarByCarId(long id) {
        return carRepository.findByCarId(id);
    }

    public CarDetails addCar(CarDetails car) {
        return carRepository.save(car);
    }

    public CarDetails updateCar(CarDetails car) {
        return carRepository.save(car);
    }

    public ResponseEntity<String> deleteCar(long id) {
        carRepository.deleteById(id);
        return new ResponseEntity<>("Car has been deleted!", HttpStatus.OK);
    }

    public double getCarStatus(long id) {
        CarDetails car = carRepository.findByCarId(id);
        return car.getBattery();

//        HashMap<String,Object> status = new HashMap<String,Object>();
//        status.put("batteryPercentage", car.getBattery());
//        System.out.println(status);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(status, headers);
//
//        String URL = "http://localhost:8080/api/car/battery/" + car.getCarId();
//        RestTemplate restTemplate = new RestTemplate();
//        System.out.println(requestEntity);
//        try {
//            ResponseEntity<Map> response = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, Map.class);
//        } catch (HttpClientErrorException e) {
//            // Handle HTTP 4xx errors (e.g., 400 Bad Request, 404 Not Found, etc.)
//            HttpStatus statusCode = (HttpStatus) e.getStatusCode();
//            String responseBody = e.getResponseBodyAsString();
//        } catch (HttpServerErrorException e) {
//            // Handle HTTP 5xx errors (e.g., 500 Internal Server Error)
//            HttpStatus statusCode = (HttpStatus) e.getStatusCode();
//            String responseBody = e.getResponseBodyAsString();
//        } catch (RestClientException e) {
//        } catch (Exception e) {
//        }


    }

    //    update battery as time passes
    @Scheduled(fixedRate = 60000) // 60000 milliseconds = 1 minute
    public void updateBattery() {
        try {
            List<CarDetails> cars = carRepository.findAll(); // Retrieve all cars or filter as needed
            for (CarDetails car : cars) {
                updateBatteryForCar(car);
            }
        } catch (Exception e) {
            // Handle exceptions appropriately, e.g., log the error or send notifications.
            e.printStackTrace();
        }
    }

    private void updateBatteryForCar(CarDetails car) {
        if (car.getBattery() > 100.0 || car.getBattery() < 0.0) {
            return;
        }
        else if (car.getStatus().equals("charging")) {
            car.setBattery(car.getBattery() + 4.0);
        } else if (car.getStatus().equals("driving")) {
            car.setBattery(car.getBattery() - 2.0);
        } else {
            car.setBattery(car.getBattery() - 0.1);
        }
        carRepository.save(car);
        // Optionally, log the update for monitoring and debugging
        System.out.println("Updated battery for car: " + car.getCarId() + ", New Battery Level: " + car.getBattery());
        getCarStatus(car.getCarId());

    }

    //update car status
    public void updateCarStatus(CarDetails car, String status) {
        car.setStatus(status);
        carRepository.save(car);
    }


    public CarDetails start(long id) {
        CarDetails car = carRepository.findByCarId(id);
        updateCarStatus(car, "charging");
        return carRepository.save(car);
    }

    public CarDetails stop(long id) {
        CarDetails car = carRepository.findByCarId(id);
        updateCarStatus(car, "driving");
        return carRepository.save(car);
    }
}
