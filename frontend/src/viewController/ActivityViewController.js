import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { BASE_URL } from "../constants/Config";


export default ActivityViewController = ( { navigation } ) => {
    // const [ isReady, setIsReady ] = useState(false);      
    
    //test 
    const historyButtonPressed = () => {
        console.log("history button pressed");
        navigation.navigate('HistoryScreen');
    }

    const scanQRButtonPressed = () => {
        console.log("history button pressed");
        navigation.navigate('UpcomingAppointmentView'); 
    }

    const cancelButtonPressed = ( async ) => {
        console.log("delete appointment");
//      return axios.get(`${BASE_URL}/api/appointment/cencel/${apptId}`,
//     {
//       headers: {
//         'Authorization': `Bearer ${userToken}`
//       }
//     }
//     )
//     .then ( res => {
//      let data = res.data
//      console.log(data);
//    })
//    .catch (e => {
//     console.log(`catch exception: ${e}`)
//     })
    }
 
    return {
        historyButtonPressed, 
        scanQRButtonPressed, 
        cancelButtonPressed
    };
}