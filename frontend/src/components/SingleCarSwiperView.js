import React from 'react';
import { View, Text, SafeAreaView, Button } from 'react-native'

export default SingleCarSwiperView = ( { car } ) => {

    return (
        <View key={car.id}>
            <Text>Nickname: {car.nickname}</Text> 
            <Text>Battery: {car.batteryPercentage}</Text>
            <Text>Car Plate: {car.plate}</Text>
            <Text>Car Model: {car.model}</Text>
            <Text>Car Id: {car.id}</Text>
        </View>
    );
}