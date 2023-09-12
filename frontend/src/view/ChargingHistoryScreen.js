import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';

export default ChargingHistoryScreen = () => {

    const { userData } = useContext(UserContext);

  return (
    <View>
      <Text>This is Charging History Screen</Text>
    </View>
  );
}
