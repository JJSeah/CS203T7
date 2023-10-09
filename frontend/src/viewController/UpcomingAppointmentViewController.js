import { useContext, useState } from "react";
import { UserContext } from "../model/User";


export default UpcomingAppointmentViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(false);     

    const testButtonPressed = () => {
        console.log("test button pressed");
        navigation.navigate('ChargingCarScreen');
    }  

    return {
        isReady, 
        setIsReady,
        testButtonPressed,
    };
}