import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../../../model/User';

export default VehicleInformationScreen = () => {

  const { userCars } = useContext(UserContext);

  return (
    <View>

      { userCars.map(car => {
        return (
          <View key={car.id}>
            <Text>{car.nickname}</Text>
            <Text>{car.batteryCapacity}</Text>
          </View>
        )
      })}
    </View>
  );
}
