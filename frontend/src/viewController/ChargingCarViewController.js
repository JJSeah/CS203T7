import { useContext, useState } from "react";
import { UserContext } from "../model/User";


export default ChargingCarViewController = ( { navigation } ) => {
    // const [ isReady, setIsReady ] = useState(false);      

    //test 
    const stopButtonPressed = () => {
        console.log("stop button pressed");
        navigation.navigate('HomeNavigator');
    }

    const finishButtonPressed = () => {
        console.log("finish button pressed");
        navigation.navigate('HomeNavigator');
    }
    

    return {
        stopButtonPressed, 
        finishButtonPressed
    };
}