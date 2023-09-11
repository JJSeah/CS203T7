import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';

export default HistoryScreen = () => {

  const { userData } = useContext(UserContext);

  return (
    <View>
      
      <Text>This is the history screen</Text>
    </View>
  );
}
