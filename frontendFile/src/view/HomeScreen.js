import React from 'react'
import { Text, View, Button } from 'react-native'
import SettingsViewController from '../viewController/SettingsViewController';
import HomeScreenViewController from '../viewController/HomeScreenViewController';
import CarsSwipeView from '../components/CarsSwipeView';

export default HomeScreen = ( ) => {

  const { logOutButtonPressed } = SettingsViewController();
  const { userInfo } = HomeScreenViewController();

  return (
    <View>

      <Text> Hi { userInfo.username} </Text>

      <CarsSwipeView cars={userInfo.cars}
      />

      <Text>{ userInfo.username }</Text>
      <Text>{ userInfo.firstName }</Text>
      <Text>{ userInfo.lastName }</Text>
      <Text>{ userInfo.email }</Text>

    



      <Button
        title="log out"
        onPress={logOutButtonPressed}
      />
      
    </View>
  );
};
