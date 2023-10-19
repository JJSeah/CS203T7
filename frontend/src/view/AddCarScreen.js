import React, { useEffect, useState } from "react";
import { Text, View, StyleSheet } from "react-native";
import CustomLongButton from "../components/CustomLongButton";
import CustomTextField from "../components/CustomTextField";
import AddCarScreenViewController from "../viewController/AddCarScreenViewController";
import CustomDropDownList from "../components/CustomDropDownList";
import { SafeAreaView } from "react-native-safe-area-context";
import { styles } from "../components/Design";
import { Picker } from "@react-native-picker/picker";

export default AddCarScreen = ({ navigation }) => {
  const {
    model,
    nickname,
    chargingRate,
    batteryCapacity,
    CarModelsData,
    carPlate,
    batteryPercentage,
    dropdownSelectListPressed,
    setNickname,
    addCarButtonPressed,
    setBatteryPercentage,
    setCarPlate,
    clearAllFieldsPressed,
  } = AddCarScreenViewController({ navigation });

  const pickerValues = Array.from({ length: 101 }, (_, i) => i);

  useEffect( () => {

  }, [carPlate])

  return (
    <SafeAreaView style={localStyles.container}>
      <View style={localStyles.topContainer}>
        <View>
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

          <View style={{ margin: 20 }}>
            <Text style={styles.bodyText}>Car Model {model}</Text>

            <Text style={styles.bodyText}>
              Charging rate (kw) = {chargingRate}
            </Text>

            <Text style={styles.bodyText}>
              Battery capacity (kWh) = {batteryCapacity}
            </Text>
          </View>

          <View>
            <CustomTextField
              placeholder="Nickname"
              onChangeText={setNickname}
            />

            <Text 
              // style={{ color: "white" }}
            >{batteryPercentage}</Text>

            <CustomTextField
              placeholder="Battery"
              onChangeText={setBatteryPercentage}
            />

            <CustomTextField
              placeholder="Car Plate"
              onChangeText={setCarPlate}
            />
          </View>
        </View>
      </View>

      <View style={localStyles.buttonsContainer}>
        <CustomLongButton title="Add car" onPress={addCarButtonPressed} disabled={
          model === "" ||
          nickname === "" ||
          carPlate === ""
          }
        />
      </View>
    </SafeAreaView>
  );
};

{
  /* <Picker */
}
// selectedValue={batteryPercentage}
// onValueChange={(itemValue, itemIndex) => setBatteryPercentage(itemValue)}
// >
{
  /* {pickerValues.map((value) => ( */
}
// <Picker.Item style={{color:"white"}} key={value} label={value.toString()} value={value}/>
// ))}
{
  /* </Picker> */
}

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "stretch",
    margin: 10,
    // backgroundColor: "black",
  },
  topContainer: {
    flex: 9,
  },
  buttonsContainer: {
    flex: 1,
  },
});
