import React, { useContext, useEffect, useState, useRef } from 'react';
import { Text, View, SafeAreaView, TouchableOpacity, StyleSheet, ScrollView, FlatList, Button } from 'react-native';
import HistoryScreenViewController from '../viewController/HistoryScreenViewController';
import { UserContext } from '../model/User';
import { Dropdown } from 'react-native-element-dropdown';
import { styles } from "../components/Design"; 
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';
import { set, sortBy } from 'lodash';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import { Ionicons } from '@expo/vector-icons';
import { MaterialIcons } from '@expo/vector-icons';

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
  {label: '2022', value:'2022'}, 
  {label: '2023', value:'2023'}, 
  {label: '2024', value:'2024'}
]


export default HistoryScreen = ({navigation}) => {

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
    loadFonts(); 
  }, []);

  const { isReady, setIsReady, selectedCar, setSelectedCar, monthValue, setMonthValue, yearValue, setYearValue, showAllRecords, setShowAllRecords, filteredRecords, setFilteredRecords, testButtonPressed} = HistoryScreenViewController({navigation})
  const { userCars, allAppointments } = useContext(UserContext);
  const [ filteredAppointments, setFilteredAppointments ] = useState([]);
  const [ totalCost, setTotalCost ] = useState(0);

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
      if (selectedCar != null && monthValue !== null && yearValue != null && allAppointments){
        const filtered = sortBy(allAppointments.filter((appointment) => {

          const dateParts = appointment.date.split('-');
          const monthNo = parseInt(dateParts[1]);
          const yearNo = parseInt(dateParts[0]);

          return appointment.car.nickname === selectedCar && String(monthNo) ===  monthValue && String(yearNo) === yearValue && appointment.status === 'completed';
        }), 'date').reverse();

        setFilteredAppointments(filtered);
      }

      else if (selectedCar != null && monthValue != null && allAppointments){
        const filtered = sortBy(allAppointments.filter((appointment) => {

          const dateParts = appointment.date.split('-');
          const monthNo = parseInt(dateParts[1]);
          
          return appointment.car.nickname === selectedCar && String(monthNo) ===  monthValue && appointment.status === 'completed';
        }), 'date').reverse();

        setFilteredAppointments(filtered);
      }

      else if (selectedCar != null && allAppointments){
        const filtered = sortBy(allAppointments.filter((appointment) => {
          return appointment.car.nickname === selectedCar && appointment.status === 'completed';
        }), 'date').reverse();

        setFilteredAppointments(filtered);
      }
      
      else if (monthValue !== null && yearValue != null && allAppointments) {
        const filtered = sortBy(allAppointments.filter((appointment) => {

          // appointment.status === "completed";
          const dateParts = appointment.date.split('-');
          const monthNo = parseInt(dateParts[1]);
          const yearNo = parseInt(dateParts[0]);
          
          return String(monthNo) ===  monthValue && String(yearNo) === yearValue && appointment.status === 'completed';
        }), 'date').reverse();
        
        setFilteredAppointments(filtered);

      } else if (monthValue !== null && allAppointments) {
        const filtered = sortBy(allAppointments.filter((appointment) => {

          // appointment.status === "completed";
          const dateParts = appointment.date.split('-');
          const monthNo = parseInt(dateParts[1]);
          
          return String(monthNo) ===  monthValue && appointment.status === 'completed';
        }), 'date').reverse();
        
        setFilteredAppointments(filtered);

      } else if (yearValue != null && allAppointments) {
        const filtered = sortBy(allAppointments.filter((appointment) => {

          // appointment.status === "completed";
          const dateParts = appointment.date.split('-');
          const yearNo = parseInt(dateParts[0]);
          
          return String(yearNo) === yearValue && appointment.status === 'completed';
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
  }, [selectedCar, monthValue, yearValue, showAllRecords, allAppointments]);

  useEffect(() => {
    if(filteredAppointments){
      let updatedTotalCost = 0; 
      filteredAppointments.forEach((appointment) => {
        updatedTotalCost += appointment.cost + 5;
      });
      setTotalCost(updatedTotalCost);
    }
  }, [filteredAppointments]);
  
  return (
    <SafeAreaView style={[historyStyles.container, {flex:1}]}>
      <View styles={historyStyles.container1}>

      <View style={{marginLeft:240}}>
      {((selectedCar && monthValue && yearValue && !showAllRecords) || (monthValue && yearValue && !showAllRecords) || 
      (selectedCar && !showAllRecords) || (monthValue && !showAllRecords) || (yearValue && !showAllRecords)) && (
        <TouchableOpacity
        style={historyStyles.filterButton}
         onPress={() => {
          setSelectedCar(null);
          setMonthValue(null); 
          setYearValue(null);
          setShowAllRecords(true);
          setFilteredAppointments(allAppointments);
         }}
        >
          <View style={{ flexDirection: 'row', alignItems: 'center'}}>
            <MaterialIcons name="clear" size={20} color="white" />
            <Text style={historyStyles.filterText}>Clear Filters</Text>
          </View>
          </TouchableOpacity>
       )}  
    </View>
        <View style={historyStyles.dropDownContainer}>
        <Dropdown style={historyStyles.car}
            data={userCars.map(car => ({ value: car.nickname, label: car.nickname }))}
            placeholder="Car"
            placeholderStyle={{color: 'grey'}}
            searchPlaceholder="Select Car"
            value={selectedCar}
            labelField="label"
            valueField="value"
            onChange={data => {
              setSelectedCar(data.value);
              setShowAllRecords(false);
            }} 
            itemTextStyle={historyStyles.dropdownValueText}
            itemContainerStyle={historyStyles.dropdownList}
            selectedTextStyle={{color: 'white'}}
            activeColor='grey'
            />

          <Dropdown style={historyStyles.month}
            data={month}
            placeholder="Month"
            placeholderStyle={{color: 'grey'}}
            searchPlaceholder="Select Month"
            value={monthValue}
            maxHeight={300}
            labelField="label"
            valueField="value"
            onChange={data => {
              setMonthValue(data.value);
              setShowAllRecords(false);
            } } 
            itemTextStyle={historyStyles.dropdownValueText}
            itemContainerStyle={historyStyles.dropdownList}
            selectedTextStyle={{color: 'white'}}
            activeColor='grey'
            />

          <Dropdown style={historyStyles.year}
            data={year}
            placeholder='2023'
            placeholderStyle={{color: 'grey'}}
            value={yearValue}
            labelField="label"
            valueField="value"
            onChange={data => {
              setYearValue(data.value);
              setShowAllRecords(false);
            }} 
            itemTextStyle={historyStyles.dropdownValueText}
            itemContainerStyle={historyStyles.dropdownList}
            selectedTextStyle={{color: 'white'}}
            activeColor='grey'
            />
        </View>
      </View>
    
      
      <View style={historyStyles.container2}>
      {(showAllRecords || (selectedCar || monthValue || yearValue)) && filteredAppointments.length > 0 && (
        <View style={historyStyles.totalCostContainer}>
          <Text style={historyStyles.totalCost}>Total Cost : $ {totalCost.toFixed(2)}
          </Text>
        </View> 
      )}
      </View>


    <View style={historyStyles.container3}>
      {(selectedCar || monthValue || yearValue) && filteredAppointments.length === 0 && (
          <View style={{alignItems: 'center'}}>
              <Text style={historyStyles.totalCost}>No Record</Text>
          </View>
      )}

      <FlatList
        style={historyStyles.flatListContainer}
        data={filteredAppointments}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({item}) => (
          <View style={historyStyles.recordContainer}>
            <View style={historyStyles.stationNameContainer}>
              <View style={historyStyles.carContainer}>
                <Ionicons name="car-sport-outline" size={21} color="#D3D3D3" />
                <Text style={historyStyles.carNickname}>{item.car.nickname}</Text>
              </View>
              <Text style={historyStyles.stationName}>{item.station.name}</Text>
              <Text style={historyStyles.address}>{item.station.address}</Text>
              <Text style={historyStyles.dateTime}>{formatDate(item.date)}
              , {formatTime(item.startTime)} - {formatTime(item.endTime + 50)}
              </Text>
              
            </View>
            <Text style={historyStyles.cost}>${(item.cost + 5).toFixed(2)}</Text>
          </View>
        )}
        />
      </View>
    </SafeAreaView> 
  )
}

