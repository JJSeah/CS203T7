import React from 'react';
import { Text, View, Button, TextInput, Image, TouchableOpacity, StyleSheet } from 'react-native';
import LogInViewController from '../viewController/LogInViewController';;
import TextField from '../components/TextField';
import RectangularButton from '../components/RectangularButton';
import LinkButton from '../components/LinkButton';

export default WelcomeScreen = ( { navigation } ) => {

  const { setEmail, setPassword, logInButtonPressed, securePasswordEntry } = LogInViewController( { navigation } )

  return (
    <View>
      <Text>This is the welcome screen</Text>

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
        onPress={() => {navigation.navigate("RegisterScreen")}}
      />

    </View>
  );
};

