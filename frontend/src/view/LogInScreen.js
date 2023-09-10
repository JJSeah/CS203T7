import React, { useContext } from 'react';
import { ActivityIndicator, Button, Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import CustomTextField from '../components/CustomTextField';
import LogInScreenViewController from '../viewController/LogInScreenViewController';
import { UserContext } from '../model/User';
import Ionicons from "@expo/vector-icons/Ionicons"

export default LogInScreen = ( { navigation } ) => {
  
  const { isLoading, email, password, setEmail, setPassword, logInButtonPressed, forgotPasswordButtonPressed, makeNewAccountButtonPressed } = LogInScreenViewController( { navigation } );


  return (
    <View>   

      <Button
      title="load daniel ta data"
      onPress={() => {
        setEmail("johndoe@example.com");
        setPassword("mysecretpassword");
      }}
      />

      <Button
      title="load tames doe data"
      onPress={() => {
        setEmail("tame@example.com");
        setPassword("myswdcretpassword");
      }}
      />

      <Text>The current email is ${email}</Text>

      <Text>The current password is ${password}</Text>

      <CustomTextField
        placeholder="Email"
        onChangeText={setEmail}
      />      

      <CustomTextField
        placeholder="Password"
        onChangeText={setPassword}
      />      

      <CustomLongButton
        title="Login"
        onPress={logInButtonPressed}
      />

      <CustomLongButton
        title="Forgot Password"
        onPress={forgotPasswordButtonPressed}
      />

      <CustomLongButton
        title="Make new account"
        onPress={makeNewAccountButtonPressed}
      />

    </View>
  );
}
