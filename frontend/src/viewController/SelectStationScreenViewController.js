import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";
import { BASE_URL } from "../constants/Config";
import { useRoute } from "@react-navigation/native";
import axios from "axios";

export default SelectStationScreenViewController = ( { navigation }, stations ) => {

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

  const findAvailableStationsButtonPressed = async (startTime, endTime) => {
    const dateString =
      startTime.getFullYear() +
      "-" +
      ("0" + (startTime.getMonth() + 1)).slice(-2) +
      "-" +
      ("0" + startTime.getDate()).slice(-2);

    const startTimeString = startTime.toLocaleTimeString("en-us", {
      hour12: false,
    });

    const endTimeString = endTime.toLocaleTimeString("en-us", {
      hour12: false,
    });

    let url = `${BASE_URL}/api/appointment/available`;

    axios
      .post(
        url,
        {
          startTime: startTimeString,
          endTime: endTimeString,
          date: dateString,
        }
        // {
        //   headers: { Authorization: `Bearer ${userToken}` },
        // }
      )
      .then((res) => {
        let data = res.data;
        data = data.filter((station) => station.address !== null);
        navigation.navigate("SelectStationScreen", { stations: data });
      })
      .catch((e) => {
        console.log(
          `Error finding available stations within ${startTimeString} and ${endTimeString} on ${dateString} ${e}`
        );
      });
  };

  return {
    findAvailableStationsButtonPressed,
    selectedChargers,
    markerPressed,
    selectedStation,
    selectFinalCharger,
    finalCharger
  };
};
