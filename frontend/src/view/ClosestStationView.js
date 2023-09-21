import React, { useContext, useEffect } from 'react';
import { Button, Text, View, StyleSheet, ActivityIndicator} from 'react-native';
import AutomateBookingScreenViewController from '../viewController/AutomateBookingScreenViewController';
import { UserContext } from '../model/User';
import MapView, { Marker } from 'react-native-maps';
import UpcomingAppointmentView from './UpcomingAppointmentView';

export default ClosestStationView = () => {

    const { closestStation, upcomingAppointment } = useContext(UserContext);

    return (
        <View>
            {/* <MapView style={styles.map}>
                <Marker 
                    coordinate={{latitude: closestStation.latitude, longitude: closestStation.longitude}}
                />
            </MapView> */}
            <Text>The closest station is {closestStation.name}</Text>
            <Text>The closest station id is {closestStation.id}</Text>
            <Text>The address is {closestStation.address}</Text>
            <Text>The latitude is {closestStation.latitude}</Text>
            <Text>The longitude is {closestStation.longitude}</Text>

            <View>
                {
                    (upcomingAppointment === null) ?  
                        <ActivityIndicator/> : 
                        <UpcomingAppointmentView/>
                }
            </View>
        </View>
    );
}

const styles = StyleSheet.create({


    map: {
      width: '100%',
      height: '100%'
    },
  });