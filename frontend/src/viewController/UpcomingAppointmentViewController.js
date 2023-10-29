import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import * as SecureStore from "expo-secure-store"
import axios from "axios";
import { BASE_URL } from '../constants/Config';



export default UpcomingAppointmentViewController = ( { navigation } ) => {
    // const [ isReady, setIsReady ] = useState(false);     
    
    //test 
    const chargingProgressButtonPressed = () => {
        console.log("check car charging progress");
        navigation.navigate('ChargingCarView');
    }

    const startAppointment = (appt) => {

        axios
          .get(`${BASE_URL}/api/appointment/start/${appt.id}`, {
            headers: {
              Authorization: `Bearer ${userToken}`,
            },
          })
          .then((res) => {
            let data = res.data;
            getAllAppointments();
            navigation.pop()
            navigation.navigate("ChargingCarView", appt);
          })
          .catch((e) => {
            console.log(`error starting appointment: ${e}`);
          });
      };
    

    return {
        chargingProgressButtonPressed,
        startAppointment
    };
}