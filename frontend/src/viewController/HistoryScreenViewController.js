import { useContext, useState } from "react";
import { UserContext } from "../model/User";


export default HistoryViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(false);      
    const [ monthValue, setMonthValue ] = useState(null);
    const [ yearValue, setYearValue ] = useState("2023");

    return {
        isReady, 
        setIsReady,
        monthValue, 
        setMonthValue, 
        yearValue, 
        setYearValue
    };

}