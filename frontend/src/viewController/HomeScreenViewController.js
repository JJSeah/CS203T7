import { useContext, useState } from "react";
import { UserContext } from "../model/User";

export default HomeScreenViewController = ( { navigation } ) => {
    
    const { userCars } = useContext(UserContext);

    const addCarButtonPressed = () => {
        console.log("Add car button pressed")
        navigation.navigate("AddCarScreen")
    }
    
    const automateBookingButtonPressed = () => {
        console.log("Automate booking button pressed")
        navigation.navigate("AutomateBookingScreen")
    }

    const manualBookingButtonPressed = () => {
        console.log(userCars)
        console.log("Manual booking button pressed")
    }

    return {
        addCarButtonPressed,
        automateBookingButtonPressed,
        manualBookingButtonPressed
    };

}