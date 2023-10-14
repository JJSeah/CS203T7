import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";
import { BASE_URL } from "../constants/Config";
import { useRoute } from "@react-navigation/native";
import axios from "axios";

export default SelectStationScreenViewController = ( { navigation }, stations ) => {

  const { currentCar } = useContext(UserContext);

  const [ selectedChargers, setSelectedChargers ] = useState(null);
  const [ selectedStation, setSelectedStation ] = useState(null);

  const [ finalCharger, setFinalCharger ] = useState(null);

  const markerPressed = (latitude, longitude) => {
    const chargers = stations.filter( (station) =>
        station.latitude === latitude && station.longitude === longitude
    );
    
    setSelectedChargers(chargers)
    setSelectedStation(chargers[0])

    if (chargers.length > 0) {
      setFinalCharger(chargers[0])
    }
  };

  const selectFinalCharger = (charger) => {
    setFinalCharger(charger)
  }


  const confirmBookingButtonPressed = async () => {
    navigation.navigate("HomeNavigator");
  };

  return {
    confirmBookingButtonPressed,
    selectedChargers,
    markerPressed,
    selectedStation,
    selectFinalCharger,
    finalCharger
  };
};
