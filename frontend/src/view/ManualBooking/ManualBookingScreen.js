import React, { useContext, useEffect, useState } from "react";
import {
  Button,
  Text,
  View,
  Alert,
  SafeAreaView,
  StyleSheet,
} from "react-native";
import { UserContext } from "..//../model/User";
import ManualBookingScreenViewController from "../../viewController/ManualBookingScreenViewController";
import CustomLongButton from "../../components/CustomLongButton";
import { useRoute } from "@react-navigation/native";
import RNDateTimePicker from "@react-native-community/datetimepicker";
import ReminderScreen from "../ReminderScreen";
import FontLoader from '../../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';

SplashScreen.preventAutoHideAsync();

export default ManualBookingScreen = ({ navigation }) => {
  const route = useRoute();
  const currentCar = route.params?.currentCar;

  const [currentDate, setCurrentDate] = useState(new Date());
  const [maxDate, setMaxDate] = useState(new Date());

  const [bookingStartTime, setBookingStartTime] = useState(new Date());
  const [bookingEndTime, setBookingEndTime] = useState(new Date());

  const { isReady, setIsReady, findAvailableStationsButtonPressed } =
    ManualBookingScreenViewController({ navigation });

  const { userCars, userCards } = useContext(UserContext);

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
    loadFonts(); 
  }, []);

  const diffHours = (start, end) => {
    var diff = end.getTime() - start.getTime();
    var hours = Math.floor(diff / 1000 / 60 / 60);

    diff -= hours * 1000 * 60 * 60;

    var minutes = Math.floor(diff / 1000 / 60);
    if (hours < 0) hours = hours + 24;

    return hours;
  };

  const diffMinutes = (start, end) => {
    var diff = end.getTime() - start.getTime();
    var hours = Math.floor(diff / 1000 / 60 / 60);

    diff -= hours * 1000 * 60 * 60;

    var minutes = Math.floor(diff / 1000 / 60);
    // If using time pickers with 24 hours format, add the below line get exact hours

    return (minutes <= 9 ? "0" : "") + minutes;
  };

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentDate(new Date());
      setMaxDate(addDays(new Date(), 2));
    }, 1000);

    setBookingStartTime(roundDateByMinutes(bookingStartTime, 5));
    setBookingEndTime(roundDateByMinutes(bookingEndTime, 5));

    return () => {
      clearInterval(interval);
    };
  }, []);

  const onChangeDate = (event, selectedDate) => {
    setBookingStartTime(selectedDate);
    setBookingEndTime(selectedDate);
  };

  const onChangeStartTime = (event, selectedDate) => {
    setBookingStartTime(selectedDate);
    setBookingEndTime(selectedDate);
  };

  const onChangeEndTime = (event, selectedDate) => {
    setBookingEndTime(selectedDate);
  };

  const addDays = (date, days) => {
    var result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
  };

  const roundDateByMinutes = (date, minutes) => {
    let ms = 1000 * 60 * minutes; // convert minutes to ms
    let roundedDate = new Date(Math.ceil(date.getTime() / ms) * ms);

    return roundedDate;
  };

  return (
    <SafeAreaView style={{ flex: 1 }}>
      {(userCars.length === 0 || userCards.length === 0) ? (
        <ReminderScreen/>
      ) : (
        <View
        style={{flex: 1}}
        >
          <View style={localStyles.topContainer}>
            <View>
              <Text>Current time: {currentDate.toLocaleTimeString()}</Text>
              <Text>Booking for: {currentCar.nickname}</Text>
            </View>

            <View style={{ alignContent: "center", justifyContent: "center" }}>
              <RNDateTimePicker
                display="calendar"
                value={bookingStartTime}
                onChange={onChangeDate}
                minimumDate={currentDate}
                maximumDate={maxDate}
              />
            </View>

            <Text>Hours: {diffHours(bookingStartTime, bookingEndTime)}</Text>
            <Text>
              Minutes: {diffMinutes(bookingStartTime, bookingEndTime)}
            </Text>

            <Text>
              Start time{" "}
              {bookingStartTime.toLocaleTimeString([], {
                hour: "2-digit",
                minute: "2-digit",
              })}
            </Text>
            <RNDateTimePicker
              mode="time"
              display="spinner"
              value={bookingStartTime}
              minimumDate={currentDate}
              onChange={onChangeStartTime}
              minuteInterval={5}
            />

            <Text>
              End time{" "}
              {bookingEndTime.toLocaleTimeString([], {
                hour: "2-digit",
                minute: "2-digit",
              })}
            </Text>
            <RNDateTimePicker
              mode="time"
              display="spinner"
              value={bookingEndTime}
              minimumDate={bookingStartTime}
              onChange={onChangeEndTime}
              minuteInterval={5}
            />
          </View>

          <View style={localStyles.bottomContainer}>
            <CustomLongButton
              title="Find available stations"
              onPress={() => {
                if (bookingStartTime < currentDate) {
                  setBookingStartTime(currentDate);
                }

                if (bookingEndTime <= bookingStartTime) {
                  Alert.alert(
                    "Invalid timings",
                    "Please select appropriate timings",
                    [
                      {
                        text: "Got it",
                        onPress: () => {},
                      },
                    ]
                  );
                } else {
                  findAvailableStationsButtonPressed(
                    currentCar,
                    bookingStartTime,
                    bookingEndTime
                  );
                }
              }}
            />
          </View>
        </View>
      )}
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  topContainer: {
    flex: 9,
  },
  bottomContainer: {
    flex: 1,
  },
});
