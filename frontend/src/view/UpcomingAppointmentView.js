import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { UserContext } from '../model/User';

export default AppointmentView = () => {

  const { upcomingAppointment } = useContext(UserContext);

  return (
    <View>
      <Text>Appointment details: </Text>
      <Text>The cost of charging is {upcomingAppointment.costOfCharging}</Text>
      <Text>The distance to stations is {upcomingAppointment.distance}</Text>
      <Text>The estimated time of charging is {upcomingAppointment.estimateTimeOfCharging}</Text>
      <Text>The estimated time to arrive is {upcomingAppointment.timeToArrive}</Text>
    </View>
  );
}
