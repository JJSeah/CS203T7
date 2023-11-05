import React, { useContext } from "react";
import {Button, Text, View, ScrollView, TextBase, StyleSheet, Image, Alert} from "react-native";
import Icon from 'react-native-vector-icons/FontAwesome';

import CustomLongButton from "../components/CustomLongButton";
import CarDetails from "../components/CarDetails";
import { styles } from "../components/Design";
import CustomCarButton from "../components/CustomCarButton";

import HomeScreenViewController from "../viewController/HomeScreenViewController";
import { UserContext } from "../model/User";
import { useFocusEffect } from "@react-navigation/native";
import { CarRepository } from "../model/CarRepository";
import { SafeAreaView } from "react-native-safe-area-context";
import CarSwiperView from "./CarSwiperView";

import { emptyCarIcon } from "../../assets/images/index";


export default HomeScreen = ({ navigation }) => {
  const {
    manualBookingButtonPressed,
    automateBookingButtonPressed,
  } = HomeScreenViewController({ navigation });

  const { userData, userCars, setCurrentCar, currentCar, allAppointments } =
    useContext(UserContext);

  const { loadCarsData } = CarRepository();

  useFocusEffect(
    React.useCallback(() => {
      if (userCars !== null) {
        // loadCarsData();
      }
    }, [])
  );

  return (
    <SafeAreaView style={localStyles.container}>
      <View style={localStyles.headerContainer}>
        <Text style={localStyles.headerText}>
          Welcome back, {userData.usernames}!
          </Text>

        {currentCar !== null ? (
          <>
            <Text style = {localStyles.subHeaderText}>
              {currentCar.model}
              </Text>
            <Text style = {localStyles.subHeaderText}>
              {currentCar.nickname}
              </Text>
              {/* <Text style = {localStyles.subHeaderText}>{currentCar.id}</Text> */}
          </>
        ) : (
          <>
          <Text style = {localStyles.subHeaderText}>
            You do not have a car yet
            </Text>
            <Image source = {emptyCarIcon}
            style = {localStyles.emptyCarIcon}/>
          </>
        )}
      </View>


      <View style={localStyles.swiperContainer}>
        <View>
          <CarSwiperView userCars={userCars} setCurrentCar={setCurrentCar}/>
        </View>
      </View>

      <View style={localStyles.carDetailsContainer}>
        {currentCar !== null ? (
          <>
            <CarDetails 
            iconName = "batteryIcon" 
            value = {parseInt(currentCar.batteryPercentage)} 
            title = "BATTERY"
            />

          <CarDetails 
          iconName = "carPlateIcon" 
          value = {currentCar.plate} 
          title = "CAR PLATE"
          />
          {/* <Text style = {localStyles.subHeaderText}>{currentCar.id}</Text> */}
          </>
        ) : (
          <>
          <CarDetails 
          iconName = "batteryIcon" 
          value = "-"
          title = "BATTERY"
          />
        <CarDetails 
        iconName = "carPlateIcon" 
        value = "-"
        title = "CAR PLATE"
        />
          </>
        )
        }
      </View>

      <View>
        <ScrollView>
        </ScrollView>
      </View>

      <View style={localStyles.bottomContainer}>

        <CustomLongButton
          title="Manual booking"
          onPress={manualBookingButtonPressed}
        />

        <CustomLongButton
          title="Automate booking"
          onPress={() => {
            automateBookingButtonPressed()
          }}
        />

      </View>
    </SafeAreaView>
  );
};




const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },

  // all main containers

  headerContainer: {
    flex: 1.5,
    // backgroundColor: "red",
    alignItems: "center",
  },
  swiperContainer: {
    flex: 4,
    // backgroundColor: "blue",
  },
  carDetailsContainer: {
    flex: 2,
    flexDirection: 'row',
    justifyContent: 'space-evenly',
    // backgroundColor: "yellow",

  },
  bottomContainer: {
    flex: 3,
  },


  headerText: {
    fontFamily: "Product-Sans-Regular",
    fontWeight: "bold",
    fontSize: 20,
    color: "white",
    marginBottom: 30,
  },
  subHeaderText: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 14,
    color: "white",
    marginBottom: 10,
  },

  emptyCarIcon: {
    marginTop: 100,
    width: 250,
    height: 160,
    resizeMode: 'contain',
    opacity: 0.2,
  },



  buttonContainer: {},
});
