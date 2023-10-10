import React, { useContext } from "react";
import { StyleSheet, Text, View, ActivityIndicator, SafeAreaView } from "react-native";
import { UserContext } from "../../../model/User";
import CarInformation from "../../../components/CarInformation";
import CustomLongButton from "../../../components/CustomLongButton";

import DeleteCarViewController from "../../../viewController/DeleteCarViewController";

export default VehicleInformationScreen = () => {
  const { userData, userCars, setCurrentCar, currentCar } =
    useContext(UserContext);

  const { deleteCarButtonPressed } = DeleteCarViewController();

  return (
    <SafeAreaView style={localStyles.container}>
      {userCars === null ? (
        <View>
          <ActivityIndicator />
        </View>
      ) : Array.isArray(userCars) && userCars.length > 0 ? (
        userCars.map((car) => (
          <View key={car.id} style={localStyles.sectionContainer}>
            <View key={car.id} style={localStyles.informationContainer}>
              <CarInformation title="Car Nickname" value={car.nickname} />
              <CarInformation
                title="BatteryCapacity"
                value={car.batteryCapacity}
              />
            </View>

            <CustomLongButton
              title="delete car"
              onPress={() => deleteCarButtonPressed(car.id)}
            />
          </View>
        ))
      ) : (
        <Text>NO cars loaded</Text>
      )}
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },
  sectionContainer: {
    marginBottom: 24,
    borderWidth: 1,
    borderColor: "#ccc",
    backgroundColor: "#f9f9",
  },
  informationContainer: {},
  buttonContainer: {},
});

{
  /*<CarInformation
title = "Model"
value = {car.model}
/>
<CarInformation
title = "Plate"
value = {car.plate}
/>*/
}
