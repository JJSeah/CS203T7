import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import React from 'react';
import FontAwesome from 'react-native-vector-icons/FontAwesome';
import SettingsScreenViewController from '../viewController/SettingsScreenViewController';
import SettingsButton from '../components/SettingsButton';

// Creating 2 sections: Account and Records
// Account: Profile, Vehicle Information, Payment Methods, Notification (need?)
// Records: Billing History, Charging History

const styles = StyleSheet.create({
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

export default SettingsScreen = ( { navigation } ) => {

  const { 
    profileButtonPressed, 
    vehicleInformationButtonPressed, 
    paymentMethodsButtonPressed, 
    notificationButtonPressed,
    billingHistoryButtonPressed,
    chargingHistoryButtonPressed, } = SettingsScreenViewController( { navigation } );

  return (

    // Account Section and Records Section
    <ScrollView style={styles.container}>

      {/* This is Account container */}

      <View style={styles.sectionContainer}>
        <View style={styles.section}>
          <Text style={styles.sectionHeader}>Account</Text>
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
          <Text style={styles.sectionHeader}>Records</Text>
        </View>

        <SettingsButton
        title="Billing History"
        onPress={billingHistoryButtonPressed}
        />

        <SettingsButton
        title="Charging History"
        onPress={chargingHistoryButtonPressed}
        />
        {/* <TouchableOpacity onPress={() => handleItemPress('Billing History')}>
          <View style={styles.item}>
            <Text style={styles.itemText}>Billing History</Text>
            <FontAwesome name="angle-right" size={20} color="#ccc" /> 
          </View>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleItemPress('Charging History')}>
          <View style={styles.item}>
            <Text style={styles.itemText}>Charging History</Text>
            <FontAwesome name="angle-right" size={20} color="#ccc" /> 
          </View>
        </TouchableOpacity> */}
      </View>
    </ScrollView>
  );
};


