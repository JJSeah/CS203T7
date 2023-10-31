import React, { useContext, useEffect, useState, useRef } from "react";
import { View, SafeAreaView, StyleSheet, Text, ScrollView, TouchableOpacity} from "react-native";
import { useRoute } from "@react-navigation/native";
import MapView, { Callout, Marker } from "react-native-maps";
import SelectStationScreenViewController from "../../viewController/SelectStationScreenViewController";
import CustomLongButton from "../../components/CustomLongButton";
import { Ionicons } from "@expo/vector-icons";


export default SelectStationScreen = ({ navigation }) => {
  const route = useRoute();
  const stations = route.params?.stations;
  const currentCar = route.params?.currentCar;
  const timings = route.params?.timings;

  const mapViewRef = useRef(null);

  const { selectedChargers, markerPressed, selectedStation, selectFinalCharger, finalCharger, confirmBookingButtonPressed} =
    SelectStationScreenViewController({ navigation }, stations);

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <View style={localStyles.topContainer}>
        <View style={localStyles.titleContainer}>
          <Text>Click on one of the stations to find available chargers</Text>
          <Text>For {currentCar.nickname}</Text>
          <Text>For {timings.dateString}</Text>
          <Text>For {timings.startTimeString}</Text>
          <Text>For {timings.endTimeString}</Text>
        </View>

        <View style={localStyles.mapContainer}>
          <MapView style={localStyles.map} ref={mapViewRef}>
            {stations.map((station) => (
              <Marker
                key={station.chargerId}
                coordinate={{
                  latitude: station.latitude,
                  longitude: station.longitude,
                }}
                onPress={() => {
                  markerPressed(station.latitude, station.longitude)
                  if (mapViewRef.current) {
                    mapViewRef.current.animateToRegion({
                      latitude: station.latitude,
                      longitude: station.longitude,
                      latitudeDelta: 0.04,
                      longitudeDelta: 0.04,
                    });
                    }
                  }
                }
              ></Marker>
            ))}
          </MapView>
        </View>

        <View style={localStyles.selectStationContainer}>
          {selectedChargers === null ? (
            <Text>Please select a charger</Text>
          ) : (
            <View>
              <View>
                <Text>Station: {selectedStation.name}</Text>
                <Text>Address: {selectedStation.address}</Text>
              </View>

              <ScrollView>
                <View>
                  {selectedChargers.map((charger) => {
                    return (
                      <TouchableOpacity 
                        key={charger.chargerId}
                        onPress={() => { selectFinalCharger(charger) }}
                      >
                        <View 
                        style={localStyles.selectChargerContainer}
                        >
                          <View>
                            <Text>Charger ID: {charger.chargerId}</Text>
                            <Text>Charging Rate: {charger.chargingRate}</Text>
                          </View>

                          <View style={localStyles.iconContainer}>
                            {charger.chargerId === finalCharger.chargerId ?
                              <Ionicons name="checkmark-circle" size={24} color="green"/>
                            :
                            <></>
                            }
                          </View>
                        </View>
                      </TouchableOpacity>
                    );
                  })}
                </View>
              </ScrollView>
            </View>
          )}
        </View>
      </View>

      <View style={localStyles.bottomContainer}>
        <CustomLongButton
          title="Confirm booking"
          disabled={selectedStation === null}
          onPress={() => {
            confirmBookingButtonPressed(timings.dateString, timings.startTimeString, timings.endTimeString)
          }}
        />
      </View>
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  map: {
    width: "100%",
    height: "100%",
  },
  topContainer: {
    flex: 9,
  },
  bottomContainer: {
    flex: 1,
  },
  titleContainer: {
    flex: 4,
  },
  mapContainer: {
    flex: 10,
  },
  selectStationContainer: {
    flex: 10,
  },
  selectChargerContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between'
  },
  iconContainer: {
    justifyContent:'center',

  }
});
