import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import * as SecureStore from "expo-secure-store"
import axios from "axios";
import { BASE_URL } from '../constants/Config';



export default UpcomingAppointmentViewController = ( { navigation } ) => {
    // const [ isReady, setIsReady ] = useState(false);     
    const {userId, userToken, getAllAppointments} = useContext(UserContext)
    //test 
    const chargingProgressButtonPressed = () => {
        console.log("check car charging progress");
        navigation.navigate('ChargingCarView');
    }

    const scanQrCodeCorrectCharger = async(appt) => {
      const chargerId = appt.charger.id;
      let url = `${BASE_URL}/api/QrCode/checkComingAppt/charger${chargerId}/${userId}`
      axios.get(`${BASE_URL}/api/appointment/checkComingAppt/${userId}`, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        let data = res.data;
      })
      .catch((e) => {
        console.log(`QR code error catch exception: ${e}`);
      });
    }

    const scanQrCodeIncorrectCharger = async(appt) => {
      const incorrectchargerId = appt.charger.id + 1;

      let url = `${BASE_URL}/api/QrCode/checkComingAppt/charger${incorrectchargerId}/${userId}`

      console.log(url)
      // axios.get(`${BASE_URL}/api/appointment/checkComingAppt/${userId}`, {
      //   headers: {
      //     Authorization: `Bearer ${userToken}`,
      //   },
      // })
      // .then((res) => {
      //   let data = res.data;
      // })
      // .catch((e) => {
      //   console.log(`QR code error catch exception: ${e}`);
      // });
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
        chargingProgressButtonPressed,
        startAppointment,
        scanQrCodeCorrectCharger,
        scanQrCodeIncorrectCharger
    };
}