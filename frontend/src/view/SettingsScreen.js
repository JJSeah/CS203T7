import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import React from 'react';
import FontAwesome from 'react-native-vector-icons/FontAwesome';

// Creating 2 sections: Account and Records
// Account: Profile, Vehicle Information, Payment Methods, Notification (need?)
// Records: Billing History, Charging History

const testDate = {
  "name": "user",
  "email": "user@smu.edu.sg"
}

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
  item: {
    flexDirection: 'row', // Aligning text and icon horizontally
    alignItems: 'center', 
    paddingHorizontal: 12,
    paddingVertical: 12,
    justifyContent: 'space-between', // Right arrow to the right
  },
  itemText: {
    fontSize: 18,
    flex: 1,
    marginRight: 16, // icon and text spacing
  },
});

export default SettingsScreen = () => {
  return (

    // Account Section and Records Section
    <ScrollView style={styles.container}>
      <View style={styles.sectionContainer}>
        <View style={styles.section}>
          <Text style={styles.sectionHeader}>Account</Text>
        </View>
        <TouchableOpacity onPress={() => handleItemPress('Profile')}>
          <View style={styles.item}>
            <Text style={styles.itemText}>Profile</Text>
            <FontAwesome name="angle-right" size={20} color="#ccc" /> 
          </View>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleItemPress('Vehicle Information')}>
          <View style={styles.item}>
            <Text style={styles.itemText}>Vehicle Information</Text>
            <FontAwesome name="angle-right" size={20} color="#ccc" /> 
          </View>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleItemPress('Payment Methods')}>
          <View style={styles.item}>
            <Text style={styles.itemText}>Payment Methods</Text>
            <FontAwesome name="angle-right" size={20} color="#ccc" /> 
          </View>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleItemPress('Notification')}>
          <View style={styles.item}>
            <Text style={styles.itemText}>Notification</Text>
            <FontAwesome name="angle-right" size={20} color="#ccc" /> 
          </View>
        </TouchableOpacity>
      </View>

      <View style={styles.sectionContainer}>
        <View style={styles.section}>
          <Text style={styles.sectionHeader}>Records</Text>
        </View>
        <TouchableOpacity onPress={() => handleItemPress('Billing History')}>
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
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
};


