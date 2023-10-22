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
    

    return {
        chargingProgressButtonPressed
    };
}