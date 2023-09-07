import React from 'react';
import { Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import HomeScreenViewController from '../viewController/HomeScreenViewController';

export default HomeScreen = ( { navigation } ) => {

  const { addCarButtonPressed, manualBookingButtonPressed, automateBookingButtonPressed } = HomeScreenViewController( { navigation} );

  return (
    <View>
      <Text>This is the home screen</Text>

      <CustomLongButton
        title="Add car"
        onPress={addCarButtonPressed}
      />

      <CustomLongButton
        title="Manual booking"
        onPress={manualBookingButtonPressed}
      />

      <CustomLongButton
        title="Automate booking"
        onPress={automateBookingButtonPressed}
      />

    </View>
  );
}
