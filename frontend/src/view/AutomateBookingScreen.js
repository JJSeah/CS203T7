import React, { useContext, useEffect, useState } from 'react';
import { Text, View, useColorScheme } from 'react-native';
import * as Location from 'expo-location'
import { UserContext } from '../model/User';

export default AutomateBookingScreen = () => {
  
  const { coordinates } = useContext(UserContext);

  return (
      <View>
        <Text>This is the automate booking screen</Text>
        <Text>{coordinates.latitude}</Text>
        <Text>{coordinates.longitude}</Text>
      </View>
  );
}
