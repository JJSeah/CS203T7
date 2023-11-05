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
    isSingaporeCarPlateNumber,
    setValidInput,
    validInput,
    setCarPlateMessage,
    carPlateMessage
  } = AddCarScreenViewController({ navigation });


  useEffect(() => {
    
    if (isSingaporeCarPlateNumber(carPlate)) {
      setCarPlateMessage("")
    } else {
      setCarPlateMessage("Incorrect format for car plate number")
    }

    if (isSingaporeCarPlateNumber(carPlate) && model !== "" && nickname !== "") {
      setValidInput(true)
      return;
    } 

    setValidInput(false)
  }, [carPlate, model, nickname]);

  return (
    <SafeAreaView style={localStyles.container}>
      <View style={localStyles.topContainer}>
        <View>
          <View
          style={{padding: 10}} 
          >
            <CustomDropDownList
              setSelected={(model) => {
                dropdownSelectListPressed(model);
              }}
              data={CarModelsData.map((car) => car.model)}
              placeholder="Select car model"
              searchPlaceholder="Name of model"
            />
          </View>

          <View style={{ margin: 20 }}>
            <Text style={styles.bodyText}>
              Car Model {model === "" ? "" : `= ${model}`}
            </Text>
            <Text style={styles.bodyText}>
              Charging rate {chargingRate === 0 ? "" : `= ${chargingRate} kW`}
            </Text>

            <Text style={styles.bodyText}>
              Battery capacity{" "}
              {batteryCapacity === 0 ? "" : `= ${batteryCapacity} kW/h`}
            </Text>
          </View>

          <View>
            <View
              style={{
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <CustomTextField
                placeholder="Nickname"
                onChangeText={setNickname}
              />

              <CustomTextField
                placeholder="Car Plate"
                onChangeText={setCarPlate}
              />

              <View
              >
              {
                carPlateMessage === "" ? 
                <></> :
                <View
                >
                  <Text
                    style={{color:"grey"}} 
                  >{carPlateMessage}</Text>
                </View>
              }
              </View>
            </View>

            <View>
              <View style={{ margin: 20 }}>
                <Text style={styles.bodyText}>
                  Battery percentage = {batteryPercentage}%
                </Text>
              </View>

              <View>
                <Picker
                  selectedValue={batteryPercentage}
                  onValueChange={(itemValue, itemIndex) =>
                    setBatteryPercentage(itemValue)
                  }
                >
                  {[...Array(100).keys()].map((value) => (
                    <Picker.Item
                      key={value}
                      color="white"
                      label={value.toString()}
                      value={value}
                    />
                  ))}
                </Picker>
              </View>
            </View>
          </View>
        </View>
      </View>

      <View style={localStyles.buttonsContainer}>
        <CustomLongButton
          title="Add car"
          onPress={addCarButtonPressed}
          disabled={!validInput}
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
    // margin: 10,
    backgroundColor: "#141414",
  },
  topContainer: {
    flex: 9,
  },
  buttonsContainer: {
    flex: 1,
  },
});
