import React, { useContext } from 'react';
import { Text, View, Button, StyleSheet } from 'react-native';
import { UserContext } from '../../../model/User';
import InBetweenSpace from '../../../components/InBetweenSpace';
import CustomTextField from '../../../components/CustomTextField';


export default EditProfileScreen = ( { navigation } ) => {

    const { userData } = useContext(UserContext);


  return (
    <View>

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
  );
}





