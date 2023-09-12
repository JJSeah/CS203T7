import React, { useContext } from 'react';
import { Text, View, useColorScheme } from 'react-native';
import { MapContext, MapProvider } from '../model/MapRepository';

export default AutomateBookingScreen = () => {

  return (
    <MapProvider>
      <View>
        <Text>This is the automate booking screen</Text>
      </View>
    </MapProvider>
  );
}
