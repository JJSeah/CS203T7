import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { BASE_URL } from "../constants/Config";

export default ChargingCarViewController = ( { navigation } ) => {
    // const [ isReady, setIsReady ] = useState(false);      
    const [ buttonState, setButtonState ] = useState("STOP");
    const { userToken, loadAllAppointments } = useContext(UserContext);

    //test 
    const stopButtonPressed = ( appt ) => {
      //   console.log("stop button pressed");
      //   axios.put(`${BASE_URL}/api/appointment/cancel/${apptId}`,
      //  {
      //    headers: {
      //      'Authorization': `Bearer ${userToken}`
      //    }
      //  })
      //  .then(res => {
      //   console.log("stop charging")
      //  })
      //  .catch (e => {
      //   console.log(`failed to stop charging: ${e}`)
      //   })
        navigation.navigate("MakePaymentScreen", appt)
    }

    const checkCarBatteryStatus = async(carId) => {
        console.log(`Checking car with id ${carId}'s battery percentage`)
    }


    const finishButtonPressed = ( appt) => {
        console.log("finish button pressed");
        console.log(appt.id)
        navigation.navigate("MakePaymentScreen", appt)
    }
    

    return {
        buttonState, 
        setButtonState, 
        stopButtonPressed, 
        finishButtonPressed,
        checkCarBatteryStatus,
    };
}