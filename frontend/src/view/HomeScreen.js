import React, { useContext } from 'react';
import { Button, Text, View, ScrollView, TextBase } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import HomeScreenViewController from '../viewController/HomeScreenViewController';
import { UserContext } from '../model/User';
import CarSwipeView from '../components/CarSwipeView';
import { useFocusEffect } from '@react-navigation/native';
import { CarRepository } from '../model/CarRepository';
import { SafeAreaView } from 'react-native-safe-area-context';

export default HomeScreen = ( { navigation } ) => {

  const { addCarButtonPressed, manualBookingButtonPressed, automateBookingButtonPressed} = HomeScreenViewController( { navigation} );

  const { userData, userCars, logOut, currentCar } = useContext(UserContext); 

  const { loadCarsData } = CarRepository();

  useFocusEffect(
    React.useCallback(() => {      
      loadCarsData();
    }, [])
  )

  return (
    <SafeAreaView>
    <View>

      <Text>Welcome {userData.username}</Text>

      <Text>Your email is {userData.email}</Text>

      <Text>Your id is {userData.id}</Text>

      <Text>Your firstName is {userData.firstName}</Text>

      <Text>Your lastName is {userData.lastName}</Text>

      {
        userCars.map(car => (
          <View key={car.id}>
            <Text>{car.nickname}</Text>
          </View>
        ))
      }


      <CustomLongButton
        title="Add car"
        onPress={addCarButtonPressed}
        />

      <CustomLongButton
        title="Manual booking"
        onPress={manualBookingButtonPressed}
        />

      <CustomLongButton
        title="Automate booking"
        onPress={automateBookingButtonPressed}
        />

      <CustomLongButton
        title="Log out user" 
        onPress={logOut}
        />
        
      </View>
    </SafeAreaView>
  );
}