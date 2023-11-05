import React, { useContext, useEffect, useState } from "react";
import { Text, View, ActivityIndicator, StyleSheet } from "react-native";
import * as Location from "expo-location";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";
import CustomLongButton from "../components/CustomLongButton";
import AutomateBookingScreenViewController from "../viewController/AutomateBookingScreenViewController";
import ClosestStationView from "./ClosestStationView";
import GrantLocationScreen from "./GrantLocationScreen";
import UpcomingAppointmentView from "./UpcomingAppointmentView";
import ReminderScreen from "./ReminderScreen";
import MapView, { Marker } from "react-native-maps";
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';

SplashScreen.preventAutoHideAsync();

export default AutomateBookingScreen = ({ navigation }) => {
  const {
    userCoordinates,
    closestStation,
    upcomingAppointmentDetails,
    userCars,
    userCards,
  } = useContext(UserContext);

  const { isReady, setIsReady, findClosestStation, confirmButtonPressed } =
    AutomateBookingScreenViewController({
      navigation
    });

    useEffect(() => {
      const loadFonts = async() => {
        await FontLoader();
        setIsReady(true);
        await SplashScreen.hideAsync();
      }; 
      loadFonts(); 
    }, []);  

  useEffect(() => {
    if (
      userCoordinates !== null &&
      userCars.length !== 0 &&
      userCards.length !== 0
    ) {
      console.log("finding closest station");
      findClosestStation(userCoordinates.latitude, userCoordinates.longitude);
    }
  }, []);

  return userCoordinates === null ? (
    <GrantLocationScreen />
  ) : userCars.length === 0 || userCards.length === 0 ? (
    <ReminderScreen />
  ) : closestStation !== null ? (
    <SafeAreaView style={localStyles.container}>
      <View style={localStyles.infoContainer}>
        <ClosestStationView />
      </View>

      <View style={localStyles.buttonsContainer}>
        <CustomLongButton
          title="Cancel"
          onPress={() => {
            navigation.pop();
          }}
        />

        <CustomLongButton
          title="Confirm"
          disabled={upcomingAppointmentDetails === null}
          onPress={() => {
            confirmButtonPressed();
          }}
        />
      </View>
    </SafeAreaView>
  ) : (
    <SafeAreaView
      style={{ flex: 1, backgroundColor: "#141414", justifyContent: "center" }}
    >

      <ActivityIndicator style={{ padding: 50 }} />
      <View
        style={{
          alignItems:"center"
        }}
      >
          <Text style={localStyles.label}>Finding closest station for you...</Text>
      </View>
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "column",
    alignItems: "stretch",
    backgroundColor: "#141414",
  },
  infoContainer: {
    flex: 9,
    alignItems: "stretch",
  },
  buttonsContainer: {
    flex: 1,
    flexDirection: "row",
  },
  label: {
    color: "white",
    fontSize: 18,
    fontWeight: "bold",
    fontFamily: 'Product-Sans-Regular'
  },
});
