import React from 'react';
import { Text, View, Button, TextInput, Image} from 'react-native';
import LogInViewController from '../viewController/LogInViewController';;
import TextField from '../components/TextField';

export default WelcomeScreen = ( { navigation } ) => {

  const { setEmail, setPassword, logInButtonPressed, securePasswordEntry } = LogInViewController( { navigation } )

  const navigateToRegisterScreen = () => {
    navigation.navigate('RegisterScreen');
  }

  const showForgetPasswordSheet = () => {
    console.log("haha")
  }

  return (
    <View>
      <Text>This is the welcome screen</Text>

      <TextField
        placeholder='Email'
        onChangeText={setEmail}
      />

      <TextInput
        placeholder='Password'
        onChangeText={input => setPassword(input)}
        secureTextEntry={securePasswordEntry}
      />

      <Button
        title='Login' 
        onPress={logInButtonPressed}
      />

      <Button
        title='Create New Account' 
        onPress={navigateToRegisterScreen}
      />  

    </View>
  );
};
