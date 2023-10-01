import React, { useContext } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import MapView, { Marker }from 'react-native-maps';
import { UserContext } from '../model/User';

export default MapScreen = () => {

  const { allStations } = useContext(UserContext);

  return (
      <View>
        <MapView style={styles.map} >
          {allStations.map(station => (
            <Marker
              key={station.id}
              coordinate={{latitude: station.latitude, longitude: station.longitude}}
            />
          ))}
        </MapView>
      </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    width: '100%',
    height: '100%',
  },
});