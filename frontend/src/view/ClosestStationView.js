import React, { useContext, useEffect } from 'react';
import { Button, Text, View, StyleSheet, ActivityIndicator, SafeAreaView } from 'react-native';
import AutomateBookingScreenViewController from '../viewController/AutomateBookingScreenViewController';
import { UserContext } from '../model/User';
import MapView, { Marker } from 'react-native-maps';
import UpcomingAppointmentView from './UpcomingAppointmentView';
import { styles } from "../components/Design"

export default ClosestStationView = () => {

    const { closestStation, upcomingAppointment } = useContext(UserContext);

    return (
        <View>
            {/* <MapView style={styles.map}>
                <Marker
                    coordinate={{ latitude: closestStation.latitude, longitude: closestStation.longitude }}
                />
            </MapView> */}

            <View style={{ ...styles.boxContainer, borderTopLeftRadius: 50, borderTopRightRadius: 50, margin: 25, marginBottom:5, padding:30 }}>
            <Text style= {{...styles.header, color: 'black', margin:0}}>{closestStation.name}</Text>
            <Text > {closestStation.address}</Text>

            <Text >Station_ID:{closestStation.id}</Text>

            <Text >The latitude is {closestStation.latitude}</Text>
            <Text >The longitude is {closestStation.longitude}</Text>
            </View>

            <View>
                {
                        (upcomingAppointment === null) ?
                            <ActivityIndicator /> :
                            <UpcomingAppointmentView />
                    }
            </View>

        </View>
    );
}

// const styles = StyleSheet.create({


//     map: {
//       width: '100%',
//       height: '100%'
//     },
//   });