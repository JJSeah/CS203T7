import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import * as Location from 'expo-location'

export default HomeScreenViewController = ( { navigation } ) => {
    
    const { setUserCoordinates, currentCar } = useContext(UserContext);

    const addCarButtonPressed = () => {
        console.log("Add car button pressed")
        navigation.navigate("AddCarScreen")
    }
    
    const automateBookingButtonPressed = () => {
        console.log("Automate booking button pressed")
        getCurrentLocation()
    }
    
    const getCurrentLocation = async() => {
        setUserCoordinates(null)

        let { status } = await Location.requestForegroundPermissionsAsync();
        
        if ( status !== 'granted') {
          console.log("Please grant location permissions")
        } else {
            console.log("Getting current locations")
            let currentLocation = await Location.getCurrentPositionAsync({});
            setUserCoordinates(currentLocation.coords)
            console.log(currentLocation.coords)
        }
        navigation.navigate("AutomateBookingScreen")
    }

    const manualBookingButtonPressed = () => {
        navigation.navigate("ManualBookingScreen", {currentCar: currentCar})
    }


    return {
        addCarButtonPressed,
        automateBookingButtonPressed,
        manualBookingButtonPressed
    };

}