const historyStyles = StyleSheet.create({
  container:{
    flex: 1,
    backgroundColor: "#141414",
  },
  flatListContainer:{
    // flex: 1,
  },
  container1: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: '#141414',
  },
  container2: {
   flex: 1,
    backgroundColor: '#141414',
  },
  container3: {
    flex: 15,
    backgroundColor: '#141414',
  },
  dropDownContainer:{
    // flex: 1, 
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center', 
    padding: 16,
    backgroundColor: '#141414', 
  },
  dropdownList: {
    backgroundColor: '#141414',
  },
  dropdownValueText: {
    color: 'white',
  },
  car: {
    height: 45, 
    width: 135,
    backgroundColor: '#141414',
    borderColor: 'grey',
    borderWidth: 0.5, 
    borderRadius: 5, 
    paddingHorizontal: 15,
    marginLeft: 0,
    marginTop: 10, 
  },
  month: {
    // flex: 1,
    height: 45, 
    width: 120, 
    borderColor: 'gray', 
    borderWidth: 0.5, 
    borderRadius: 5, 
    paddingHorizontal: 15,
    marginLeft: 10,
    marginTop: 10,  
  }, 
  year:{
    height: 45, 
    width: 90, 
    borderColor: 'gray',
    borderWidth: 0.5, 
    borderRadius: 5, 
    paddingHorizontal: 15,
    marginLeft: 10,
    marginTop: 10, 
  }, 
  totalCostContainer:{
    marginLeft: 240,
  },
  recordContainer: {   
    // flex: 10, 
    borderWidth: 1, 
    borderColor: '#D3D3D3', 
    borderRadius: 10, 
    padding: 16, 
    margin: 15,
    marginTop: 0,
    marginBottom: 10,
    flexDirection: 'row', 
    justifyContent: 'space-between', 
    alignItems: 'center',
  }, 
  stationNameContainer: {
    flexDirection: 'column',
    alignItems: 'flex-start', 
  },
  carContainer:{
    flexDirection: 'row', 
  },
  carNickname: {
    fontWeight: 'bold', 
    fontSize: 16, 
    fontFamily: 'Product-Sans-Regular', 
    marginLeft: 12, 
    color: 'white',
  },
  stationName: {
    fontWeight: 'bold', 
    fontSize: 20, 
    fontFamily: 'Product-Sans-Regular', 
    color: 'white',
    // marginBottom: 8,
  },
  address: {
    fontSize: 13, 
    color: '#808080',
    fontFamily: 'Product-Sans-Regular',
  },
  dateTime: {
    fontSize: 15, 
    fontFamily: 'Product-Sans-Regular',
    color: 'white',
  }, 
  cost: {
    fontSize: 16,
    fontStyle: 'italic', 
    fontFamily: 'Product-Sans-Regular',
    color: 'white',
  }, 
  totalCost: {
    fontSize: 16, 
    fontWeight: 'bold', 
    fontFamily: 'Product-Sans-Regular', 
    color: 'white',
    marginBottom: 5,
    marginRight: 10,
  }, 
  filterButton: {
    borderRadius: 10, 
    marginHorizontal: 10, 
    borderColor: 'white',
    borderWidth: 0.5,
    padding: 10,
  }, 
  filterText: {
    fontSize: 15, 
    color: '#B2D3C2',
    fontFamily: 'Product-Sans-Regular',
    fontWeight: 'bold', 
    marginLeft: 10, 
  }
})
