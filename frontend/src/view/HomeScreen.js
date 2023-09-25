import React, { useContext } from "react";
import {Button, Text, View, ScrollView, TextBase, StyleSheet, Image,} from "react-native";
import Icon from 'react-native-vector-icons/FontAwesome';


import CustomLongButton from "../components/CustomLongButton";
import CarDetails from "../components/CarDetails";
import HomeScreenViewController from "../viewController/HomeScreenViewController";
import { UserContext } from "../model/User";
import CarSwipeView from "../components/SingleCarSwiperView";
import { useFocusEffect } from "@react-navigation/native";
import { CarRepository } from "../model/CarRepository";
import { SafeAreaView } from "react-native-safe-area-context";
import { styles } from "../components/Design";
import CarSwiperView from "./CarSwiperView";

export default HomeScreen = ({ navigation }) => {
  const {
    addCarButtonPressed,
    manualBookingButtonPressed,
    automateBookingButtonPressed,
  } = HomeScreenViewController({ navigation });

  const { userData, userCars, logOut, setCurrentCar, currentCar } =
    useContext(UserContext);

  const { loadCarsData } = CarRepository();

  useFocusEffect(
    React.useCallback(() => {
      if (userCars !== null) {
        loadCarsData();
      }
    }, [])
  );

  return (
    <SafeAreaView style={localStyles.container}>
      <View style={localStyles.headerContainer}>
        <Text style={localStyles.headerText}>
          Welcome back, {userData.username}!
          </Text>

        {currentCar !== null ? (
          <>
            <Text style = {localStyles.subHeaderText}>
              {currentCar.model}
              </Text>
            <Text style = {localStyles.subHeaderText}>
              {currentCar.nickname}
              </Text>
          </>
        ) : (
          <Text>You do not have a car yet</Text>
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
          icon = "battery-full" 
          value = {currentCar.batteryPercentage} 
          title = "BATTERY"
          />

          <CarDetails 
          icon = "square" 
          value = {currentCar.plate} 
          title = "CAR PLATE"
          />
            {/* <Text>Id: {currentCar.id}</Text> */}
          </>
        ) : (
          <Text>Please add a car</Text>
        )
        }
      </View>

      <View style={localStyles.bottomContainer}>
        <CustomLongButton title="Add car" onPress={addCarButtonPressed} />

        <CustomLongButton
          title="Manual booking"
          onPress={manualBookingButtonPressed}
        />

        <CustomLongButton
          title="Automate booking"
          onPress={automateBookingButtonPressed}
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
    backgroundColor: "blue",
  },
  carDetailsContainer: {
    flex: 2,
    backgroundColor: "white",
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
  },

  carDetailsText: {

  },

  carStyle: {
    marginTop: 60,
    width: 400,
    height: 300,
  },



  buttonContainer: {},
});
