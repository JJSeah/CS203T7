import React from "react";
import { Text, View } from "react-native";
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from "../components/SingleCarSwiperView";

export default CarSwiperView = ( { cars, onSwiped } ) => {

    return (
        <View style={{backgroundColor:'red'}}>
            (cars.length === 0) ?

            (
            <Text>no cars to swipe</Text> 
            )
            :
            (
            <Swiper
            cards={cars}
            infinite={true}
            onSwiped={index => {
                console.log(index)
            //   console.log(currentCar)
            //   setCurrentCar(userCars[(index + 1) % userCars.length])
            }}
            renderCard={card => {
            return( 
                <SingleCarSwiperView
                car={card}
                />
            )
            }}
            />
            )
        </View>
    );

}