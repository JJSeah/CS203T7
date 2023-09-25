import React, { useContext } from "react";
import {Button, Text, View, ScrollView, TextBase, StyleSheet, Image,} from "react-native";
import CustomLongButton from "../components/CustomLongButton";
import HomeScreenViewController from "../viewController/HomeScreenViewController";
import { UserContext } from "../model/User";
import CarSwipeView from "../components/SingleCarSwiperView";
import { useFocusEffect } from "@react-navigation/native";
import { CarRepository } from "../model/CarRepository";
import { SafeAreaView } from "react-native-safe-area-context";
import { styles } from "../components/Design";
import CarSwiperView from "./CarSwiperView";

import { TESLA1 } from '../../assets/images/index';





export default HomeScreen = ({ navigation }) => {
  const {
    addCarButtonPressed,
    manualBookingButtonPressed,
    automateBookingButtonPressed,
  } = HomeScreenViewController({ navigation });

  const { userData, userCars, logOut, setCurrentCar, currentCar } = useContext(UserContext);

  const { loadCarsData } = CarRepository();

  useFocusEffect(
    React.useCallback(() => {
      loadCarsData();
    }, [])
  );

  return (
    <SafeAreaView style={localStyles.container}>

      <View style={localStyles.headerContainer}>
        <Text style = {localStyles.headerText}>
          Welcome back, {userData.username}!
          </Text>

          {(currentCar !== null) ? (
            <>
            <Text style = {localStyles.subHeaderText}>
              {currentCar.model}
            </Text>
            <Text style = {localStyles.subHeaderText}>
                {currentCar.nickname}
              </Text>
              <Image
            source={TESLA1}
            style = {localStyles.carStyle}/>
            </>
            ) : (
            <Text style = {localStyles.subHeaderText}>
              You do not have a car yet
              </Text> 
              )
          }
          
      </View>

      {/* <View style = {localStyles.carShowStage}></View> */}

      
      

      {/* <View style={localStyles.swiperContainer}>
          <CarSwiperView
          />
      </View> */}


      {/* <View
        style={localStyles.bottomContainer}
      >
        
        <CustomLongButton title="Add car" onPress={addCarButtonPressed}/>

        <CustomLongButton
          title="Manual booking"
          onPress={manualBookingButtonPressed}
        />

        <CustomLongButton
          title="Automate booking"
          onPress={automateBookingButtonPressed}
        />

      </View> */}

    </SafeAreaView>
  );
};



const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#141414',
  },


  headerContainer: {
    flex: 1,
    // backgroundColor: 'red',
    alignItems: 'center',
  },
  headerText: {
    fontFamily: 'Product-Sans-Regular',
    fontWeight: 'bold',
    fontSize: 20,
    color: 'white',
    marginTop: 85,
    marginBottom: 20,
  },

  subHeaderText: {
    fontFamily: 'Product-Sans-Regular',
    fontSize: 14,
    color: 'white',
    marginTop: 10,
  },
  carStyle: {
    marginTop: 60,
    width: 400,
    height: 300,
  },
  // swiperContainer: {
  //   flex: 1,
  //   backgroundColor: 'blue'
  // },
  // carShowStage: {

  //   height: 300, // Adjust the height of the car show stage as needed
  //   backgroundColor: '#333', // You can use a dark color for the stage
  //   justifyContent: 'center',
  //   alignItems: 'center',
  // },
  bottomContainer: {
    flex: 1,
  },
  buttonContainer: {

  },
});
