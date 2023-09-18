import React, { useContext } from 'react';
import { Text, View, Button, StyleSheet } from 'react-native';
import { UserContext } from '../model/User';
import InBetweenSpace from '../components/InBetweenSpace';
import SettingsScreenViewController from '../viewController/SettingsScreenViewController';
import CustomLongButton from '../components/CustomLongButton';

export default ProfileScreen = ( { navigation } ) => {

    const { userData } = useContext(UserContext);

    const { 
      editProfileButtonPressed, } = SettingsScreenViewController( { navigation } );

  return (
    <View>

      {/* Username can't be edited */}

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

        <CustomLongButton
          title="Edit"
          onPress={editProfileButtonPressed}
        />

    </View>
  );
}





