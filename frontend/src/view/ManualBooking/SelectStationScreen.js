import React, { useContext, useEffect, useState, useRef } from "react";
import {
  View,
  SafeAreaView,
  StyleSheet,
  Text,
  ScrollView,
  TouchableOpacity,
} from "react-native";
import { useRoute } from "@react-navigation/native";
import MapView, { Callout, Marker } from "react-native-maps";
import SelectStationScreenViewController from "../../viewController/SelectStationScreenViewController";
import CustomLongButton from "../../components/CustomLongButton";
import { Ionicons } from "@expo/vector-icons";
import { MaterialCommunityIcons } from "@expo/vector-icons";

export default SelectStationScreen = ({ navigation }) => {
  const route = useRoute();
  const stations = route.params?.stations;
  const currentCar = route.params?.currentCar;
  const timings = route.params?.timings;

  const mapViewRef = useRef(null);

  const {
    selectedChargers,
    markerPressed,
    selectedStation,
    selectFinalCharger,
    finalCharger,
    confirmBookingButtonPressed,
  } = SelectStationScreenViewController({ navigation }, stations);

  return (
    <SafeAreaView style={{ flex: 1, backgroundColor: "#141414"}}>
      <View style={localStyles.topContainer}>
        <View style={localStyles.titleContainer}>
          <View
            style={{
              justifyContent: "center",
              flexDirection: "column",
              alignItems: "center",
              alignContent: "stretch",
              flex: 1,
            }}
          >
            <View style={{ flex: 1 }}>
              <View style={localStyles.rectangle} />
            </View>

            <View
              style={{
                flex: 9,
                alignItems: "center",
                flexDirection: "row",
                paddingHorizontal: 10,
                paddingTop: 10
              }}
            >
              <Text
                style={{
                  textDecorationLine: "underline",
                  fontSize: 22,
                  color: !selectedStation ? "teal" : "red",
                  fontWeight: "bold",
                }}
              >
                {currentCar.nickname}
              </Text>
              <Text
                style={{
                  textDecorationLine: "underline",
                  fontSize: 22,
                  color: !selectedStation ? "teal" : "red",
                  fontWeight: "bold",
                }}
              >
                {" "}
                |{" "}
              </Text>
              <Text
                style={{
                  textDecorationLine: "underline",
                  fontSize: 22,
                  color: !selectedStation ? "teal" : "red",
                  fontWeight: "bold",
                }}
              >
                {timings.dateString}
              </Text>
            </View>
          </View>

          <View
            style={{
              flexDirection: "row",
              justifyContent: "space-between",
              paddingHorizontal: 50,
              paddingBottom: 10,
              flex: 1,
            }}
          >
            <View style={{ alignItems: "center", justifyContent: "center" }}>
              <Text style={localStyles.label}>Start time</Text>
              <Text style={localStyles.text}>{timings.startTimeString}</Text>
            </View>

            <View style={{ alignItems: "center", justifyContent: "center" }}>
              <Text style={localStyles.label}>End time</Text>
              <Text style={localStyles.text}>{timings.endTimeString}</Text>
            </View>
          </View>
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
                pinColor={
                  selectedStation === null
                    ? "teal"
                    : selectedStation.latitude === station.latitude &&
                      selectedStation.longitude === station.longitude
                    ? "red"
                    : "teal"
                }
                onPress={() => {
                  markerPressed(station.latitude, station.longitude);
                  if (mapViewRef.current) {
                    mapViewRef.current.animateToRegion({
                      latitude: station.latitude,
                      longitude: station.longitude,
                      latitudeDelta: 0.03,
                      longitudeDelta: 0.03,
                    });
                  }
                }}
              ></Marker>
            ))}
          </MapView>
        </View>

        <View style={localStyles.selectStationContainer}>
          {selectedChargers === null ? (
            <View
              style={{
                flex: 1,
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <Text style={localStyles.text}>
                Click on one of the stations to find available chargers
              </Text>
            </View>
          ) : (
            <View>
              <View style={{ padding: 10 }}>
                <Text style={localStyles.text}>
                  Station: {selectedStation.name}
                </Text>
                <Text style={localStyles.text}>
                  Address: {selectedStation.address}
                </Text>
              </View>

              <ScrollView>
                <View>
                  {selectedChargers.map((charger) => {
                    return (
                      <TouchableOpacity
                        key={charger.chargerId}
                        onPress={() => {
                          selectFinalCharger(charger);
                        }}
                      >
                        <View>
                          <View style={localStyles.chargerContainer}>
                            <View style={{ flexDirection: "row" }}>
                              <View style={{ paddingRight: 10 }}>
                                <MaterialCommunityIcons
                                  name="ev-station"
                                  size={35}
                                  color="white"
                                />
                              </View>

                              <View>
                                <Text style={localStyles.text}>
                                  Charger ID: {charger.chargerId}
                                </Text>
                                <Text style={localStyles.text}>
                                  Charging Rate: {charger.chargingRate} kW/h
                                </Text>
                              </View>
                            </View>

                            <View style={localStyles.iconContainer}>
                              {charger.chargerId === finalCharger.chargerId ? (
                                <Ionicons
                                  name="checkmark-circle"
                                  size={24}
                                  color="green"
                                />
                              ) : (
                                <></>
                              )}
                            </View>
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
            confirmBookingButtonPressed(
              timings.dateString,
              timings.startTimeString,
              timings.endTimeString
            );
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
    flex: 5,
    // backgroundColor:'red'
  },
  mapContainer: {
    flex: 10,
    paddingHorizontal: 10,
    paddingBottom: 8,
  },
  selectStationContainer: {
    flex: 10,
  },
  selectChargerContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  iconContainer: {
    justifyContent: "center",
  },
  chargerContainer: {
    borderWidth: 1,
    borderColor: "white",
    borderRadius: 10,
    padding: 20,
    margin: 8,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  text: {
    color: "white",
  },
  label: {
    color: "white",
    fontSize: 18,
    fontWeight: "bold",
  },
  rectangle: {
    width: 200,
    height: 3,
    backgroundColor: "white",
    borderRadius: 25,
  },
  underlined: {
    textDecorationLine: "underline",
    fontSize: 25,
    color: "teal",
    fontWeight: "bold",
  },
});
