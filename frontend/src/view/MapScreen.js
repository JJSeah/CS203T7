import React from 'react';
import { Text, View, StyleSheet } from 'react-native';
import MapView from 'react-native-maps';
import { MapProvider } from '../model/MapRepository';

export default MapScreen = () => {
  return (
    <MapProvider>
      <View>
        <MapView
          style={styles.map}
          />
      </View>
    </MapProvider>
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