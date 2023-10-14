import React, { useContext } from 'react';
import { Text, View, Button, StyleSheet } from 'react-native';
import { UserContext } from '../../../model/User';
import InBetweenSpace from '../../../components/InBetweenSpace';
import SettingsScreenViewController from '../../../viewController/SettingsScreenViewController';
import CustomLongButton from '../../../components/CustomLongButton';

// fonts

export default ProfileScreen = ( { navigation } ) => {

    const { userData } = useContext(UserContext);

    const { 
      editProfileButtonPressed, } = SettingsScreenViewController( { navigation } );

  return (
    <View style = {localStyles.container}>

      {/* Username can't be edited */}
      <View style = {localStyles.informationContainer}>

      <InBetweenSpace
      title="Username"
      value={userData.username}
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
          title="Edit"
          onPress={editProfileButtonPressed}
          />
          </View>

    </View>
  );
}

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },
  informationContainer: {
    flex: 9,
    backgroundColor: "#fefe",
  },
  buttonContainer: {
    flex: 1,
  },
});





