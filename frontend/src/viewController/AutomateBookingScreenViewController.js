import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { BASE_URL } from "../constants/Config";

export default AutomateBookingScreenViewController = ( { navigation } ) => {

    const { closestStation, setClosestStation, setUpcomingAppointment } = useContext(UserContext);

    useEffect(() => {

        if (closestStation !== null) {
            loadDetailsOfUpcomingAppointment();
        }

    }, [closestStation])

    const { userToken, userId, userCoordinates, currentCar } = useContext(UserContext)

    const findClosestStation = async(latitude, longitude) => {

        setClosestStation(null)
        setUpcomingAppointment(null)

        console.log(`the latitude is ${latitude} and longitude is ${longitude}`)

        let url = `${BASE_URL}/api/stations/closest`

        axios.post(url, {
            latitude,
            longitude
        }, 
        {
            headers : { Authorization : `Bearer ${userToken}` }
        }        
        ).then(res => {
            let data = res.data
            setClosestStation(data)
        }).catch(e => {
            console.log(`Error finding closest station ${e}`)
        })
    }

    const loadDetailsOfUpcomingAppointment = async() => {

        let stationId = closestStation.id

        let url = `${BASE_URL}/api/stationCheck/${userId}/${currentCar.id}`

        axios.post(url, {
            "latitude": userCoordinates.latitude,
            "longitude": userCoordinates.longitude,
            "id": stationId
        },
        {
            headers : { Authorization : `Bearer ${userToken}` }
        }        
        ).then(res => {
            let data = res.data
            console.log(`The car used to book is ${currentCar.id}, nickname ${currentCar.nickname}`)
            setUpcomingAppointment(data)
        }).catch(e => {
            console.log(`Error loading details of upcoming appointment ${e}`)
        })
    }

    const cancleButtonPressed = () => {
        navigation.pop();
    } 

    const confirmButtonPressed = () => {
        console.log("making appointment")
    }
    
    return {
        cancleButtonPressed,
        confirmButtonPressed,
        findClosestStation,
    };

}