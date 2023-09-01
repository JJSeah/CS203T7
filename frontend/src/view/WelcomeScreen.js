import React from 'react';
import { Text, View, Button, TextInput, Image, TouchableOpacity, StyleSheet } from 'react-native';
import LogInViewController from '../viewController/LogInViewController';;
import TextField from '../components/TextField';
import RectangularButton from '../components/RectangularButton';
import LinkButton from '../components/LinkButton';

export default WelcomeScreen = ( { navigation } ) => {

  const { email, setEmail, setPassword, logInButtonPressed, securePasswordEntry, resetFields, } = LogInViewController( { navigation } )

  const navigateToRegisterScreen = () => {
    navigation.navigate("RegisterScreen")
  }

  return (
    <View>

      <TextField
        placeholder='Email'
        onChangeText={setEmail}
      />

      <TextField
        placeholder='Password'
        onChangeText={setPassword}
      />

      <RectangularButton
        title="Log in"
        onPress={logInButtonPressed}
      />

      <LinkButton
        title='Forgotten password?'
      />

      <RectangularButton
        title="Create new account"
        onPress={navigateToRegisterScreen}
      />

    </View>
  );
};

