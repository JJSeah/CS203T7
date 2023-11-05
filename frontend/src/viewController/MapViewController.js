import { useContext, useState } from "react";
import { UserContext } from "../model/User";


export default MapViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(null);
    const [ selectedStation, setSelectedStation ] = useState(null);

    return {
        isReady, 
        setIsReady,
        selectedStation, 
        setSelectedStation
    };
}