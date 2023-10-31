import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { useRoute } from "@react-navigation/native";
import { BASE_URL } from "../constants/Config";

export default ChargingCarViewController = ({ navigation }) => {
  const { userToken, loadAllAppointments } = useContext(UserContext);
  const [ buttonState, setButtonState ] = useState("STOP");
  const [ chargingCar, setChargingCar ] = useState(null)
  //test
  const completeChargingButtonPressed = (appt) => {
    navigation.pop()
    navigation.navigate("MakePaymentScreen", appt);
  };

  const checkCarBatteryStatus = async (carId) => {
    console.log(`Checking car with id ${carId}'s battery percentage`);

    let url = `${BASE_URL}/api/car/${carId}`;

    axios.get(url, {
      headers: {
        Authorization: `Bearer ${userToken}`,
      },
    }).then((res) => {
        let data = res.data
        setChargingCar(data)
    }).catch(e => {
        console.log(`Error getting car battery status ${e}`)
    });
  };


  return {
    buttonState,
    setButtonState,
    checkCarBatteryStatus,
    completeChargingButtonPressed,
    chargingCar
  };
};
