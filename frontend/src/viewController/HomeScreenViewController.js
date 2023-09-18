import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import * as Location from 'expo-location'

export default HomeScreenViewController = ( { navigation } ) => {
    
    const { setCoordinates } = useContext(UserContext);

    const addCarButtonPressed = () => {
        console.log("Add car button pressed")
        navigation.navigate("AddCarScreen")
    }
    
    const automateBookingButtonPressed = () => {
        console.log("Automate booking button pressed")
        getCurrentLocation()
    }
    
    const getCurrentLocation = async() => {
        let { status } = await Location.requestForegroundPermissionsAsync();
        if ( status !== 'granted') {
          console.log("Please grant location permissions")
          navigation.navigate("AutomateBookingScreen")
        } else {
            console.log("Getting current locations")
            let currentLocation = await Location.getCurrentPositionAsync({});
            setCoordinates(currentLocation.coords)
            console.log(currentLocation.coords)
            navigation.navigate("AutomateBookingScreen")
        }
    }

    const manualBookingButtonPressed = () => {
        console.log("Manual booking button pressed")
    }


    return {
        addCarButtonPressed,
        automateBookingButtonPressed,
        manualBookingButtonPressed
    };

}