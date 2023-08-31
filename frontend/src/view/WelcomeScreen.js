import React from 'react';
import { Text, View, Button, TextInput, Image} from 'react-native';
import LogInViewController from '../viewController/LogInViewController';;

export default WelcomeScreen = ( { navigation } ) => {

  const { setEmail, setPassword, logInUser, securePasswordEntry } = LogInViewController( { navigation } )

  const navigateToRegisterScreen = () => {
    navigation.navigate('RegisterScreen');
  }

  const showForgetPasswordSheet = () => {
    console.log("haha")
  }

  return (
    <View>
      <Text>This is the welcome screen</Text>
      <TextInput
        onChangeText={input => setEmail(input)}
        placeholder='Email'
      />

      <TextInput
        placeholder='Password'
        onChangeText={input => setPassword(input)}
        secureTextEntry={securePasswordEntry}
      />


      <Button
        title='Login' 
        onPress={logInUser}
      />

      <Button
        title='Create New Account' 
        onPress={navigateToRegisterScreen}
      />  

    </View>
  );
};
