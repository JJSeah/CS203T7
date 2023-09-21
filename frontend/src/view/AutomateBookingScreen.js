import React, { useContext, useEffect, useState} from 'react';
import { Text, View, ActivityIndicator, StyleSheet } from 'react-native';
import * as Location from 'expo-location'
import { UserContext } from '../model/User';
import { SafeAreaView } from 'react-native-safe-area-context';
import CustomLongButton from '../components/CustomLongButton';
import AutomateBookingScreenViewController from '../viewController/AutomateBookingScreenViewController';
import ClosestStationView from './ClosestStationView';
import GrantLocationScreen from './GrantLocationScreen';
import UpcomingAppointmentView from './UpcomingAppointmentView';
import ReminderToAddCarScreen from './ReminderToAddCarScreen';

export default AutomateBookingScreen = ( { navigation } ) => {

  const { userCoordinates, closestStation, upcomingAppointment, userCars } = useContext(UserContext);

  const { findClosestStation } = AutomateBookingScreenViewController( { navigation } );

  useEffect(() => {

    if (userCoordinates === null || userCars.length === 0) {
      return;
    } 

    findClosestStation(userCoordinates.latitude, userCoordinates.longitude);

}, []);

  return (
        (userCoordinates === null) ?

        <GrantLocationScreen 
          styles={styles.grantLocationScreen}
        />
      
        : 
        <SafeAreaView>

            <View>

            </View>

            <View>
                
                  {
                    (userCars.length === 0) ?
                    <ReminderToAddCarScreen/> :

                    (closestStation !== null) ?                    
                      <ClosestStationView/>            
                    : 
                    (<ActivityIndicator/>)
                }
            </View>

            <View>
              <CustomLongButton
                title="Confirm"
                onPress={() => { navigation.pop() }}
              />

              <CustomLongButton
                title="Cancle"
                onPress={() => { navigation.pop() }}
              />
            </View> 

      </SafeAreaView>
  );
}

const styles = StyleSheet.create({
});
