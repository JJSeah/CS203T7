import React, { useContext, useEffect } from "react";
import {
  Text,
  View,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  ActivityIndicator
} from "react-native";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";
import { useRoute } from "@react-navigation/native";
import CustomLongButton from "../components/CustomLongButton";
import MakePaymentViewController from "../viewController/MakePaymentViewController";
import { Ionicons } from "@expo/vector-icons";
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';
import { MaterialCommunityIcons } from "@expo/vector-icons";

SplashScreen.preventAutoHideAsync();

export default PaymentScreen = ({ navigation }) => {
  const { userCards, userCars } = useContext(UserContext);
  const { isReady, setIsReady, paymentCard, checkPaymentAmount, amount, setPaymentCard, makePayment } =
    MakePaymentViewController({
      navigation,
    });
  const route = useRoute();
  const appt = route.params;
  const station = route.params.station;

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
    loadFonts(); 
  }, []);


  useEffect(() => {
    checkPaymentAmount(appt.id)
  }, [])

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

  return (
    <SafeAreaView style={localStyles.safeArea}>
      <View style={localStyles.costContainer}>
        {amount === null ? (
          <ActivityIndicator />
        ) : (
          <View>
            <Text style={localStyles.sgdText}>SGD</Text>
            <Text style={localStyles.costText}>{amount.toFixed(2)} </Text>
          </View>
        )}
      </View>

      <View style={localStyles.stationInfoContainer}>
        <View style={{flexDirection: 'row', alignItems:'center'}}>
        <MaterialCommunityIcons name="ev-station" size={35} color="white"/>
          <View style={{flexDirection: 'column'}}>
            <Text style={localStyles.stationName}>{appt.station.name}</Text>
            <Text style={localStyles.address}>{appt.station.address}</Text>
          </View>
          </View>
      </View>

      <View style={localStyles.dateAndCardContainer}>
        <View style={{flexDirection: 'row', marginBottom: 15}}>
            <Text style={localStyles.header}>Date </Text>
            <Text style={localStyles.dateTime}>{formatDate(appt.date)}</Text>
        </View>

        <View style={{flexDirection: 'row', marginBottom: 15}}>
            <Text style={localStyles.header}>Time </Text>
            <Text style={localStyles.dateTime}>{formatTime(appt.startTime)} - {formatTime(appt.endTime)}</Text>
        </View>

        <Text style={localStyles.header}>Payment</Text>
        <ScrollView>
          {userCards.map((card) => (
            <View key={card.id} style={localStyles.cardContainer}>
              <View style={{flexDirection: 'row', marginRight: 110}}>
                <TouchableOpacity
                  onPress={() => {
                    setPaymentCard(card);
                  }}
                >
                 <View style={{flexDirection: 'row'}}>
                    <Text style={localStyles.cardOptionText}>Credit / Debit Card  </Text>
                    <Text style={localStyles.cardNumber}>*{card.number.slice(-4)}</Text>
                  </View>
                </TouchableOpacity>
              </View>
              <View>
                {card.id === paymentCard.id ? (
                  <View>
                    <Ionicons
                      name="checkmark-circle"
                      size={24}
                      color="green"
                    />
                  </View>
                ) : (
                  <></>
                )}
              </View>
            </View>
          ))}
        </ScrollView>
      </View>

      <View style={localStyles.bottomContainer}>
        <TouchableOpacity
        style={localStyles.confirmButton}
        onPress={() => {
                      makePayment(appt.id, paymentCard.id);
                    }}
                    disabled={amount === null}
        >
          <Text style={localStyles.buttonText}>Confirm Payment</Text>
                    
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  safeArea: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#141414",
  },
  costContainer: {
    flex: 1.75,
    alignItems: "center",
    justifyContent: "center",
  },
  sgdText: {
    fontSize: 25,
    color: 'grey',
    fontWeight: 'bold',  
    fontFamily: 'Product-Sans-Regular', 
  },
  costText: {
    fontSize: 90,
    color: 'white',
    fontWeight: 'bold',  
    fontFamily: 'Product-Sans-Regular',
    marginLeft: 30,
  },
  stationInfoContainer: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    marginBottom: 40,
  },
  stationName: {
    fontSize: 20,
    marginLeft: 10, 
    color: 'white',
    fontWeight: 'bold',  
    fontFamily: 'Product-Sans-Regular', 
  },
  address: {
    fontSize: 13,
    marginTop: 5,
    marginLeft: 10,
    color: 'grey', 
    fontFamily: 'Product-Sans-Regular', 
  },
  dateAndCardContainer: {
    flex: 3,
    flexDirection: 'column',
    alignItems: "left",
    // marginRight: 85,
    // justifyContent: "center",
  },
  cardContainer: {
    borderWidth: 1,
    borderColor: "white",
    borderRadius: 10,
    padding: 15,
    margin: 8,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },
  cardItem: {
    flexDirection: "row", 
    alignItems: "stretch", 
    flex: 1,
  },
  dateTime: {
    fontSize: 20,
    color: 'white',
    fontWeight: 'bold',  
    fontFamily: 'Product-Sans-Regular', 
  },
  header:{
    fontSize: 20,
    color: 'grey',
    fontWeight: 'bold',  
    fontFamily: 'Product-Sans-Regular', 
    marginLeft: 10,
    marginRight: 130
  },
  cardOptionText: {
    fontSize: 16,
    color: 'white',
    fontWeight: 'bold',  
    fontFamily: 'Product-Sans-Regular',
  },
  cardNumber: {
    fontSize: 18,
    color: '#D3D3D3',
    fontWeight: 'bold',  
    fontFamily: 'Product-Sans-Regular', 
  },
  bottomContainer: {
    alignItems: "center",
    flex: 1,
  },
  cardInfoContainer: {
    flexDirection: "row",
    alignItems: "stretch",
  },
  confirmButton: {
    width: '70%',
    height: 50, 
    borderWidth: 1,         
    borderColor: 'white',   
    backgroundColor: 'white',  
    borderRadius: 80,      
    paddingHorizontal: 45,   
    paddingVertical: 5,
    alignItems: 'center',
    justifyContent: 'center',
    shadowColor: '#D3D3D3',       // Shadow color
    shadowOffset: { width: 4, height: 4  }, // Shadow offset
    shadowOpacity: 0.25,         // Shadow opacity
    shadowRadius: 4,            // Shadow radius
    elevation: 5,  
  }, 
  buttonText:{
    color: 'black',
    fontSize: 16,
    fontFamily: 'Product-Sans-Regular',
  }
});

