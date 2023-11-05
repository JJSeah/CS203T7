import { useContext, useState } from "react";
import { UserContext } from "../model/User";


export default HistoryViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(false);      
    const [ selectedCar, setSelectedCar ] = useState(null);
    const [ monthValue, setMonthValue ] = useState(null);
    const [ yearValue, setYearValue ] = useState(null);
    const [ showAllRecords, setShowAllRecords ] = useState(true);
    const [ filteredRecords, setFilteredRecords ] = useState(null);

    return {
        isReady, 
        setIsReady,
        selectedCar, 
        setSelectedCar,
        monthValue, 
        setMonthValue, 
        yearValue, 
        setYearValue, 
        showAllRecords, 
        setShowAllRecords, 
        filteredRecords, 
        setFilteredRecords
    };
}