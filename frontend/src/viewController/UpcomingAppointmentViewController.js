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
    
   const cancelButtonPressed = () => {
     console.log("cancel appointment"); 
     return axios.get(`${BASE_URL}/api/appointment/cancel/`,
   {
      headers: {
        'Authorization': `Bearer ${userToken}`
      }
   }
   )
   .then ( res => {
    let data = res.data
    console.log(data);
   })
   .catch (e => {
    console.log(`catch exception: ${e}`)
   })
   }


    return {
        chargingProgressButtonPressed
    };
}