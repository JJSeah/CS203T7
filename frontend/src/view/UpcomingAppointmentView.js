import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';
import { styles } from '../components/Design';

export default AppointmentView = () => {

  const { upcomingAppointmentDetails } = useContext(UserContext);

  return (
    <View>
      <Text>Appointment details: </Text>
      <Text>The cost of charging is ${upcomingAppointmentDetails.costOfCharging}</Text>
      <Text>The distance to stations is {upcomingAppointmentDetails.distance} m</Text>
      <Text>The estimated time of charging is {upcomingAppointmentDetails.estimateTimeOfCharging} hours</Text>
      <Text>The estimated time to arrive is {upcomingAppointmentDetails.timeToArrive} seconds</Text>
    </View>
  );
}
