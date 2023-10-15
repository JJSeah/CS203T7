import { useContext, useState } from "react";
import { UserContext } from "../model/User";


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