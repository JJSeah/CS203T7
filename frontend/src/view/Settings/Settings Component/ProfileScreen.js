import React, { useContext } from 'react';
import { Text, View, Button, StyleSheet } from 'react-native';
import { UserContext } from '../../../model/User';
import InBetweenSpace from '../../../components/InBetweenSpace';
import SettingsScreenViewController from '../../../viewController/SettingsScreenViewController';
import CustomLongButton from '../../../components/CustomLongButton';
import { SafeAreaView } from 'react-native-safe-area-context';

// fonts

export default Profile = ( { navigation } ) => {

    const { userData } = useContext(UserContext);

    const { 
      editProfileButtonPressed, } = SettingsScreenViewController( { navigation } );

  return (
    <SafeAreaView style = {localStyles.container}>

      <View style = {localStyles.informationContainer}>

      <InBetweenSpace
      title="Username"
      value={userData.usernames}
      />

      <InBetweenSpace
      title="First Name"
      value={userData.firstName}
      />

      <InBetweenSpace
      title="Surname"
      value={userData.lastName}
      />

      <InBetweenSpace
      title="Email"
      value={userData.email}
      />
      
      </View>

      <View style = {localStyles.buttonContainer}>

        <CustomLongButton
          title="Edit Profile"
          onPress={editProfileButtonPressed}
          />
          </View>

    </SafeAreaView>
  );
}

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },
  informationContainer: {
    flex: 9,
  },
  buttonContainer: {
    flex: 1,
  },
});





