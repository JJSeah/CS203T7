import React, { useContext } from 'react'
import { Text, View, Button } from 'react-native'
import SettingsViewController from '../viewController/SettingsViewController';

export default HomeScreen = ( ) => {

  const { logOutButtonPressed } = SettingsViewController();

  return (
    <View>
      <Button
        title="log out"
        onPress={logOutButtonPressed}
      />

      <Text>this is the home screen</Text>
    </View>
  );
};
