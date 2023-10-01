import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';
import { SafeAreaView } from 'react-native-safe-area-context';

export default ReminderToAddCarScreen = () => {


  return (
    <SafeAreaView>
      <Text>Add at least one car</Text>
    </SafeAreaView>
  );
}