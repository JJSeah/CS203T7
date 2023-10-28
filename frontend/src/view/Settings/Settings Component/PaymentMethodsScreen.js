import React, { useContext } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";
import { Ionicons } from "@expo/vector-icons";
import { UserContext } from '../../../model/User';
import CustomLongButton from '../../../components/CustomLongButton';


export default PaymentMethodsScreen = ( {navigation} ) => {

    const { userCards } = useContext(UserContext);
    const { 
      addCardButtonPressed, } = SettingsScreenViewController( { navigation } );


  return (
    <SafeAreaView style = {localStyles.container}>
      <View style = {localStyles.detailsContainer}>
      <View style={{flex:9}}>
      {userCards === null ? (
        <View>
          <ActivityIndicator />
        </View>
      ) : Array.isArray(userCards) && userCards.length > 0 ? (
        userCards.map((card) => (
          <View key={card.id} style={localStyles.sectionContainer}>
            <View style={localStyles.informationContainer}>
              <CarInformation 
              title="Card Holder's Name" 
              value={card.name}/>
              <CarInformation 
              title="Card Number" 
              value={card.number}/>

              <CarInformation 
              title="Expiry Date" 
              value={card.expiry}/>
            </View>
          </View>
        ))
      ) : (
        <Text>No cards available</Text>
      )}
      </View>

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
  },
  textContainer: {
    color: 'white',
  },

});
