import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";
import { BASE_URL } from "../constants/Config";
import { useRoute } from "@react-navigation/native";
import axios from "axios";

export default SelectStationScreenViewController = ( { navigation }, stations ) => {

  const { currentCar, userToken, userId, getAppointment } = useContext(UserContext);

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


  const confirmBookingButtonPressed = async (dateString, startTimeString, endTimeString) => {
    let url = `${BASE_URL}/api/appointment`

    axios.post(url, {
        "startTime": startTimeString,
        "endTime": endTimeString,
        "date": dateString,
        "station" : {
          "id": selectedStation.id
        }, 
        "charger": {
          "id": finalCharger.chargerId
        },
        "user": {
          "id": userId,
          "role": "ROLE_ADMIN"
        }
      },
      {
          headers: {
              'Authorization': `Bearer ${userToken}`
          }
      }
    )
    .then((res)=> {
      getAppointment();
      navigation.navigate("HomeNavigator");
    })
    .catch((e) => {
      console.log(`Error making manual appointment ${e}`)
    }
    )


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
