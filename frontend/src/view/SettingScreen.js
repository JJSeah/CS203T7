import React from 'react'
import { Text, View, Button } from 'react-native'


export default SettingScreen = ( {navigation} ) => {

  const logOut = () => {
    navigation.navigate();
  }

  return (
    <View>
      <Text>Hello my Name is justin and I am doing settings</Text>
      <Button
        title="Log out"
        onPress={console.logOut}
      />
    </View>
  );
};
