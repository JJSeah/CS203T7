import React, { useContext } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { UserContext } from '../../../model/User';
import CarInformation from '../../../components/CarInformation';
import CustomLongButton from '../../../components/CustomLongButton';

import DeleteCarViewController from "../../../viewController/DeleteCarViewController";


export default VehicleInformationScreen = () => {

  const { userCars } = useContext(UserContext);

  const { deleteCarButtonPressed } = DeleteCarViewController();

  return (
    <View style = {localStyles.container}>


{Array.isArray(userCars) && userCars.length > 0 ? (
       userCars.map((car) => (
          <View key={car.id} style = {localStyles.sectionContainer}>

            <CarInformation
            title = "Car Nickname"
            value = {car.nickname}
            />
            <CarInformation
            title = "BatteryCapacity"
            value = {car.batteryCapacity}
            />
            <CustomLongButton
            title= "delete car"
            onPress={() => deleteCarButtonPressed(car.id)}
            />
          </View>
        ))
      ) : (
        <Text>NO car loading hehe</Text>
      )}
      
    </View>
  );
};

const localStyles = StyleSheet.create ({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },
  sectionContainer: {
    marginBottom: 24,
    borderWidth: 1,
    borderColor: '#ccc',
    backgroundColor: '#f9f9', 
  },

});

