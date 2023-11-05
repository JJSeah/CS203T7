import React, { useContext, useEffect, useState } from "react";
import {
  Text,
  View,
  Button,
  FlatList,
  StyleSheet,
  ActivityIndicator,
  TouchableOpacity
} from "react-native";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";
import ActivityViewController from "../viewController/ActivityViewController";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import { Entypo } from '@expo/vector-icons';
import { Ionicons } from '@expo/vector-icons';
import { Fontisto } from '@expo/vector-icons';

export default ActivityScreen = ({ navigation }) => {
  const {
    sortAppointment,
    historyButtonPressed,
    scanQRButtonPressed,
    cancelButtonPressed,
  } = ActivityViewController({ navigation });
  const { allAppointments } = useContext(UserContext);
  const [ongoingAppointment, setOngoingAppointment] = useState(null);
  const [upcomingAppointment, setUpcomingAppointment] = useState(null);

  const formatDate = (dateStr) => {
    const date = new Date(dateStr);
    const day = date.getDate();
    const monthIndex = date.getMonth();
    const year = date.getFullYear();

    const months = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ];
    const month = months[monthIndex];

    return `${day} ${month} ${year}`;
  };

  const formatTime = (timeStr) => {
    const time = new Date(`2000-01-01T${timeStr}`);
    const hours = time.getHours();
    const mins = time.getMinutes();
    const ampm = hours >= 12 ? "PM" : "AM";
    const formattedHours = hours % 12 || 12;

    return `${formattedHours}:${mins.toString().padStart(2, "0")} ${ampm}`;
  };

  const filterActiveAppointment = () => {
    setUpcomingAppointment(
      allAppointments
        .filter((appointment) => {
          return appointment.status === "Active";
        })
        .sort(sortAppointment)
    );
  };

  const filterChargingAppointment = () => {
    setOngoingAppointment(
      allAppointments
        .filter((appointment) => {
          return appointment.status === "charging";
        })
        .sort(sortAppointment)
    );
  };

  useEffect(() => {
    if (allAppointments !== null) {
      filterActiveAppointment();
      filterChargingAppointment();
      return;
    }
    setOngoingAppointment(null);
    setUpcomingAppointment(null);
  }, [allAppointments]);

  return (
    <SafeAreaView style={[activityStyles.container, {flex:1}]}>
      <View>
        <TouchableOpacity
        style={activityStyles.historyButton}
        onPress={historyButtonPressed}
        disabled={allAppointments === null}
        >
          <View style={{flexDirection: 'row', alignItems: 'center'}}>
            <Entypo name="back-in-time" size={24} color="black" />
            <Text style={activityStyles.historyText}>History</Text>
          </View>

        </TouchableOpacity>  
      
      </View>
      <View style={activityStyles.halfContainer}>
        <View style={activityStyles.ongoingSection}>
          <Text style={activityStyles.sectionTitle}>ONGOING APPOINTMENT</Text>
          {ongoingAppointment === null ? (
            <View
            style={{flex: 1, justifyContent: "center", alignItems: "center"}} 
            >
              <ActivityIndicator />
            </View>
          ) : (
            <View style={{ flex: 1 }}>
              {ongoingAppointment.length === 0 ? (
                <View style={activityStyles.centeredContainer}>
                  <Text style={activityStyles.text}>No Ongoing Appointment</Text>
                </View>
              ) : (
                
                <FlatList
                  keyExtractor={(item) => item.id.toString()}
                  data={ongoingAppointment}
                  renderItem={({ item }) => ( 
                    <View style={activityStyles.ongoingContainer}>
                      <View style={activityStyles.stationNameContainer}>
                        <View style={{marginBottom: 10, flexDirection: 'row'}}>  
                        <Fontisto name="date" size={20} color="#D3D3D3" />
                      <Text style={activityStyles.dateTime}>
                          {formatDate(item.date)}, {formatTime(item.startTime)}
                        </Text>
                        </View>

                        <View style={{flexDirection: 'row'}}>
                          <Ionicons name="car-sport-outline" size={21} color="#D3D3D3" />
                          <Text style={activityStyles.carNickname}>{item.car.nickname}</Text>  
                         </View> 
                      
                        <View style={{flexDirection: 'row', marginTop: 10}}>
                          <Entypo name="location" size={21} color="#D3D3D3" />
                          <View>
                          <View style={{flexDirection: 'column', marginLeft: 7}}>
                          <Text style={activityStyles.stationName}>
                            {item.station.name}
                          </Text>
                          <Text style={activityStyles.address}>
                          {item.station.address}
                          </Text>
                          </View>
                        </View>
                        </View>

                      <View style={{flexDirection: 'row', marginTop: 10}}>
                        <MaterialCommunityIcons
                                  name="ev-station"
                                  size={23}
                                  color="#D3D3D3"
                          />
                        <View style={{marginLeft: 7}}>
                        <Text style={activityStyles.stationName}>{item.charger.id}</Text>
                        </View>
                      </View>
                 
                      </View>
                      <View style={activityStyles.icon}>
                      <MaterialCommunityIcons
                        name="fuel-cell"
                        size={40}
                        color="white"
                        onPress={() => {
                          navigation.navigate("ChargingCarView", item);
                        }}
                      />
                      </View>
                    </View>
                  )}
                />
              )}
            </View>
          )}
        </View>
        <View style={activityStyles.upcomingSection}>
          <Text style={activityStyles.sectionTitle}>UPCOMING APPOINTMENTS</Text>
          {upcomingAppointment === null ? (
            <View
              style={{flex: 1, justifyContent: "center", alignItems: "center"}} 
            >
              <ActivityIndicator />
            </View>
          ) : (
            <View style={{ flex: 1 }}>
              {upcomingAppointment.length === 0 ? (
                <View style={activityStyles.centeredContainer}>
                  <Text style={activityStyles.text}>No Upcoming Appointment</Text>
                </View>
              ) : (
                <View>
                  <FlatList
                    keyExtractor={(item) => item.id.toString()}
                    data={upcomingAppointment}
                    renderItem={({ item }) => (
                      <View style={activityStyles.upcomingContainer}>
                      <View style={activityStyles.stationNameContainer}>
                        <View style={{marginBottom: 10, flexDirection: 'row'}}>  
                        <Fontisto name="date" size={20} color="#D3D3D3" />
                      <Text style={activityStyles.dateTime}>
                          {formatDate(item.date)}, {formatTime(item.startTime)}
                        </Text>
                        </View>

                        <View style={{flexDirection: 'row'}}>
                          <Ionicons name="car-sport-outline" size={21} color="#D3D3D3" />
                          <Text style={activityStyles.carNickname}>{item.car.nickname}</Text>  
                         </View> 
                      
                        <View style={{flexDirection: 'row', marginTop: 10}}>
                          <Entypo name="location" size={21} color="#D3D3D3" />
                          <View>
                          <View style={{flexDirection: 'column', marginLeft: 7}}>
                          <Text style={activityStyles.stationName}>
                            {item.station.name}
                          </Text>
                          <Text style={activityStyles.address}>
                          {item.station.address}
                          </Text>
                          </View>
                        </View>
                        </View>

                      <View style={{flexDirection: 'row', marginTop: 10}}>
                        <MaterialCommunityIcons
                                  name="ev-station"
                                  size={23}
                                  color="#D3D3D3"
                          />
                        <View style={{marginLeft: 7}}>
                        <Text style={activityStyles.stationName}>{item.charger.id}</Text>
                        </View>
                      </View>
                 
                      </View>
                        
                        {/* <View style={{flexDirection: 'column', alignItems: 'center'}}> */}
                          <View style={{marginRight: 25, marginBottom: 93}}>
                          <MaterialCommunityIcons
                            name="qrcode-scan"
                            size={40}
                            color="white"
                            // style={activityStyles.icon}
                            onPress={() => {
                              scanQRButtonPressed(item);
                            }}
                          />
                          </View>
                          <View style={{position: 'absolute', bottom: 10, right: 2}}>
                          <Button
                            title="CANCEL"
                            color="red"
                            onPress={() => {
                              cancelButtonPressed(item);
                            }}
                          />
                          {/* </View> */}
                        </View>
                      </View>
                    )}
                  />
                </View>
              )}
            </View>
          )}
        </View>
      </View>
    </SafeAreaView>
  );
};

const activityStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#141414'
  },
  text:{
    color: 'white',
    fontWeight: "bold",
    fontSize: 16,
    fontFamily: "Product-Sans-Regular",
  },
  historyButton:{
    borderWidth: 1,         
    borderColor: 'white',   
    backgroundColor: '#D3D3D3',  
    borderRadius: 80, 
    marginLeft: 260, 
    marginRight: 10,    
    padding: 5,
    alignItems: 'center',
    justifyContent: 'center',
  },
  historyText:{
    color: 'black', 
    marginLeft: 10,
    fontFamily: 'Product-Sans-Regular',
    fontSize: 20
  },
  halfContainer: {
    flex: 1,
    flexDirection: "column",
  },
  ongoingSection:{
    flex: 1.5,
    padding: 6
  },
  upcomingSection: {
    flex: 2,
    padding: 6,
  },
  sectionTitle: {
    color: 'white',
    fontSize: 20,
    fontWeight: "bold",
    marginLeft: 15,
    marginTop: 30,
  },
  ongoingContainer: {
    borderWidth: 3,
    borderColor: "#B2D3C2",
    borderRadius: 10,
    padding: 20,
    margin: 10,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  upcomingContainer: {
    borderWidth: 1,
    borderColor: "white",
    borderRadius: 10,
    padding: 20,
    paddingRight: 0,
    margin: 10,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  centeredContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  stationNameContainer: {
    flexDirection: "column",
    alignItems: "flex-start",
    flex: 1,
  },
  carNickname: {
    fontWeight: 'bold', 
    fontSize: 16, 
    fontFamily: 'Product-Sans-Regular', 
    marginLeft: 8, 
    color: 'white',
  },
  stationName: {
    color: 'white',
    fontWeight: "bold",
    fontSize: 18,
    fontFamily: "Product-Sans-Regular",
    // marginBottom: 8,
  },
  address: {
    color: 'white',
    fontSize: 14,
    color: "gray",
    fontFamily: "Product-Sans-Regular",
  },
  dateTime: {
    fontSize: 20,
    color: 'white',
    fontFamily: "Product-Sans-Regular",
    marginLeft: 8
  },
  icon: {
    marginBottom: 10,
  },
  iconAndButton: {
    flexDirection: "column",
    justifyContent: "space-between",
    marginTop: 10,
    alignItems: "center",
  },
});
