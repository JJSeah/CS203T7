import React, { useContext, useState } from 'react';
import { Text, View, SafeAreaView, TouchableOpacity, StyleSheet } from 'react-native';
import { UserContext } from '../model/User';
import { Dropdown } from 'react-native-element-dropdown';

// take here
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from '../components/SingleCarSwiperView';

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

export default HistoryScreen = () => {

  const [monthValue, setMonthValue] = useState(null);
  const [yearValue, setYearValue] = useState(null);
  // const [isFocus, setIsFocus] = useState(false); 
  

  // take here
  const { userCars } = useContext(UserContext);
  const { currentCar, setCurrentCar } = useContext(UserContext);

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

    
        <View>
        <Text>start here</Text>
          
        <View style={ { height: 400 }}>
          <Swiper
            cardStyle={ { backgroundColor:'red', height: 400 }}
            cards={userCars}
            infinite={true}
            onSwiped={index => {
              console.log(currentCar)
              setCurrentCar(userCars[(index + 1) % userCars.length])
            }}
            renderCard={card => {
              return( 
                <SingleCarSwiperView
                  car={card}
                />
              )
            }}
          />
        </View>
        <Text>The current car is {currentCar.nickname}</Text>



        </View> 


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
