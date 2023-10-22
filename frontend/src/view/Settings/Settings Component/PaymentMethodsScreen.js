import React, { useContext } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";

import { UserContext } from '../../../model/User';
import AddCardViewController from '../../../viewController/AddCardViewController';
import CustomLongButton from '../../../components/CustomLongButton';


export default PaymentMethodsScreen = ( {navigation} ) => {

    const { userCard } = useContext(UserContext);
    const { 
      addCardButtonPressed, } = SettingsScreenViewController( { navigation } );


  return (
    <SafeAreaView style = {localStyles.container}>
      <View style = {localStyles.detailsContainer}>
      <CustomLongButton
          title="Add Card"
          onPress={addCardButtonPressed}
          />

      </View>
    </SafeAreaView>
  );
}

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "stretch",
    backgroundColor: "black",
  },
  detailsContainer: {
    flex: 9
  },
  buttonContainer: {
    flex : 1
  }

});
