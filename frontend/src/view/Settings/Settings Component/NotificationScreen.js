import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../../../model/User';

export default NotificationScreen = () => {

    const { userData } = useContext(UserContext);

  return (
    <View>
      <Text>this is notification screen</Text>
    </View>
  );
}
