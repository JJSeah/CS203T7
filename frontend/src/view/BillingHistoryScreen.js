import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';

export default BillingHistoryScreen = () => {

    const { userData } = useContext(UserContext);

  return (
    <View>
      <Text>This is Billing History Screen</Text>
    </View>
  );
}
