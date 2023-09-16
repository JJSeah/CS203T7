import React, { useContext } from 'react';
import { Text, View, Button, StyleSheet } from 'react-native';
import { UserContext } from '../model/User';
import InBetweenSpace from '../components/InBetweenSpace';

export default ProfileScreen = () => {

    const { userData } = useContext(UserContext);

  return (
    <View>

      {/* Username can't be edited */}

      <InBetweenSpace
      title="Username"
      value={userData.username}
      />

      <InBetweenSpace
      title="Given Name"
      value={userData.firstName}
      />

      <InBetweenSpace
      title="Surname"
      value={userData.lastName}
      />

      <InBetweenSpace
      title="email"
      value={userData.email}
      />

    </View>
  );
}


