import { useContext, useState } from "react";
import { UserContext } from "../model/User";


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


    return {
        historyButtonPressed, 
        scanQRButtonPressed
    };
}