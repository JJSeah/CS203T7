import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";

export default ManualBookingScreenViewController = ( { navigation } ) => {
    
    const [ bookingDate, setBookingDate ] = useState(new Date());
    const [ maxDate, setMaxDate ] = useState(null);

    const [ startTime, setStartTime ] = useState(null);
    const [ endTime, setEndTime ] = useState(null);

    const onChange = (event, selectedDate) => {
        // on cancel set date value to previous date
        if (event?.type === 'dismissed') {
            setBookingDate(new Date());
            return;
        }
        setBookingDate(selectedDate);
    };

    return (
        bookingDate,
        setBookingDate,
        startTime,
        setStartTime,
        endTime,
        setEndTime
        // onChange
    );

}