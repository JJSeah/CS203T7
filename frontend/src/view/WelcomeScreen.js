import React from 'react'
import { Text, View, Button } from 'react-native'
import ForgetPasswordModal from "./ForgetPasswordModal"

export default WelcomeScreen = ( {navigation} ) => {

  const navigateToRegisterScreen = () => {
    navigation.navigate('RegisterScreen');
  }

  const navigateToTabNavigator = () => {
    navigation.navigate('TabNavigator');
    // return ForgetPasswordModal();
  }

  return (
    <View>
      <ForgetPasswordModal/>
      {/* <Text>This is the welcome screen</Text>
      <Button
        title='Login' 
        onPress={navigateToTabNavigator}
      />
      <Button
        title='Create New Account' 
        onPress={navigateToRegisterScreen}
      /> */}
    </View>
  );
};
