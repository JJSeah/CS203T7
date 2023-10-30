import React, { useContext } from 'react';
import { Text, View, StyleSheet, 
  ActivityIndicator, Alert, Image
} from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";
import { Ionicons } from "@expo/vector-icons";
import { UserContext } from '../../../model/User';
import CustomLongButton from '../../../components/CustomLongButton';
import DeleteCardViewController from '../../../viewController/DeleteCardViewController';

import { creditCardIcon } from '../../../../assets/images/index';

export default PaymentMethodsScreen = ( {navigation} ) => {

    const { userCards } = useContext(UserContext);
    const { addCardButtonPressed } = SettingsScreenViewController( { navigation } );
    const { deleteCardButtonPressed } = DeleteCardViewController();

  return (
    <SafeAreaView style = {localStyles.container}>

      <View style = {localStyles.detailsContainer}>
      {userCards === null ? (
        <View>
          <ActivityIndicator />
        </View>
      ) : Array.isArray(userCards) && userCards.length > 0 ? (
        userCards.map((card) => (
          <View key={card.id} style={localStyles.sectionContainer}>
            <View style={localStyles.informationContainer}>

              <View style = {localStyles.iconContainer}>
              <Image
              source = {creditCardIcon}
              style = {localStyles.CardIcon}
              resizeMode = 'cover'
              />
              </View>

              <CarInformation 
              title="Card Holder's Name" 
              value={card.name}/>
              <CarInformation 
              title="Card Number" 
              value={card.number}/>

              <IconButton
              iconName="trashIcon"
              onPress={() => {
                Alert.alert(
                  "Delete car",
                  "Are you sure you want to delete " + card.name,
                  [
                    {
                      text: "Cancel",
                      onPress: () => {},
                    },
                    {
                      text: "Delete",
                      onPress: () => {
                        deleteCardButtonPressed(card.id);
                      },
                      style: "destructive",
                    },
                  ]
                );
              }
            }
            />
            </View>
          </View>
        ))
      ) : (
        <View style = {localStyles.textContainer}>
        <Text style = {localStyles.textStyle}>No cards available</Text>
        </View>
      )}
  </View>

<View style = {localStyles.buttonContainer}>
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
    backgroundColor: "#141414",
  },
  sectionContainer: {

  },
  buttonContainer: {
    flex : 4
  },
  detailsContainer: {
    flex: 6
  },
  informationContainer: {
    borderWidth: 1,
    borderRadius: 10,
    backgroundColor: "#f9f9f9",
    marginBottom: 5,
  },
  iconContainer: {
    justifyContent: 'center',
    alignItems: 'center',
  },

  CardIcon: {
    width: 250,
    height: 175,
    
  },
  textContainer: {
    alignContent:'center',
  },
  textStyle: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 20,
    fontWeight: 'bold',
    color: 'white',
  },
  

});
