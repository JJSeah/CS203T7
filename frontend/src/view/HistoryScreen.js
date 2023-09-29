import React, { useContext, useState } from 'react';
import { Text, View, SafeAreaView, TouchableOpacity, StyleSheet, ScrollView } from 'react-native';
import { UserContext } from '../model/User';
import { Dropdown } from 'react-native-element-dropdown';

// take here
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from '../components/SingleCarSwiperView';
import CarSwiperView from './CarSwiperView';

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

const fakeData = [
  {
    "station": "a", 
    "date": "2023-09-25", 
    "cost": 23.5, 
    key: 1
  },
  {
    "station": "b", 
    "date": "2023-02-25", 
    "cost": 9.2, 
    key: 2
  },
  {
    "station": "c", 
    "date": "2023-09-20", 
    "cost": 20, 
    key: 3
  },
]

export default HistoryScreen = () => {

  const [monthValue, setMonthValue] = useState(null);
  const [yearValue, setYearValue] = useState(null);
  // const [isFocus, setIsFocus] = useState(false); 
  

  // take here
  const { userCars } = useContext(UserContext);
  const { currentCar, setCurrentCar } = useContext(UserContext);

  const filteredData = fakeData.filter((item) => {
    const dateParts = item.date.split('-');
    const monthNo = parseInt(dateParts[1]);

    return monthValue && String(monthNo) ===  monthValue;
  });

  let totalCost = 0; 
  filteredData.forEach((item) => {
    totalCost += item.cost;
  });

  return (
    <SafeAreaView>
      <View>

        <View style={styles.dropDownContainer}>
          <Dropdown style={styles.month}
            data={month}
            placeholder="Month"
            searchPlaceholder="Select Month"
            value={monthValue}
            maxHeight={300}
            labelField="label"
            valueField="value"
            // onFocus={() => setIsFocus(true)}
            // onBlur={() => setIsFocus(false)}
            onChange={data => {
              setMonthValue(data.value);
              // setIsFocus(false);
            } } />

          <Dropdown style={styles.year}
            data={year}
            placeholder='2023'
            value={yearValue}
            labelField="label"
            valueField="value"
            onChange={data => {
              setYearValue(data.value);
            } } />
        </View>
      </View>

      <ScrollView>
        {fakeData.map((item) => {
          return(
            <View key={item.key}>
            <Text>{item.station} 
            {item.date}
            {item.cost}
            </Text>
            </View>
          )
        })}    
      </ScrollView>

      <View>
        {filteredData.map((item) => {
          return(
            <View key={item.key}>
              <Text>
                {item.station}</Text> 
              </View>
          );
        })}
      </View>

      <View> 
        <Text>Total cost = {totalCost}</Text>
      </View>
        
    </SafeAreaView>
  
  )

}

const styles = StyleSheet.create({
  dropDownContainer:{
    flexDirection: 'row',
    // justifyContent: 'space-between',
    // marginHorizontal: 20, 
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
  }
})
