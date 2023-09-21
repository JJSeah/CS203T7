import React from 'react';
import { View, Text, SafeAreaView, Button } from 'react-native'

export default SingleCarSwiperView = ( { car } ) => {

    return (
        <View key={car.id}>
            <Text>Nickname: {car.nickname}</Text> 
            <Text>Battery: {car.batteryPercentage}</Text>
            <Text>{car.plate}</Text>
            <Text>{car.model}</Text>
        </View>
    );
}