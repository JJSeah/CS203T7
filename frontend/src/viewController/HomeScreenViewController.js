import { useState } from "react";

export default HomeScreenViewController = ( { navigation } ) => {
    
    const addCarButtonPressed = () => {
        console.log("Add car button pressed")
        navigation.navigate("AddCarScreen")
    }
    
    const automateBookingButtonPressed = () => {
        console.log("Automate booking button pressed")
        navigation.navigate("AutomateBookingScreen")
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