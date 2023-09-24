import React, { useContext, useEffect } from 'react';
import { ActivityIndicator, Button, Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import CustomTextField from '../components/CustomTextField';
import LogInScreenViewController from '../viewController/LogInScreenViewController';
import { UserContext } from '../model/User';
import Ionicons from "@expo/vector-icons/Ionicons"
import PasswordField from '../components/PasswordField';
import { SafeAreaView } from 'react-native-safe-area-context';
import HyperlinkButton from '../components/HyperlinkButton';
import { styles } from "../components/Design";
import { Image, Icon } from 'react-native';
import { IMAGENAME } from '../../assets/images/index';
import * as Font from 'expo-font';
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';

SplashScreen.preventAutoHideAsync();

export default LogInScreen = ({ navigation }) => {

  const { isLoading,
    email,
    isReady,
    setIsReady,
    setShowpassword,
    password,
    setEmail,
    setPassword,
    logInButtonPressed,
    forgotPasswordButtonPressed,
    makeNewAccountButtonPressed
  } = LogInScreenViewController({ navigation });

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    
    }; 

    loadFonts();
  }, []);

  // if(!isReady){
  //     return (
  //     <AppLoading
  //       startAsync={loadFonts}
  //       onFinish={() => setIsReady(true)}
  //       onError={() => {}}
  //     />
  //   );
  // }

  if (!isReady) {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text>Loading...</Text>
      </View>
    );
  }

  return (
    <SafeAreaView style={styles.container}>
      <View>

        <View style={{alignItems: 'center', marginBottom:0, marginTop:25,backgroundColor:"green"}}>
          <Image
            source={IMAGENAME}
            style={{width: 500, height: 350, borderBottomWidth: 0}}
            
          />
      </View>


        <Text style={{...styles.header, textAlign:'center', marginTop: 0}}>Welcome To Electric </Text>
        <Text style={{...styles.subHeader, textAlign:'center', marginBottom:0}}> Welcome to EcoCharge Finder, your electric car's best friend. Log in to discover nearby charging stations and keep your EV on the move</Text>

        <View style={{padding:10}}>
        <CustomTextField
          placeholder="Email"
          onChangeText={setEmail}
        />

        <PasswordField
          placeholder="Password"
          onChangeText={setPassword}
          secureTextEntry={true}
        />
        </View>

      <View style={{marginLeft: 255}}>
        <HyperlinkButton
          title="Forgot Password?"
          onPress={forgotPasswordButtonPressed}
        />
      </View>

      <View style={{margin: 45, marginBottom: 35}}>
        <CustomLongButton
          title="Log In"
          onPress={logInButtonPressed}
        />
      </View>
      
      <View style={{flexDirection: 'row', marginLeft: 90}}>
        <Text style={{color: 'white'}}>Don't have an account?  </Text>
        <HyperlinkButton style={styles.hyperLinkText}
          title="Sign Up"
          onPress={makeNewAccountButtonPressed}
        />
      </View>

      <Button
        title="Load fake data"
        onPress={() => {
          setEmail("Leong123@gmail.com");
          setPassword("Leong123@gmail.com!");
        }}
      /> 

      <Text style={{fontSize:10, color:'white'}}>The current email is ${email}</Text>
      <Text style={{fontSize:10, color:'white'}}>The current password is ${password}</Text>

      </View>
    </SafeAreaView>
  );
}
