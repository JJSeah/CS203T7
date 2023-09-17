import React, { useState } from 'react';
import { Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton'
import CustomTextField from '../components/CustomTextField'
import AddCarScreenViewController from '../viewController/AddCarScreenViewController';
import CustomDropDownList from '../components/CustomDropDownList';

export default AddCarScreen = ( { navigation } ) => {

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
    clearAllFieldsPressed 
  } = AddCarScreenViewController( { navigation} )

  return (
    <View>

      <CustomDropDownList
        setSelected={model => {dropdownSelectListPressed(model)}}
        data={CarModelsData.map(car => car.model)}
        placeholder="Select car"
        searchPlaceholder="Name of model"
      />

      <Text>
        Car Model {model}
      </Text>

      <Text>
        Charging rate (kw) = {chargingRate}
      </Text>

      <Text>
        Battery capacity (kWh) = {batteryCapacity}
      </Text>


      <CustomTextField
        placeholder="Nickname"
        onChangeText={setNickname}
      />

      <CustomTextField
        placeholder="Battery"
        onChangeText={setBatteryPercentage}
      />

      <CustomTextField
        placeholder="Car Plate"
        onChangeText={setCarPlate}
      />

      <CustomLongButton
        title="Add car"
        onPress={addCarButtonPressed}
      />



    </View>
  );
}
