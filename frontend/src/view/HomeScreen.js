import React, { useContext } from "react";
import {
  Button,
  Text,
  View,
  ScrollView,
  TextBase,
  StyleSheet,
  Image,
} from "react-native";
import CustomLongButton from "../components/CustomLongButton";
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
        <Text>Welcome back, {userData.username}!</Text>

        {currentCar !== null ? (
          <>
            <Text>Model: {currentCar.model}</Text>
            <Text>Nickname: {currentCar.nickname}</Text>
          </>
        ) : (
          <Text>You do not have a car yet</Text>
        )}
      </View>

      {/* <View style = {localStyles.carShowStage}></View> */}

      <View style={localStyles.swiperContainer}>
        <View style={{ flex: 1 }}>
          <CarSwiperView userCars={userCars} setCurrentCar={setCurrentCar} />
        </View>
      </View>

      <View style={localStyles.carDetailsContainer}>
        {currentCar !== null ? (
          <>
            <Text>Battery: {currentCar.batteryPercentage}</Text>
            <Text>Plate: {currentCar.plate}</Text>
            <Text>Id: {currentCar.id}</Text>
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

  headerContainer: {
    flex: 1,
    backgroundColor: "red",
    alignItems: "center",
  },
  headerText: {
    fontFamily: "Product-Sans-Regular",
    fontWeight: "bold",
    fontSize: 20,
    color: "white",
    marginTop: 85,
    marginBottom: 20,
  },

  subHeaderText: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 14,
    color: "white",
    marginTop: 10,
  },
  carStyle: {
    marginTop: 60,
    width: 400,
    height: 300,
  },
  swiperContainer: {
    flex: 4,
    // backgroundColor: "blue",
  },
  carShowStage: {
    height: 300, // Adjust the height of the car show stage as needed
    backgroundColor: "#333", // You can use a dark color for the stage
    justifyContent: "center",
    alignItems: "center",
  },
  bottomContainer: {
    flex: 3,
  },
  carDetailsContainer: {
    flex: 2,
    backgroundColor: "white",
  },
  buttonContainer: {},
});
