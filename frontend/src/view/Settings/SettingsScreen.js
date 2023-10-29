import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import React, { useContext } from 'react';
import FontAwesome from 'react-native-vector-icons/FontAwesome';
import SettingsScreenViewController from '../../viewController/SettingsScreenViewController';
import SettingsButton from '../../components/SettingsButton';
import { SafeAreaView } from 'react-native-safe-area-context';
import { UserContext } from '../../model/User';


// Creating 2 sections: Account and Records
// Account: Profile, Vehicle Information, Payment Methods, Notification (need?)
// Suuport & Legal: Get Help, Privacy Policy, About


export default SettingsScreen = ( { navigation } ) => {

  const { 
    profileButtonPressed, 
    vehicleInformationButtonPressed, 
    paymentMethodsButtonPressed, 
    notificationButtonPressed,
    getHelpButtonPressed,
    privacyPolicyButtonPressed,
    aboutButtonPressed, } = SettingsScreenViewController( { navigation } );

  const { logOut }  = useContext(UserContext)

  return (

    // Account Section and Records Section
    
    <SafeAreaView style={localStyles.container}>
      <View style = {localStyles.sectionContainer}>

      <View style={localStyles.sectionInformation}>

        <View style={localStyles.sectionHeader}>
          <Text style = {localStyles.headerText}>Account</Text>
          </View>

        <SettingsButton
          title="Profile"
          onPress={profileButtonPressed}
          />

        <SettingsButton
          title="Vehicle Information"
          onPress={vehicleInformationButtonPressed}
          />

        <SettingsButton
          title="Payment Methods"
          onPress={paymentMethodsButtonPressed}
          />

        <SettingsButton
          title="Notification"
          onPress={notificationButtonPressed}
          />
      </View>

      {/* This is records container*/}


      <View style={localStyles.sectionInformation}>
        <View style={localStyles.sectionHeader}>
          <Text style = {localStyles.headerText}>Support & Legal</Text>
        </View>

        <SettingsButton
        title="Get Help"
        onPress={getHelpButtonPressed}
        />
        <SettingsButton
        title="Privacy Policy"
        onPress={privacyPolicyButtonPressed}
        />
        <SettingsButton
        title="About"
        onPress={aboutButtonPressed}
        />
      </View>
      <View>
        </View>
            <View style = {localStyles.buttonContainer}>
          <CustomLongButton title="Log out user" onPress={logOut} />
            </View>

      </View>
    </SafeAreaView>

  );
};

 const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },
  sectionContainer: {
    flex: 9,
  },
  
  buttonContainer: {
    flex: 1,
  },

  sectionHeader: {
    backgroundColor: '#808080',
    borderTopLeftRadius: 9,
    borderTopRightRadius: 9,
  },
  sectionInformation: {
    marginBottom: 24,
    borderWidth: 1,
    borderRadius: 10,
    backgroundColor: '#f9f9f9',
  },
  headerText: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 20,
    fontWeight: 'bold',
    marginLeft: 10,
    marginTop: 10,
    marginBottom: 5,
  },
  
});
