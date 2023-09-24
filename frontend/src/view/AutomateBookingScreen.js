import React, { useContext, useEffect, useState } from 'react';
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
// import { styles } from "../components/Design"
import MapView, { Marker } from 'react-native-maps';


export default AutomateBookingScreen = ({ navigation }) => {

  const { userCoordinates, closestStation, upcomingAppointment, userCars } = useContext(UserContext);

  const { findClosestStation } = AutomateBookingScreenViewController({ navigation });

  useEffect(() => {

    if (userCoordinates === null || userCars.length === 0) {
      return;
    }

    findClosestStation(userCoordinates.latitude, userCoordinates.longitude);

  }, []);

  return (
    <SafeAreaView>
      { (userCoordinates === null) ?

        <View>
          <GrantLocationScreen
          />
        </View>

      :
      
        <View>
          {
            (userCars.length === 0) ?
              <ReminderToAddCarScreen /> :

              (closestStation !== null) ?

                <View> 
                  <ClosestStationView />

                  <View style={styles.buttonsContainer}>

                      <View style={styles.buttons}>
                        <CustomLongButton
                          title="Confirm"
                          onPress={() => { navigation.pop()} }
                        />
                      </View>

                      <View style={styles.buttons}>
                        <CustomLongButton
                          title="Cancle"
                          onPress={() => { navigation.pop() }}
                        />
                      </View>

                    </View>
                </View>
                :
                (<ActivityIndicator />)
          }


        </View>
      }

      </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  "buttonsContainer": {
    flexDirection: 'row',
  },
  "buttons": {
    flex:1
  },
  "secondContainer": {
    margin:25,
    marginTop:0,
    marginBottom:0
  },

  "thirdContainer": {
    backgroundColor: 'white', 
    width:20,
    borderBottomLeftRadius:50, 
    borderBottomRightRadius:50, 
    padding:5
  }


})