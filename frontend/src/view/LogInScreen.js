import React, { useContext } from 'react';
import { ActivityIndicator, Button, Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import CustomTextField from '../components/CustomTextField';
import LogInScreenViewController from '../viewController/LogInScreenViewController';
import { UserContext } from '../model/User';
import Ionicons from "@expo/vector-icons/Ionicons"
import PasswordField from '../components/PasswordField';
import { SafeAreaView } from 'react-native-safe-area-context';
import HyperlinkButton from '../components/HyperlinkButton';

export default LogInScreen = ( { navigation } ) => {
  
  const { isLoading, 
    email, 
    setShowpassword,
    password, 
    setEmail, 
    setPassword, 
    logInButtonPressed, 
    forgotPasswordButtonPressed, 
    makeNewAccountButtonPressed 
  } = LogInScreenViewController( { navigation } );


  return (
    <SafeAreaView>
        <View>   

          <Button
          title="Load fake data"
          onPress={() => {
            setEmail("Leong123@gmail.com");
            setPassword("Leong123@gmail.com!");
          }}
          />

          <Text>The current email is ${email}</Text>

          <Text>The current password is ${password}</Text>

          <CustomTextField
            placeholder="Email"
            onChangeText={setEmail}
          />      

          <PasswordField
            placeholder="Password"
            onChangeText={setPassword}
            secureTextEntry={true}
          />  
      
          
          <CustomLongButton
            title="Login"
            onPress={logInButtonPressed}
          />

          <HyperlinkButton
            title="Forgot Password"
            onPress={forgotPasswordButtonPressed}
          />

          <CustomLongButton
            title="Make new account"
            onPress={makeNewAccountButtonPressed}
          />

      </View>
    </SafeAreaView>
  );
}
