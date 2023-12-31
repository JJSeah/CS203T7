import React, { useContext, useEffect, useRef } from "react";
import { Text, View, StyleSheet } from "react-native";
import MapView, { Callout, Marker } from "react-native-maps";
import { UserContext } from "../model/User";
import MapViewController from "../viewController/MapViewController";
import FontLoader from "../constants/FontLoader";
import * as SplashScreen from "expo-splash-screen";

SplashScreen.preventAutoHideAsync();

const darkMapStyle = [
  {
    featureType: "all",
    elementType: "geometry",
    stylers: [
      {
        color: "#000000", // Background color (black)
      },
    ],
  },
  {
    featureType: "all",
    elementType: "labels.text.fill",
    stylers: [
      {
        color: "#FFFFFF", // Label text color (white)
      },
    ],
  },
  // Add more styles for dark mode here...
];


export default MapScreen = ({ navigation }) => {
  const { isReady, setIsReady, selectedStation, setSelectedStation } =
    MapViewController({ navigation });
  const { allStations } = useContext(UserContext);

  const mapViewRef = useRef(null);

  useEffect(() => {
    const loadFonts = async () => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    };
    loadFonts();
  }, []);

  const handleMarkerPress = (station) => {
    setSelectedStation(station);

    if (mapViewRef.current) {
      mapViewRef.current.animateToRegion({
        latitude: station.latitude,
        longitude: station.longitude,
        latitudeDelta: 0.04,
        longitudeDelta: 0.04,
      });
    }
  };

  return (
    <View>
      <MapView style={styles.map} ref={mapViewRef} customMap={darkMapStyle}>
        {allStations.map((station) => (
          <Marker
            key={station.id}
            coordinate={{
              latitude: station.latitude,
              longitude: station.longitude,
            }}
            onPress={() => handleMarkerPress(station)}
          >
            <Callout>
              <View>
                <Text style={styles.stationName}>{station.name}</Text>
                <Text style={styles.address}>{station.address}</Text>
              </View>
            </Callout>
          </Marker>
        ))}
      </MapView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    width: "100%",
    height: "100%",
  },
  stationDetails: {
    padding: 10,
    margin: 50,
  },
  address: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 14,
    color: "gray",
    fontWeight: "bold",
  },
  stationName: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 16,
    fontWeight: "bold",
  },
});
