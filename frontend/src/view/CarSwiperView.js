import React, { useEffect } from "react";
import { Text, View, StyleSheet, Dimensions } from "react-native";
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from "../components/SingleCarSwiperView";


export default CarSwiperView = ( { cars, currentCar, onSwiped } ) => {

    return (
        <View style={localStyles.container}>

            {
            (cars.length === 0) ?

                <Text>no cars to swipe</Text> 
            :
            
            (
                    <Swiper
                        stackScale={cars.length}
                        cards={cars}
                        infinite={true}
                        cardStyle={localStyles.cardStyle}
                        onSwiped={index => {
                            onSwiped(cars[(index + 1) % cars.length])
                            console.log(`The current car is ${currentCar.nickname}`)
                        }}
                        renderCard={card => {
                            return( 
                                <View> 
                                    <SingleCarSwiperView
                                        car={card}
                                    />
                                    <Text>The current car is {currentCar.nickname}</Text>
                                </View>
                            )
                        }}
                    />
            )}
        </View>
    );

}

const localStyles = StyleSheet.create({
    "container": {
        flex:1
    },
    "cardStyle" : {
        backgroundColor: 'green',
        height: 200,
    },
    "swiperStyle" : {
        backgroundColor: 'transparent',
        padding: 10,
        margin: 20,
    }
})