import React, { useEffect } from "react";
import { Text, View, StyleSheet, Dimensions } from "react-native";
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from "../components/SingleCarSwiperView";


export default CarSwiperView = ( { cars, currentCar, onSwiped, userCars} ) => {

    return (
        <View style={styles.container}>

            {(cars.length === 0) ?

                (
                    <Text>no cars to swipe</Text> 
                )
            :
            
            (
                <View>
                    <Swiper
                        containerStyle={styles.swiperStyle}
                        stackScale={userCars.length}
                        cards={cars}
                        infinite={true}
                        cardStyle={styles.cardStyle}
                        onSwiped={index => {
                            onSwiped(userCars[(index + 1) % userCars.length])
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
                </View>
            )}
        </View>
    );

}

const styles = StyleSheet.create({
    "container": {
        maxHeight: Dimensions.get('screen').height,
        flex:1
    },
    "cardStyle" : {
        backgroundColor: 'red',
        height: Dimensions.get('window').height/3,
        width: Dimensions.get('window').width * 0.5,
        margin: 20
    },
    "swiperStyle" : {
        backgroundColor: 'transparent',
        height: Dimensions.get('window').height,
        maxHeight: Dimensions.get("window").height/10,
        padding: 10,
        margin: 20,
    }
})