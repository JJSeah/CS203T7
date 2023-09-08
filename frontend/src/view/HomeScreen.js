import React, { useContext } from 'react';
import { Button, Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import HomeScreenViewController from '../viewController/HomeScreenViewController';
import { UserContext } from '../model/User';

export default HomeScreen = ( { navigation } ) => {

  const { addCarButtonPressed, manualBookingButtonPressed, automateBookingButtonPressed} = HomeScreenViewController( { navigation} );

  const { userToken, logOut } = useContext(UserContext);

  return (
    <View>

      <Text>This is the home screen</Text>
      <Text>{userToken}</Text>
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

      <CustomLongButton
        title="Log out user" 
        onPress={logOut}
      />

    </View>
  );
}
