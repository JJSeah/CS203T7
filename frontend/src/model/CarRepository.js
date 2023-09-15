import React, { createContext, useContext, useEffect, useState } from 'react';
import * as SecureStore from "expo-secure-store"
import axios from "axios";
import { BASE_URL } from '../constants/Config';
import { UserContext } from './User';


export const CarRepository = ( ) => {

    const { userData } = useContext(UserContext);

    const addCarToBackend = () => {
        
        console.log("adding car to api")
    }

    return { addCarToBackend }; 

}