import React, { useContext } from 'react';
import { Button, Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import HomeScreenViewController from '../viewController/HomeScreenViewController';
import { UserContext } from '../model/User';

export default HomeScreen = ( { navigation } ) => {

  const { addCarButtonPressed, manualBookingButtonPressed, automateBookingButtonPressed} = HomeScreenViewController( { navigation} );

  const { userData, logOut } = useContext(UserContext);

  return (
    <View>

      <Text>Welcome {userData.username}</Text>

      <Text>Your email is {userData.email}</Text>

      <Text>Your id is {userData.id}</Text>

      <Text>Your firstName is {userData.firstName}</Text>

      <Text>Your lastName is {userData.lastName}</Text>


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
