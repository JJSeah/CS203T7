import React, { useContext, useEffect, useState } from 'react';
import { Button, Text, View } from 'react-native';
import { UserContext } from '../model/User';
import ManualBookingScreenViewController from '../viewController/ManualBookingScreenViewController';

import RNDateTimePicker from '@react-native-community/datetimepicker';

export default ManualBookingScreen = ( { navigation } ) => {

    const [ currentDate, setCurrentDate ] = useState(new Date())
    const [ maxDate, setMaxDate ] = useState(new Date())

    const [ bookingStartTime, setBookingStartTime ] = useState(new Date())
    const [ bookingEndTime, setBookingEndTime ] = useState(new Date())

    useEffect(() => {
        
        const interval = setInterval(
            () => {
                setCurrentDate(new Date())
                setMaxDate(addDays(new Date(), 2))

            }, 1000
        );

        return () => {
        
          clearInterval(interval);
        };
    }, [])

    const onChangeDate = (event, selectedDate) => {
        setBookingStartTime(selectedDate)
    };

    const onChangeStartTime = (event, selectedDate) => {
        setBookingStartTime(selectedDate);
    };

    const onChangeEndTime = (event, selectedDate) => {
        setBookingEndTime(selectedDate);
    };

    const addDays = (date, days) => {
        var result = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    }

    return (
        <View>
        <Text>You can only book from {currentDate.toTimeString()} onwards</Text>

        <Text>You want to book from {bookingStartTime.toTimeString()}</Text>
        <Text>You want to book to {bookingEndTime.toTimeString()}</Text>

        <RNDateTimePicker
            display='calendar'
            value={bookingStartTime}
            onChange={onChangeDate}
            minimumDate={currentDate}
            maximumDate={maxDate}
        />

        <Text>Start time</Text>
        <RNDateTimePicker
            mode='time'
            display='spinner'
            value={bookingStartTime}
            minimumDate={currentDate}
            onChange={onChangeStartTime}
            minuteInterval={5}
        />


        <Text>End time</Text>
        <RNDateTimePicker
            mode='time'
            display='spinner'
            value={bookingEndTime}
            minimumDate={(bookingStartTime < currentDate ? currentDate : bookingStartTime)}
            onChange={onChangeEndTime}
            minuteInterval={5}
        />

        <Button
            title="confirm"
            onPress={() => {
                if (bookingStartTime < currentDate) {
                    setBookingStartTime(currentDate)
                }

                if (bookingEndTime <= bookingStartTime) {
                    console.log("You cannot book")
                } else {
                    console.log("Success")
                    navigation.pop()
                }

            }}
        />



        </View>
    );
}
