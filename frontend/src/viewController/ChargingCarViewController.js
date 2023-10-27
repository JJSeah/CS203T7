import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { BASE_URL } from "../constants/Config";

export default ChargingCarViewController = ( { navigation } ) => {
    // const [ isReady, setIsReady ] = useState(false);      
    const [ buttonState, setButtonState ] = useState("STOP");
    const { userToken, loadAllAppointments } = useContext(UserContext);

    //test 
    const stopButtonPressed = ( apptId ) => {
        console.log("stop button pressed");
        axios.put(`${BASE_URL}/api/appointment/cancel/${apptId}`,
       {
         headers: {
           'Authorization': `Bearer ${userToken}`
         }
       })
       .then(res => {
        console.log("stop charging")
       })
       .catch (e => {
        console.log(`failed to stop charging: ${e}`)
        })

        navigation.navigate('HomeNavigator');
    }


    const finishButtonPressed = ( apptId ) => {
        console.log("finish button pressed");
        console.log(apptId)
        navigation.navigate("MakePaymentScreen", {apptId})
        // axios.put(`${BASE_URL}/api/appointment/complete/${apptId}`,
        // {
        //   headers: {
        //     'Authorization': `Bearer ${userToken}`
        //   }
        // })
        // .then(res => {
        //  console.log("finished charging")
        //  loadAllAppointments()
        //  navigation.navigate('HomeNavigator');
        // })
        // .catch (e => {
        //  console.log(`failed to finish charging: ${e}`)
        //  })
    }
    

    return {
        buttonState, 
        setButtonState, 
        stopButtonPressed, 
        finishButtonPressed
    };
}