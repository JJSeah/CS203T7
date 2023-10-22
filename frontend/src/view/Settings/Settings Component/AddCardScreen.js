import React, { useContext } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";

import { UserContext } from '../../../model/User';
import AddCardViewController from '../../../viewController/AddCardViewController';
import CustomLongButton from '../../../components/CustomLongButton';


export default AddCardScreen = ( {navigation} ) => {

    const {
      setName,
      setNumber,
      setExpiry,
      confirmAddCardButtonPressed,
    } = AddCardViewController( {navigation} );

    const { userData } = useContext(UserContext);

  return (
    <SafeAreaView style = {localStyles.container}>

      <View style = {localStyles.detailsContainer}>
        <CustomTextField 
        placeholder="Card Name" 
        onChangeText={setName}/>

      <CustomTextField 
        placeholder="Number" 
        onChangeText={setNumber}/>

      <CustomTextField 
        placeholder="MM/YY" 
        onChangeText={setExpiry}/>

          <View style = {localStyles.buttonContainer}>
        <CustomLongButton
        title="Add Card"
        onPress={confirmAddCardButtonPressed}
        />
          </View>

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
