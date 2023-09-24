import React, { useContext } from "react";
import {
  Button,
  Text,
  View,
  ScrollView,
  TextBase,
  StyleSheet,
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
        <Text>Welcome {userData.username}</Text>
      </View>

      <View style={localStyles.swiperContainer}>
          <CarSwiperView
              cars={userCars}
              onSwiped={setCurrentCar}
              currentCar={currentCar}
          />
      </View>


      <View
        style={localStyles.bottomContainer}
      >


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
  },
  headerContainer: {
    flex: 1,
    backgroundColor: 'red'
  },
  swiperContainer: {
    flex: 6,
    backgroundColor: 'blue'
  },
  bottomContainer: {
    flex: 3, 
  },
  buttonContainer: {
  },
});
