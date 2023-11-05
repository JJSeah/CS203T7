import React from 'react';
import { View, Text, SafeAreaView, Button, Image, StyleSheet } from 'react-native'; 
import { TESLA1, TESLA2 } from '../../assets/images/index';

export default SingleCarSwiperView = ( { car } ) => {

    let carImage;

  // Determine which image to use based on the car model
  switch (car.model) {
    case "Tesla Model S Long Range":
      carImage = TESLA1;
      break;
    case "Tesla Model S Plaid":
      carImage = TESLA2;
      break;
    // Add more cases for other car models here

    default:
      // Handle any other car models
      carImage = TESLA1;
  }

    return (
        <View key={car.id}>
          <Image
            source={carImage}
            style = {localStyles.carStyle}
            resizeMode='contain'/>
        </View>
    );
}

const localStyles = StyleSheet.create({
    carStyle: {
        flex: 1,
        marginBottom: 100,
      width: 300,
      height: 200,
      },
})