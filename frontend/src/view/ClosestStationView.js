import React, { useContext, useEffect } from 'react';
import { Button, Text, View, StyleSheet, ActivityIndicator, SafeAreaView } from 'react-native';
import AutomateBookingScreenViewController from '../viewController/AutomateBookingScreenViewController';
import { UserContext } from '../model/User';
import MapView, { Marker } from 'react-native-maps';
import UpcomingAppointmentView from './UpcomingAppointmentView';

export default ClosestStationView = () => {

    const { closestStation, upcomingAppointment, currentCar } = useContext(UserContext);

    return (
        <View style={localStyles.container}>
            
            <View
                style={localStyles.carInfoContainer}
            >
                <Text>Looking for the nearest charging station for</Text>
                <Text>{currentCar.nickname}</Text>
            </View>
        

            <View
                style={localStyles.stationInfoContainer} 
            >
                <Text>{closestStation.name}</Text>
                <Text> {closestStation.address}</Text>

                <Text>Station_ID:{closestStation.id}</Text>

                <Text>The latitude is {closestStation.latitude}</Text>
                <Text>The longitude is {closestStation.longitude}</Text>
            </View>


            <View
                style={localStyles.mapContainer} 
            >

                <MapView style={localStyles.map}>
                    <Marker
                        coordinate={{ latitude: closestStation.latitude, longitude: closestStation.longitude }}
                    />
                </MapView>

            </View>


            <View
                style={localStyles.appointmentContainer}
            >
                {
                        (upcomingAppointment === null) ?
                            <ActivityIndicator /> :
                            <UpcomingAppointmentView/>
                    }
            </View>

        </View>
    );
}

const localStyles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'stretch',
    },
    carInfoContainer: {
        flex: 1,
        alignItems: 'center',
        // backgroundColor: 'red',
        justifyContent: 'center'
    },
    stationInfoContainer: {
        flex: 3,
        alignItems: 'center',
        // backgroundColor: 'blue',
        justifyContent: 'center'
    },
    mapContainer: {
        flex: 4,
        padding: 20,
    },
    appointmentContainer: {
        flex: 4,
        alignItems: 'center',
        // backgroundColor: 'green',
        justifyContent: 'center'
    },
    map: {
      width: '100%',
      height: '100%'
    },
  });