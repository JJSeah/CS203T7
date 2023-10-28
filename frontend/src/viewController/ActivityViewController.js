import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { BASE_URL } from "../constants/Config";


export default ActivityViewController = ( { navigation } ) => {
    // const [ isReady, setIsReady ] = useState(false);      
    const { userToken } = useContext(UserContext);
    //test 
    const historyButtonPressed = () => {
        console.log("history button pressed");
        navigation.navigate('HistoryScreen');
    }

    const scanQRButtonPressed = ( appt ) => {
        console.log("history button pressed");
        navigation.navigate('UpcomingAppointmentView', appt); 
    }

    const cancelButtonPressed = ( appt ) => {
      console.log(appt.id)
      console.log(appt)
    //   console.log("delete appointment");
    //   axios.delete(`${BASE_URL}/api/appointment/${appt.id}`,
    //   {
    //     headers: {
    //       'Authorization': `Bearer ${userToken}`
    //     }
    //   }
    //   )
    //   .then ( res => {
    //   let data = res.data
    //   console.log("deleted appointment");
    // })
    // .catch (e => {
    //   console.log(`catch exception: ${e}`)
    //   })
    }
 
    return {
        historyButtonPressed, 
        scanQRButtonPressed, 
        cancelButtonPressed
    };
}