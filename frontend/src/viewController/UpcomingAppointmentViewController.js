import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import * as SecureStore from "expo-secure-store"
import axios from "axios";
import { BASE_URL } from '../constants/Config';
import { Alert } from "react-native"

export default UpcomingAppointmentViewController = ( { navigation } ) => {
    const {userId, userToken, getAllAppointments} = useContext(UserContext)

    const scanQrCodeCorrectCharger = async(appt) => {
      const chargerId = appt.charger.id;
      let url = `${BASE_URL}/api/QrCode/checkComingAppt/charger${chargerId}/${userId}`

      axios.get(url, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        console.log(`There is an appointment for `)
        startAppointment(appt);
      })
      .catch((e) => {
        console.log(`Scan QR code correct charger exception: ${e}`);
      });
    }

    const scanQrCodeIncorrectCharger = async(appt) => {
      const correctChargerId = appt.charger.id;
      const incorrectchargerId = correctChargerId + 1;

      let url = `${BASE_URL}/api/QrCode/checkComingAppt/charger${incorrectchargerId}/${userId}`

      axios.get(url, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        Alert.alert(
          "Incorrect charger",
          `Please go to charger ${correctChargerId}`,[
            {
              text: "Got it",
              onPress: () => {}
            }
          ]
        )
      })
      .catch((e) => {
        console.log(`QR code error incorrect charger exception: ${e}`);
      });
    }
    

    const startAppointment = (appt) => {
        axios
          .get(`${BASE_URL}/api/appointment/start/${appt.id}`, {
            headers: {
              Authorization: `Bearer ${userToken}`,
            },
          })
          .then((res) => {
            let data = res.data;
            getAllAppointments();
            navigation.pop()
            navigation.navigate("ChargingCarView", appt);
          })
          .catch((e) => {
            console.log(`error starting appointment: ${e}`);
          });
      };
    

    return {
        startAppointment,
        scanQrCodeCorrectCharger,
        scanQrCodeIncorrectCharger
    };
}