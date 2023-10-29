import { useContext, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { BASE_URL } from "../constants/Config";
import { Alert } from "react-native";

export default ActivityViewController = ({ navigation }) => {
  // const [ isReady, setIsReady ] = useState(false);
  const { userToken, getAllAppointments } = useContext(UserContext);
  //test
  const historyButtonPressed = () => {
    console.log("history button pressed");
    navigation.navigate("HistoryScreen");
  };

  const scanQRButtonPressed = (appt) => {
    console.log("scanQRCode button pressed");
    // console.log(appt.startTime)

    const apptStart = new Date(`${appt.date}`);
    const hours = parseInt(appt.startTime.slice(0, 2));
    const minutes = parseInt(appt.startTime.slice(3, 5));
    const seconds = parseInt(appt.startTime.slice(6));
    apptStart.setHours(hours, minutes, seconds);

    
    const date = new Date();
    const now_utc = Date.UTC(date.getUTCFullYear(), date.getUTCMonth(),
    date.getUTCDate(), date.getUTCHours(),
    date.getUTCMinutes(), date.getUTCSeconds());
    
    const currentTime = new Date(now_utc)

    if (currentTime <= apptStart) {
      Alert.alert("It is not time yet", "Cannot charge now", [
        {
          text: "Got it",
          onPress: () => {},
        },
      ]);
    } else {
      navigation.navigate("UpcomingAppointmentView", appt);
    }
  };

  const cancelButtonPressed = (appt) => {
    Alert.alert(
      "Cancle booking",
      `Are you sure you want to cancle the booking on ${appt.date}?`,
      [
        {
          text: "Cancle",
          onPress: () => {},
          style: "cancle",
        },
        {
          text: "Confirm",
          onPress: () => {
            cancelAppointment(appt);
          },
          style: "destructive",
        },
      ]
    );
  };

  const cancelAppointment = async (appt) => {
    let url = `${BASE_URL}/api/appointment/cancel/${appt.id}`;
    axios
      .put(url, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        getAllAppointments();
      })
      .catch((e) => {
        console.log(`error cancelling appointment exception: ${e}`);
      });
  };

  return {
    historyButtonPressed,
    scanQRButtonPressed,
    cancelButtonPressed,
  };
};
