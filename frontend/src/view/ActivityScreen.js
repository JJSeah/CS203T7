import React, { useContext, useEffect, useState } from 'react';
import { Text, View, Button, FlatList, StyleSheet } from 'react-native';
import { UserContext } from '../model/User';
import { SafeAreaView } from 'react-native-safe-area-context';
import ActivityViewController from '../viewController/ActivityViewController';
import { MaterialCommunityIcons } from '@expo/vector-icons';


export default ActivityScreen = ({ navigation }) => {
  const { historyButtonPressed, scanQRButtonPressed, cancelButtonPressed } = ActivityViewController({ navigation });
  const { allAppointments } = useContext(UserContext);
  const [ ongoingAppointment, setOngoingAppointment ] = useState(allAppointments.filter((appointment) => {return appointment.status === 'ongoing'}));
  const [ upcomingAppointment, setUpcomingAppointment ] = useState(allAppointments.filter((appointment) => {return appointment.status === 'Active'}))

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

  useEffect(() => {
    setOngoingAppointment(
      allAppointments.filter((appointment) => {return appointment.status === "charging"})
      .sort(

      )
    )

    setUpcomingAppointment(
      allAppointments.filter((appointment) => {return appointment.status === 'Active'})
      .sort(

      )
    )
  }, [allAppointments])
  // const ongoingAppointment = allAppointments.filter((appointment) => {return appointment.status === 'ongoing'})
  // const upcomingAppointment = allAppointments.filter((appointment) => {return appointment.status === 'Active'})

  const cancelOngoingAppt = (itemId) => {
    const updatedOngoingAppt = ongoingAppointment.filter(item => item.id !== itemId);
    setOngoingAppointment(updatedOngoingAppt);
  }

  const cancelUpcomingAppt = (itemId) => {
    const updatedUpcomingAppt = upcomingAppointment.filter(item => item.id !== itemId);
    setUpcomingAppointment(updatedUpcomingAppt);
  }


  return (
    <SafeAreaView style={activityStyles.container}>
        <View style={activityStyles.historyButton}>
             <Button title="History" onPress={historyButtonPressed} />
        </View>
      <View style={activityStyles.halfContainer}>
        <View style={activityStyles.appointmentSection}>
          <Text style={activityStyles.sectionTitle}>ONGOING APPOINTMENT</Text>
          {ongoingAppointment.length === 0 && (
            <View style={activityStyles.centeredContainer}>
            <Text>No activity at the moment</Text>
            </View>
          )}
          <FlatList
            keyExtractor={(item) => item.id.toString()}
            data={ongoingAppointment}
            renderItem={({ item }) => (
              <View style={activityStyles.recordContainer}>
                <View style={activityStyles.stationNameContainer}>
                  <Text style={activityStyles.stationName}>{item.station.name}</Text>
                  <Text style={activityStyles.address}>{item.station.address}</Text>
                  <Text style={activityStyles.dateTime}>
                    {formatDate(item.date)}, {formatTime(item.startTime)}
                  </Text>
                </View>
                <MaterialCommunityIcons name="fuel-cell" size={30} color="black"
                onPress={() => {
                  navigation.navigate("ChargingCarView", item)}
                }/>
              </View>
            )}
          />
        </View>
        <View style={activityStyles.appointmentSection}>
          <Text style={activityStyles.sectionTitle}>UPCOMING APPOINTMENT</Text>
          {upcomingAppointment.length === 0 && (
            <View style={activityStyles.centeredContainer}>
            <Text>No upcoming appointment</Text>
            </View>
          )}
          <FlatList
            keyExtractor={(item) => item.id.toString()}
            data={upcomingAppointment}
            renderItem={({ item }) => (
              <View style={activityStyles.recordContainer}>
                <View style={activityStyles.stationNameContainer}>
                  <Text style={activityStyles.stationName}>{item.station.name}</Text>
                  <Text style={activityStyles.address}>{item.station.address}</Text>
                  <Text style={activityStyles.dateTime}>
                    {formatDate(item.date)}, {formatTime(item.startTime)}
                  </Text>
                </View>
                <View style={activityStyles.iconAndButton}>
                <MaterialCommunityIcons name="qrcode-scan" size={35} color="black" style={activityStyles.icon}
                onPress={() => {
                  scanQRButtonPressed(item)}
                  } />
                <Button title="CANCEL" color='red' onPress={() => {
                  cancelButtonPressed(item); 
                  // cancelUpcomingAppt(item.id)
                  }}/>
                </View>
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
      padding: 25, 
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
    icon: {
      marginBottom: 10,
    },
    iconAndButton: {
      flexDirection: 'column', 
      justifyContent: 'space-between',
      marginTop: 10,
      alignItems: 'center',
    }
  })