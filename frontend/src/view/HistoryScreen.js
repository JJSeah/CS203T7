import React, { useContext, useEffect, useState, useRef } from 'react';
import { Text, View, SafeAreaView, TouchableOpacity, StyleSheet, ScrollView, FlatList, Button } from 'react-native';
import HistoryScreenViewController from '../viewController/HistoryScreenViewController';
import { UserContext } from '../model/User';
import { Dropdown } from 'react-native-element-dropdown';
import { styles } from "../components/Design"; 
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';
import { set, sortBy } from 'lodash';


SplashScreen.preventAutoHideAsync();

const month = [
  {label: 'January', value:'1'}, 
  {label: 'February', value:'2'}, 
  {label: 'March', value:'3'}, 
  {label: 'April', value:'4'}, 
  {label: 'May', value:'5'}, 
  {label: 'June', value:'6'}, 
  {label: 'July', value:'7'}, 
  {label: 'August', value:'8'}, 
  {label: 'September', value:'9'}, 
  {label: 'October', value:'10'}, 
  {label: 'November', value:'11'}, 
  {label: 'December', value:'12'}, 
]

const year =[
  {label: '2022', value:'1'}, 
  {label: '2023', value: '2'}, 
  {label: '2024', value: '3'}
]


export default HistoryScreen = ({navigation}) => {

  const {isReady, setIsReady, monthValue, setMonthValue, yearValue, setYearValue, showAllRecords, setShowAllRecords, filteredRecords, setFilteredRecords, testButtonPressed} = HistoryScreenViewController({navigation})
  const { allAppointments } = useContext(UserContext);
  const [ filteredAppointments, setFilteredAppointments ] = useState([]);
  const [ totalCost, setTotalCost ] = useState(0);

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
    loadFonts(); 
  }, []);

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
    const filterData = () => {
      if (monthValue !== null && yearValue != null && allAppointments) {
        const filtered = sortBy(allAppointments.filter((appointment) => {

          // appointment.status === "completed";
          const dateParts = appointment.date.split('-');
          const monthNo = parseInt(dateParts[1]);
          const yearNo = parseInt(dateParts[0]);
          
          return String(monthNo) ===  monthValue && String(yearNo) === yearValue && appointment.status === 'completed';
        }), 'date').reverse();
        
        setFilteredAppointments(filtered);
      } else if (allAppointments) {
          const allRecords = sortBy(allAppointments.filter((appointment) => {
          return appointment.status === 'completed'
          }), 'date').reverse();

        setFilteredAppointments(allRecords);
      }
    };

    filterData();
  }, [monthValue, showAllRecords, allAppointments]);

  useEffect(() => {
    if(filteredAppointments){
      let updatedTotalCost = 0; 
      filteredAppointments.forEach((appointment) => {
        updatedTotalCost += appointment.cost;
      });
      setTotalCost(updatedTotalCost);
    }
  }, [filteredAppointments]);

  // let totalCost = 0; 
  // if(filteredRecords){
  // filteredRecords.forEach((appointment) => {
  //   totalCost += appointment.cost; 
  // });
  // }
  
  return (
    <SafeAreaView>
      <View>

        <View style={historyStyles.dropDownContainer}>
          <Dropdown style={historyStyles.month}
            data={month}
            placeholder="Month"
            placeholderStyle={{color: 'black'}}
            searchPlaceholder="Select Month"
            value={monthValue}
            maxHeight={300}
            labelField="label"
            valueField="value"
            onChange={data => {
              setMonthValue(data.value);
              setShowAllRecords(false);
            } } />

          <Dropdown style={historyStyles.year}
            data={year}
            placeholder='2023'
            placeholderStyle={{color: 'black'}}
            value={yearValue}
            labelField="label"
            valueField="value"
            onChange={data => {
              setYearValue(data.value);
              setShowAllRecords(false);
            } } />
        </View>
      </View>
      
      {monthValue && yearValue && filteredAppointments.length === 0 && (
        <View>
          <Text style={historyStyles.totalCost}>No record</Text>
        </View>
      )}

      {monthValue && yearValue && filteredAppointments.length > 0 && (
        <View>
          <Text style={historyStyles.totalCost}>Total cost for {month.find((m) => m.value === monthValue).label} 
                {year.find((y) => y.label === yearValue)?.label}: ${totalCost}
          </Text>
        </View> 
      )}

      {(!monthValue || !yearValue || showAllRecords) &&(
      <View> 
        <Text style={historyStyles.totalCost}>Total cost: ${totalCost}</Text>
      </View>
      )}


      <FlatList
        data={filteredAppointments}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({item}) => (
          <View style={historyStyles.recordContainer}>
            <View style={historyStyles.stationNameContainer}>
              <Text style={historyStyles.stationName}>{item.station.name}</Text>
              <Text style={historyStyles.address}>{item.station.address}</Text>
              <Text style={historyStyles.dateTime}>{formatDate(item.date)}, {formatTime(item.startTime)}</Text>
            </View>
            <Text style={historyStyles.cost}>${item.cost}</Text>
          </View>
        )}
        />
     
      {monthValue && yearValue && !showAllRecords && (
        <Button
         title='show all records'
         onPress={() => {
          setMonthValue(null); 
          setYearValue('2023');
          setShowAllRecords(true);
          setFilteredAppointments(allAppointments);
         }}
        />
      )}  
  
    </SafeAreaView> 
  )
}

const historyStyles = StyleSheet.create({
  dropDownContainer:{
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center', 
    padding: 16,
  },
  month: {
    // flex: 1,
    height: 45, 
    width: 135, 
    borderColor: 'gray', 
    borderWidth: 0.5, 
    borderRadius: 5, 
    paddingHorizontal: 15,
    marginLeft: 130,
    marginTop: 10,  
  }, 
  year:{
    height: 45, 
    width: 95, 
    borderColor: 'gray',
    borderWidth: 0.5, 
    borderRadius: 5, 
    paddingHorizontal: 15,
    marginLeft: 10,
    marginTop: 10, 
  }, 
  recordContainer: {
    borderWidth: 1, 
    borderColor: 'black', 
    borderRadius: 10, 
    padding: 20, 
    margin: 8,
    flexDirection: 'row', 
    justifyContent: 'space-between', 
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
  cost: {
    fontSize: 16,
    fontStyle: 'italic', 
    fontFamily: 'Product-Sans-Regular'
  }, 
  totalCost: {
    fontSize: 16, 
    fontWeight: 'bold', 
    fontFamily: 'Product-Sans-Regular', 
    marginHorizontal: 10
  }
})
