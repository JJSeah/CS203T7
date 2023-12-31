import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import * as Location from 'expo-location'
import {Alert} from "react-native";

export default HomeScreenViewController = ( { navigation } ) => {
    
    const { allAppointments, setUserCoordinates, currentCar, userCards, userCars } = useContext(UserContext);

    const addCarButtonPressed = () => {
        console.log("Add car button pressed")
        navigation.navigate("AddCarScreen")
    } 

    const automateBookingButtonPressed = () => {

        if (userCards.length === 0 || userCars.length === 0) {
          navigation.navigate("ReminderScreen")
          return;
        }


        const automateVerAppt = allAppointments.filter((appt) => {return appt.manualAppointment === false})
        const unfinished = automateVerAppt.filter((appt) => {return (appt.status !== "completed" && appt.status !== "cancelled")})
        if (unfinished.length !== 0) {
          Alert.alert(
            "Unable to make booking",
            "You have an automated booking that is not completed yet",
            {
              text: "Got it",
              onPress: () => {}
            }
          )
          return;
        } 
        if (currentCar.batteryPercentage === 100) {
          Alert.alert(
            "Current car battery percentage too high",
            "Please select another car or wait until your battery is at the right percentage",
            {
              text: "Got it",
              onPress: () => {}
            }
          )

          return;
        } 

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


      if (userCards.length === 0 || userCars.length === 0) {
        navigation.navigate("ReminderScreen")
        return;
      }

        const manualVerAppt = allAppointments.filter((appt) => {return appt.manualAppointment === true})
        const unfinished = manualVerAppt.filter((appt) => {return (appt.status !== "completed" && appt.status !== "cancelled")})
        if (unfinished.length === 2) {
          Alert.alert(
            "Unable to make booking",
            "You can make at most 2 manual bookings in advance only",
            [
            {
              text: "Got it",
              onPress: () => {}
            }
          ]
          )
        } else {
            navigation.navigate("ManualBookingScreen", {currentCar: currentCar})
        }
    }


    return {
        addCarButtonPressed,
        automateBookingButtonPressed,
        manualBookingButtonPressed
    };

}