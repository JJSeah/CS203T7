import React, { useState } from "react";
import { Text, View, StyleSheet } from "react-native";
import CustomLongButton from "../components/CustomLongButton";
import CustomTextField from "../components/CustomTextField";
import AddCarScreenViewController from "../viewController/AddCarScreenViewController";
import CustomDropDownList from "../components/CustomDropDownList";
import { SafeAreaView } from "react-native-safe-area-context";
import { styles } from '../components/Design'

export default AddCarScreen = ({ navigation }) => {
  const {
    model,
    chargingRate,
    batteryCapacity,
    CarModelsData,
    dropdownSelectListPressed,
    setNickname,
    addCarButtonPressed,
    setBatteryPercentage,
    setCarPlate,
    clearAllFieldsPressed,
  } = AddCarScreenViewController({ navigation });

  return (
    <SafeAreaView style={localStyles.container}>
      <View 
        style={localStyles.topContainer}
      >
        <View>
          <CustomDropDownList
            setSelected={(model) => {
              dropdownSelectListPressed(model);
            }}
            data={CarModelsData.map((car) => car.model)}
            placeholder="Select car"
            searchPlaceholder="Name of model"
          />
        </View>

        <View>
          <Text>Car Model {model}</Text>

          <Text>Charging rate (kw) = {chargingRate}</Text>

          <Text>Battery capacity (kWh) = {batteryCapacity}</Text>
        </View>

        <View>
          <CustomTextField placeholder="Nickname" onChangeText={setNickname} />

          <CustomTextField
            placeholder="Battery"
            onChangeText={setBatteryPercentage}
          />

          <CustomTextField placeholder="Car Plate" onChangeText={setCarPlate} />
        </View>
      </View>

      <View
        style={localStyles.buttonsContainer}
      >
        <CustomLongButton title="Add car" onPress={addCarButtonPressed} />
      </View>

    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "stretch",
    backgroundColor: "black",
  },
  topContainer: {
    flex: 9
  },
  buttonsContainer: {
    flex : 1
  }
});
