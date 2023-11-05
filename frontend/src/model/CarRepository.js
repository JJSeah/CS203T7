import React, { createContext, useContext, useEffect, useState } from "react";
import * as SecureStore from "expo-secure-store";
import axios from "axios";
import { BASE_URL } from "../constants/Config";
import { UserContext } from "./User";

export const CarRepository = () => {
  const { userToken, userId, setUserCars, setCurrentCar, userCars } =
    useContext(UserContext);

  const addCarToBackend = async(newCar, { navigation } ) => {
    let url = `${BASE_URL}/api/car/add/${userId}`;

    axios
      .post(
        url,
        {
          nickname: newCar.nickname,
          model: newCar.model,
          plate: newCar.plate,
          chargingRate: newCar.chargingRate,
          batteryPercentage: newCar.batteryPercentage,
          batteryCapacity: newCar.batteryCapacity,
        },
        {
          headers: { Authorization: `Bearer ${userToken}` },
        }
      )
      .then((res) => {
        loadCarsData()
        console.log(res.data)
        navigation.pop()
      })
      .catch((e) => {
        console.log(`Error adding car to back end ${e}`);
      });
  };

  const loadCarsData = async () => {
    let url = `${BASE_URL}/api/car/user/${userId}`;

    setUserCars(null)

    axios
      .get(url)
      .then((res) => {
        let data = res.data;

        data = data.reverse();

        setUserCars(data);

        if (data.length > 0) {
          setCurrentCar(data[0])
        } else {
          setCurrentCar(null)
        }
      })
      .catch((e) => {
        console.log(`Error adding car to back end ${e}`);
      });
  };

  // delete car method
  const deleteCar = async( id ) => {
    let url = `${BASE_URL}/api/car/${id}`;
    axios.delete(url)
    .then(() => {
      loadCarsData()
      console.log("succesfully deleted a car");
    })
    .catch((e) => {
      console.log(`Error deleting car ${e}`);
    });
};

  return { addCarToBackend, loadCarsData, deleteCar };
};
