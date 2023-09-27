import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';

export default AboutScreen = () => {

    const { userData } = useContext(UserContext);

  return (
    <View>
      <Text>{userData.name}</Text>
      <Text>{userData.email}</Text>
      <Text>This is About Screen</Text>
    </View>
  );
}