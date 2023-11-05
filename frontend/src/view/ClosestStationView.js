import React, { useContext, useEffect, useState } from "react";
import {
  Button,
  Text,
  View,
  StyleSheet,
  ActivityIndicator,
  SafeAreaView,
} from "react-native";
import AutomateBookingScreenViewController from "../viewController/AutomateBookingScreenViewController";
import { UserContext } from "../model/User";
import MapView, { Marker } from "react-native-maps";
import UpcomingAppointmentView from "./UpcomingAppointmentView";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { styles } from "../components/Design";
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';

SplashScreen.preventAutoHideAsync();

export default ClosestStationView = () => {
  const { closestStation, upcomingAppointmentDetails, currentCar } =
    useContext(UserContext);

  const [ isReady, setIsReady ] = useState(false);      

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
    loadFonts(); 
  }, []);

  return (
    <View style={localStyles.container}>
      <View style={localStyles.carInfoContainer}>
        <Text style={localStyles.label}>
          We found the closest station to you!
        </Text>
        <Text style={localStyles.label}>
          Current car: {currentCar.nickname}
        </Text>
      </View>

      <View style={localStyles.stationInfoContainer}>
        <View>
          <MaterialCommunityIcons
            name="ev-station"
            size={40}
            color="red"
            padding={2}
          />
        </View>

        <View>
          <Text style={localStyles.label}>{closestStation.name}</Text>
          <Text style={localStyles.smallText}>{closestStation.address}</Text>
        </View>
      </View>

      <View style={localStyles.mapContainer}>
        <MapView style={localStyles.map}>
          <Marker
            coordinate={{
              latitude: closestStation.latitude,
              longitude: closestStation.longitude,
            }}
          />
        </MapView>
      </View>

      <View style={localStyles.appointmentContainer}>
        {upcomingAppointmentDetails === null ? (
          <View>
            <ActivityIndicator style={{ padding: 20 }} />
            <View
              style={{
                alignItems: "center",
              }}
            >
              <Text style={localStyles.label}>Calculating details...</Text>
            </View>
          </View>
        ) : (
          <View style={{ flex: 1}}>
            <View
              style={{
                flex: 1,
                paddingHorizontal: 20,
                justifyContent: "space-between",
                alignItems: "center",
                flexDirection: "row",
              }}
            >
              <View>
                <Text style={localStyles.label}>The charger id is:</Text>
              </View>

              <View>
                <Text style={localStyles.label}>
                  {upcomingAppointmentDetails.charger.id}
                </Text>
              </View>
            </View>

            <View style={{ flex: 4, 
              
              // backgroundColor: "blue", 
              flexDirection: "row"}}>
              <View
                style={{
                  // backgroundColor: "purple", 
                  flex: 1,
                  flexDirection: "column"
                }} 
              >
                <View 
                  style={localStyles.singleInfoContainer} 
                >
                  <Text style={localStyles.title}>
                    Cost
                  </Text>
                  <Text style={localStyles.text}>
                    ${upcomingAppointmentDetails.costOfCharging}
                  </Text>
                </View>

                <View
                  style={localStyles.singleInfoContainer} 
                >
                  <Text style={localStyles.title}>
                    Distance 
                  </Text>
                  <Text style={localStyles.text}>
                    {upcomingAppointmentDetails.distance / 1000} km
                  </Text>
                </View>
              </View>

              <View
                style={{
                  // backgroundColor: "blue", 
                  flex: 1}} 
              >
                <View
                  style={localStyles.singleInfoContainer} 
                >
                <Text style={localStyles.title}>
                  Charge Time 
                </Text>

                <Text style={localStyles.text}>
                  {upcomingAppointmentDetails.estimateTimeOfCharging} minutes
                </Text>
                </View>

                <View
                  style={localStyles.singleInfoContainer} 
                >

                <Text style={localStyles.title}>
                  ETA
                </Text>

                <Text style={localStyles.text}>
                  {upcomingAppointmentDetails.timeToArrive} minutes
                </Text>
                </View>
              </View>
            </View>
          </View>
        )}
      </View>
    </View>
  );
};

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "stretch",
    backgroundColor: "#141414",
    // backgroundColor: "blue",
  },
  carInfoContainer: {
    flex: 2,
    alignItems: "center",
    paddingTop: 10,
    // backgroundColor: "red",
    justifyContent: "center",
  },
  singleInfoContainer: {
    flex:1,
    borderColor: "white",
    borderWidth: 1,
    borderRadius: 10,
    margin: 5,
    justifyContent: "center",
    alignItems: "center"
  },
  stationInfoContainer: {
    flex: 2,
    alignItems: "center",
    // backgroundColor: "green",
    justifyContent: "center",
    flexDirection: "row",
  },
  mapContainer: {
    flex: 4,
    padding: 30,
  },
  appointmentContainer: {
    flex: 4,
    padding: 10
  },
  map: {
    width: "100%",
    height: "100%",
  },
  label: {
    color: "white",
    fontSize: 18,
    fontWeight: "bold",
    fontFamily: 'Product-Sans-Regular'
  },
  text: {
    color: "white",
    fontSize: 15,
    fontFamily: 'Product-Sans-Regular'
  },
  smallText: {
    color: "grey",
    fontSize: 15,
    fontFamily: 'Product-Sans-Regular'
  },
  title: {
    color: "white",
    fontSize: 15,
    fontWeight: "bold", 
    fontFamily: 'Product-Sans-Regular'
  },
});
