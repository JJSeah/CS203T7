import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import React, { useEffect }from 'react';
import FontAwesome from 'react-native-vector-icons/FontAwesome';
import SettingsScreenViewController from '../viewController/SettingsScreenViewController';
import SettingsButton from '../components/SettingsButton';
import { SafeAreaView } from 'react-native-safe-area-context';
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';
import { styles } from "../components/Design"; 
// Creating 2 sections: Account and Records
// Account: Profile, Vehicle Information, Payment Methods, Notification (need?)
// Suuport & Legal: Get Help, Privacy Policy, About

SplashScreen.preventAutoHideAsync();

export default SettingsScreen = ( { navigation } ) => {

  const { 
    isReady,
    setIsReady,
    profileButtonPressed, 
    vehicleInformationButtonPressed, 
    paymentMethodsButtonPressed, 
    notificationButtonPressed,
    getHelpButtonPressed,
    privacyPolicyButtonPressed,
    aboutButtonPressed, } = SettingsScreenViewController( { navigation } );

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
  
    loadFonts();
  }, []);

  return (

    // Account Section and Records Section
    <SafeAreaView style={styles.container}>

      {/* This is Account container */}

      <View style={styles.sectionContainer}>
        <View style={styles.section}>
          <Text style={styles.SectionHeader}>Account</Text>
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


      <View style={styles.sectionContainer}>
        <View style={styles.section}>
          <Text style={styles.sectionHeader}>Support & Legal</Text>
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
    </SafeAreaView>
  );
};

const settingStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff', 
    padding: 16,
  },
  sectionContainer: {
    marginBottom: 24,
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 8,
    backgroundColor: '#f9f9f9', 
  },
  section: {
    backgroundColor: '#ADD8E6', 
    paddingHorizontal: 12,
    paddingVertical: 8,
    borderTopLeftRadius: 8,
    borderTopRightRadius: 8,
  },
  
  sectionHeader: {
    fontSize: 20, // Account and Records
    fontWeight: 'bold',
    marginBottom: 8,
  },
});

