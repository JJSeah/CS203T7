import React, { useContext, useState } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import MapView, { Callout, Marker }from 'react-native-maps';
import { UserContext } from '../model/User';

export default MapScreen = () => {

  const { allStations } = useContext(UserContext);
  const [selectedStation, setSelectedStation] = useState(null);

  const handleMarkerPress = (station) => {
    setSelectedStation(station);
  };

  const renderStationDetails = (station) => {
    return (
      <View style ={styles.stationDetails}>
        <Text>Name: {station.name}</Text>
        <Text>Address: {station.address}</Text>
        <Text>Availability: {station.avail ? 'available' : 'occupied'}</Text>
      </View>
    );
  };

  const fakeData = [
    {
      station: {
        id: 1,
        name: "bumble",
        address: "toapayoh",
        latitude: 1.3343,
        longitude: 103.8563,
        avail: true,
      },
    },
      {
        station: {
        id: 2,
        name: "bee",
        address: "orchard",
        latitude: 1.304833,
        longitude: 103.831833,
        avail: true,
      },
    }
  ];
  
  return (
      // <View>
      //   <MapView style={styles.map} >
      //     {allStations.map(station => (
      //       <Marker
      //         key={station.id}
      //         coordinate={{latitude: station.latitude, longitude: station.longitude}}
      //       />
      //     ))}
      //   </MapView>
      // </View>

      <View>
        <MapView style={styles.map}>
          {fakeData.map((station) => (
              
            <Marker
              key={station.station.id}
              coordinate={{latitude: station.station.latitude, longitude: station.station.longitude, }}
              onPress={() => handleMarkerPress(station)}
              >
                <Callout>
                  <View> 
                    <Text>{station.station.name}</Text>
                    <Text>{station.station.address}</Text>
                    <Text>{station.station.avail ? 'available' : 'occupied'}</Text>
                  </View>
                </Callout>
              </Marker> 
            
            )
            )} 
          </MapView>

          {selectedStation && renderStationDetails(selectedStation)}
      </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    width: '100%',
    height: '100%',
  },
  stationDetails: {
    padding: 10
  }
});