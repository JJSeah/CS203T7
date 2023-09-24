import React, { useContext } from 'react';
import { Button, Text, View, ScrollView, TextBase } from 'react-native';
import CustomLongButton from '../components/CustomLongButton';
import HomeScreenViewController from '../viewController/HomeScreenViewController';
import { UserContext } from '../model/User';
import CarSwipeView from '../components/SingleCarSwiperView'
import { useFocusEffect } from '@react-navigation/native';
import { CarRepository } from '../model/CarRepository';
import { SafeAreaView } from 'react-native-safe-area-context';
import { styles } from '../components/Design';

export default HomeScreen = ( { navigation } ) => {

  const { addCarButtonPressed, manualBookingButtonPressed, automateBookingButtonPressed} = HomeScreenViewController( { navigation} );

  const { userData, userCars, logOut } = useContext(UserContext); 

  const { loadCarsData } = CarRepository();

  useFocusEffect(
    React.useCallback(() => {    
      loadCarsData();
    }, [])
  )

  return (

    
    <SafeAreaView>
      <View>

        <View style={{backgroundColor: 'blue'}}>

          <Text>Welcome {userData.username}</Text>

          <Text>Your email is {userData.email}</Text>

          <Text>Your id is {userData.id}</Text>

          <Text>Your firstName is {userData.firstName}</Text>

          <Text>Your lastName is {userData.lastName}</Text>

        </View>


        <View style={{backgroundColor: 'red'}}>

          {
            userCars.map(car => (
              <View key={car.id}>
                <Text>{car.nickname}</Text>
        
              </View>
            ))
          }

        </View>


        <View
          style={{ display: 'flex',  flexDirection:'column', backgroundColor: 'green', height: 400}}
        >


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

      </View>
    </SafeAreaView>
  );
}