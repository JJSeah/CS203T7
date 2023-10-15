import { useContext, useState } from "react";
import { UserContext } from "../model/User";


export default HistoryViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(false);      
    const [ monthValue, setMonthValue ] = useState(null);
    const [ yearValue, setYearValue ] = useState('2023');
    const [ showAllRecords, setShowAllRecords ] = useState(false);
    const [ filteredRecords, setFilteredRecords ] = useState(null);

    return {
        isReady, 
        setIsReady,
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