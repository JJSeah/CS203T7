import React, { useContext, useEffect } from "react";
import { Text, View, StyleSheet, Dimensions } from "react-native";
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from "../components/SingleCarSwiperView";
import { UserContext } from "../model/User";


export default CarSwiperView = ( ) => {

    const {userCars, setCurrentCar} = useContext(UserContext);

    return (
        <View style={localStyles.container}>

            {
            (userCars.length === 0) ?

                <Text>no cars to swipe</Text> 
            :
            
            (
                    <Swiper
                        stackScale={userCars.length}
                        cards={userCars}
                        infinite={true}
                        cardStyle={localStyles.cardStyle}
                        onSwiped={index => {
                            setCurrentCar(userCars[(index + 1) % userCars.length])
                        }}
                        renderCard={card => {
                            return( 
                                <View> 
                                    <SingleCarSwiperView
                                        car={card}
                                    />
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