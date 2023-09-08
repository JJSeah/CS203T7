import React, { useContext } from 'react';
import { ActivityIndicator, Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import CustomTextField from '../components/CustomTextField';
import LogInScreenViewController from '../viewController/LogInScreenViewController';
import { UserContext } from '../model/User';
import Ionicons from "@expo/vector-icons/Ionicons"

export default LogInScreen = ( { navigation } ) => {
  
  const { isLoading, setEmail, setPassword, logInButtonPressed, forgotPasswordButtonPressed, makeNewAccountButtonPressed } = LogInScreenViewController( { navigation } );
  const { userToken } = useContext(UserContext);

  return (
    <View>   

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
