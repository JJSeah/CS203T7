import React, { useContext } from 'react';
import { Text, View, Button, FlatList, StyleSheet } from 'react-native';
import { UserContext } from '../model/User';
import { SafeAreaView } from 'react-native-safe-area-context';
import ActivityViewController from '../viewController/ActivityViewController';

const fakeData = [
    {
      "station": "Shell Recharge", 
      "address": "80 upper thomson",
      "date": "2023-10-15", 
      "time": "13:05:35",
      "cost": 23.5, 
      key: 1
    },
    {
      "station": "SP Mobility ", 
      "address": "50 toa payoh",
      "date": "2023-10-30", 
      "time": "09:15:05",
      "cost": 9.2, 
      key: 2
    },
    {
        "station": "CHARGE+", 
        "address": "60 orchard",
        "date": "2023-011-20", 
        "time": "20:40:45",
        "cost": 20, 
        key: 3
      },
  ]


export default ActivityScreen = ({ navigation }) => {
  const { historyButtonPressed, scanQRButtonPressed } = ActivityViewController({ navigation });


  const formatDate = (dateStr) => {
    const date = new Date(dateStr); 
    const day = date.getDate(); 
    const monthIndex = date.getMonth();
    const year = date.getFullYear(); 

    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const month = months[monthIndex];

    return `${day} ${month} ${year}`;
  };

  const formatTime = (timeStr) => {
    const time = new Date(`2000-01-01T${timeStr}`);
    const hours = time.getHours();
    const mins = time.getMinutes();
    const ampm = hours >= 12 ? 'PM' : 'AM'; 
    const formattedHours = hours % 12 || 12;

    return `${formattedHours}:${mins.toString().padStart(2, '0')} ${ampm}`;
  }

  const ongoingAppointments = fakeData.filter(appointment => new Date(appointment.date) <= new Date());
  const upcomingAppointments = fakeData.filter(appointment => new Date(appointment.date) > new Date());


  return (
    <SafeAreaView style={activityStyles.container}>
        <View style={activityStyles.historyButton}>
             <Button title="History" onPress={historyButtonPressed} />
        </View>
      <View style={activityStyles.halfContainer}>
        <View style={activityStyles.appointmentSection}>
          <Text style={activityStyles.sectionTitle}>ONGOING APPOINTMENT</Text>
          {ongoingAppointments.length === 0 && (
            <View style={activityStyles.centeredContainer}>
            <Text>No activity at the moment</Text>
            </View>
          )}
          <FlatList
            keyExtractor={(item) => item.key.toString()}
            data={ongoingAppointments}
            renderItem={({ item }) => (
              <View style={activityStyles.recordContainer}>
                <View style={activityStyles.stationNameContainer}>
                  <Text style={activityStyles.stationName}>{item.station}</Text>
                  <Text style={activityStyles.address}>{item.address}</Text>
                  <Text style={activityStyles.dateTime}>
                    {formatDate(item.date)}, {formatTime(item.time)}
                  </Text>
                </View>
                <Button title="SCAN QR"
                onPress={scanQRButtonPressed}/>
              </View>
            )}
          />
        </View>
        <View style={activityStyles.appointmentSection}>
          <Text style={activityStyles.sectionTitle}>UPCOMING APPOINTMENTS</Text>
          {upcomingAppointments.length === 0 && (
            <View style={activityStyles.centeredContainer}>
            <Text>No upcoming appointments</Text>
            </View>
          )}
          <FlatList
            keyExtractor={(item) => item.key.toString()}
            data={upcomingAppointments}
            renderItem={({ item }) => (
              <View style={activityStyles.recordContainer}>
                <View style={activityStyles.stationNameContainer}>
                  <Text style={activityStyles.stationName}>{item.station}</Text>
                  <Text style={activityStyles.address}>{item.address}</Text>
                  <Text style={activityStyles.dateTime}>
                    {formatDate(item.date)}, {formatTime(item.time)}
                  </Text>
                </View>
                <Button title="SCAN QR"
                onPress={scanQRButtonPressed} />
              </View>
            )}
          />
        </View>
      </View>
    </SafeAreaView>
  );
}

const activityStyles = StyleSheet.create({
    container: {
        flex: 1,
    },
    halfContainer: {
        flex: 1, 
        flexDirection: 'column'
    }, 
    appointmentSection: {
        flex: 1, 
        padding: 8, 
    },
    sectionTitle: {
        fontSize: 20, 
        fontWeight: 'bold',
        marginLeft: 15,
        marginTop: 30,
    },
    recordContainer: {
      borderWidth: 1, 
      borderColor: 'black', 
      borderRadius: 10, 
      padding: 30, 
      margin: 8,
      flexDirection: 'row', 
      justifyContent: 'space-between', 
      alignItems: 'center',
    }, 
    centeredContainer: {
      flex: 1, 
      justifyContent: 'center', 
      alignItems: 'center',
    },
    stationNameContainer: {
      flexDirection: 'column',
      alignItems: 'flex-start', 
      flex: 1,
    },
    stationName: {
      fontWeight: 'bold', 
      fontSize: 20, 
      fontFamily: 'Product-Sans-Regular', 
      // marginBottom: 8,
    },
    address: {
      fontSize: 13, 
      color: 'gray',
      fontFamily: 'Product-Sans-Regular'
    },
    dateTime: {
      fontSize: 15, 
      fontFamily: 'Product-Sans-Regular'
    },
    historyButton: {
        position: 'absolute', 
        top: 50, 
        right: 10,
        zIndex: 1,
    },
  })