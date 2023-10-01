import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';
import { styles } from '../components/Design';

export default AppointmentView = () => {

  const { upcomingAppointment } = useContext(UserContext);

  return (
    <View>
      <Text>Appointment details: </Text>
      <Text>The cost of charging is ${upcomingAppointment.costOfCharging}</Text>
      <Text>The distance to stations is {upcomingAppointment.distance} m</Text>
      <Text>The estimated time of charging is {upcomingAppointment.estimateTimeOfCharging} hours</Text>
      <Text>The estimated time to arrive is {upcomingAppointment.timeToArrive} seconds</Text>
    </View>
  );
}
