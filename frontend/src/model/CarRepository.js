import React, { createContext, useContext, useEffect, useState } from 'react';
import * as SecureStore from "expo-secure-store"
import axios from "axios";
import { BASE_URL } from '../constants/Config';
import { UserContext } from './User';


export const CarRepository = ( ) => {

    const { userData, userId } = useContext(UserContext);

    const addCarToBackend = (newCar) => {
        let url = `${BASE_URL}/api/car/add/${userId}`
        
        axios.post(url, {
            "nickname": newCar.nickname,
            "model": newCar.model,
            "plate": newCar.carPlate,
            "chargingRate": newCar.chargingRate,
            "batteryPercentage": newCar.batteryPercentage,
            "batteryCapacity": newCar.batteryCapacity
        })
        .then( res => {
            console.log(res.data)
        })
        .catch(e => {
            console.log(`Error adding car to back end ${e}`)
        })

    }

    return { addCarToBackend }; 

}