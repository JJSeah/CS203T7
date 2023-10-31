import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";
import axios from "axios";
import { BASE_URL } from "../constants/Config";

export default AutomateBookingScreenViewController = ({ navigation }) => {
  const {
    closestStation,
    setClosestStation,
    setUpcomingAppointmentDetails,
    userToken,
    userId,
    userCoordinates,
    currentCar,
    getAllAppointments,
  } = useContext(UserContext);
  const [bookingData, setBookingData] = useState(null);

  useEffect(() => {
    if (
      closestStation !== null &&
      currentCar !== null &&
      userCoordinates.latitude !== null &&
      userCoordinates.longitude
    ) {
      loadDetailsOfUpcomingAppointment();
    }
  }, [closestStation]);

  const findClosestStation = async (latitude, longitude) => {
    setClosestStation(null);
    setUpcomingAppointmentDetails(null);

    let url = `${BASE_URL}/api/stations/closest`;

    axios.post(url,
        {
          latitude,
          longitude,
        },
        {
          headers: { Authorization: `Bearer ${userToken}` },
        }
      )
      .then((res) => {
        let data = res.data;
        setClosestStation(data);
      })
      .catch((e) => {
        console.log(`Error finding closest station ${e}`);
      });
  };

  const loadDetailsOfUpcomingAppointment = async () => {
    let stationId = closestStation.id;

    let url = `${BASE_URL}/api/stationCheck/${userId}/${currentCar.id}`;

    console.log(stationId);

    axios.post(url,
        {
          latitude: userCoordinates.latitude,
          longitude: userCoordinates.longitude,
          id: stationId,
        },
        {
          headers: { Authorization: `Bearer ${userToken}` },
        }
      )
      .then((res) => {
        let data = res.data;

        let dateString = data.date;
        let chargerId = data.charger.id;
        let startTimeString = data.startTime;
        let endTimeString = data.endTime;
        let stationId = data.stationId;

        console.log("charger available is" + chargerId);

        setBookingData({
          startTime: startTimeString,
          endTime: endTimeString,
          date: dateString,
          station: {
            id: stationId,
          },
          charger: {
            id: chargerId,
          },
          user: {
            id: userId,
            // "role": "ROLE_ADMIN"
          },
          car: {
            id: currentCar.id
          },
          manualAppointment: false,
        });

        setUpcomingAppointmentDetails(data);
      })
      .catch((e) => {
        console.log(`Error loading details of upcoming appointment ${e}`);
      });
  };

  const cancleButtonPressed = () => {
    navigation.pop();
  };

  const confirmButtonPressed = async () => {
    let url = `${BASE_URL}/api/appointment`;

    axios
      .post(url, bookingData, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        getAllAppointments();
        navigation.pop();
      })
      .catch((e) => {
        console.log(`Error making appointment in automate booking screen${e}`);
      });
  };

  return {
    cancleButtonPressed,
    confirmButtonPressed,
    findClosestStation,
  };
};
