import React, { useContext } from 'react';
import { Button, View } from 'react-native';
import LogInViewController from '../viewController/LogInViewController';
import TextField from '../components/TextField';
import RectangularButton from '../components/RectangularButton';
import LinkButton from '../components/LinkButton';
import PasswordField from '../components/PasswordField';
import { AuthContext } from '../context/AuthContext';


export default WelcomeScreen = ( { navigation } ) => {

  const { setEmail, setPassword, logInButtonPressed } = LogInViewController ( { navigation } )
   
  

  const navigateToRegisterScreen = () => {
    navigation.navigate("RegisterScreen")
  }

  return (
    <View>

      <TextField
        placeholder='LOL'
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

 