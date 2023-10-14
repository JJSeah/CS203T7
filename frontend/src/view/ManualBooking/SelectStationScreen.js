import React, { useContext, useEffect, useState } from "react";
import { View, SafeAreaView, StyleSheet, Text, ScrollView, TouchableOpacity} from "react-native";
import { useRoute } from "@react-navigation/native";
import MapView, { Callout, Marker } from "react-native-maps";
import SelectStationScreenViewController from "../../viewController/SelectStationScreenViewController";
import CustomLongButton from "../../components/CustomLongButton";

export default SelectStationScreen = ({ navigation }) => {
  const route = useRoute();
  const stations = route.params?.stations;

  const { selectedChargers, markerPressed, selectedStation, selectFinalCharger, finalCharger } =
    SelectStationScreenViewController({ navigation }, stations);

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <View style={localStyles.topContainer}>
        <View style={localStyles.titleContainer}>
          <Text>Click on one of the stations to find available chargers</Text>
        </View>

        <View style={localStyles.mapContainer}>
          <MapView style={localStyles.map}>
            {stations.map((station) => (
              <Marker
                key={station.chargerId}
                coordinate={{
                  latitude: station.latitude,
                  longitude: station.longitude,
                }}
                onPress={() =>
                  markerPressed(station.latitude, station.longitude)
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
                        onPress={() => { selectFinalCharger(charger) }}
                      >
                        <View style={localStyles.selectChargerContainer}>
                          <View key={charger.chargerId}>
                            <Text>{charger.chargerId}</Text>
                            <Text>{charger.chargingRate}</Text>
                          </View>

                          <View>
                            {charger.chargerId === finalCharger.chargerId ?
                            (<Text>
                              Selected this
                            </Text>)
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
          onPress={() => {
            console.log("haha");
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
    flex: 1,
  },
  mapContainer: {
    flex: 10,
  },
  selectStationContainer: {
    flex: 10,
  },
  selectChargerContainer: {
    alignContent: 'stretch'
  }
});
