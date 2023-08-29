import React from 'react'
import { Text, View, Button } from 'react-native'


export default SettingScreen = ( {navigation} ) => {

  const logOut = () => {
    navigation.navigate();
  }

  return (
    <View>
      <Text>This is the setting screen</Text>
      <Button
        title="Log out"
        onPress={console.logOut}
      />
    </View>
  );
};
