import React, { createContext, useEffect, useState, useContext } from 'react';
import axios from "axios";
import { BASE_URL } from '../constants/Config';
import { UserContext } from './User';

export const MapContext = createContext();

export const MapProvider = ( { children } ) => {

    const [ stations, setStations ] = useState(null)

    const getAllLocations = () => {
        axios.get(`${BASE_URL}/api/stations/all`)
        .then ( res => {
            let data = res.data
            console.log(data)
            setStations(data)
        })
        .catch( e => {
            console.log(`Error loading stations ${e}`)
        })
    }

    useEffect(() => {
        getAllLocations()
    }, []);

    return (
        <MapContext.Provider 
            value={{ stations }}
        >
            { children }
        </MapContext.Provider>
    );
}