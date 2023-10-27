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
import ReminderScreen from "./ReminderScreen"
import MapView, { Marker } from "react-native-maps";

export default AutomateBookingScreen = ({ navigation }) => {
  const { userCoordinates, closestStation, upcomingAppointmentDetails, userCars, userCards} =
    useContext(UserContext);

  const { findClosestStation, confirmButtonPressed } = AutomateBookingScreenViewController({
    navigation,
  });

  useEffect(() => {
    if (userCoordinates === null || userCars.length === 0) {
      return;
    }

    findClosestStation(userCoordinates.latitude, userCoordinates.longitude);
  }, []);

  return userCoordinates === null ? (
    <GrantLocationScreen />
  ) : userCars.length === 0 || userCards.length === 0? (
    <ReminderScreen/>
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
    <SafeAreaView>
      <ActivityIndicator />
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "column",
    alignItems: "stretch",
  },
  infoContainer: {
    flex: 9,
    alignItems: "stretch",
  },
  buttonsContainer: {
    flex: 1,
    flexDirection: "row",
  },
});
