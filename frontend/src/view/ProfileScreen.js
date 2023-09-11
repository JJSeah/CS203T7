import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';

export default ProfileScreen = () => {

    const { userData } = useContext(UserContext);

  return (
    <View>

      <Text>Welcome {userData.username}</Text>

        <Text>Your email is {userData.email}</Text>

        <Text>Your id is {userData.id}</Text>

        <Text>Your firstName is {userData.firstName}</Text>

        <Text>Your lastName is {userData.lastName}</Text>
    </View>
  );
}
