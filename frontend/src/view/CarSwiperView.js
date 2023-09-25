import React, { useContext, useEffect } from "react";
import { Text, View, StyleSheet, Dimensions } from "react-native";
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from "../components/SingleCarSwiperView";
import { UserContext } from "../model/User";
import { CarRepository } from "../model/CarRepository";


export default CarSwiperView = ( { userCars, setCurrentCar }) => {

    // const {userCars, setCurrentCar} = useContext(UserContext);
    // const { loadCarsData } = CarRepository()

    const renderCar = ( card, cardIndex ) => {
        return (
            <View key={cardIndex}>
              <SingleCarSwiperView
                car={card}
                />
            </View>
        );
    }

    return (
        <View style={localStyles.container}>

            {
            (userCars.length === 0) ?

                <Text>no cars to swipe</Text> 
            :
            
            (
                    <Swiper
                        key={userCars.length}
                        stackScale={userCars.length}
                        cards={userCars}
                        infinite={true}
                        cardStyle={localStyles.cardStyle}
                        onSwiped={index => {
                            setCurrentCar(userCars[(index + 1) % userCars.length])
                        }}
                        renderCard={(card, cardIndex) => {
                            return renderCar(card, cardIndex)
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