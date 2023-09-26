import React, { useContext, useEffect } from "react";
import { Text, View, StyleSheet, Dimensions, ActivityIndicator, Image } from "react-native";
import Swiper from "react-native-deck-swiper"
import SingleCarSwiperView from "../components/SingleCarSwiperView";
import { UserContext } from "../model/User";
import { CarRepository } from "../model/CarRepository";
import { emptyCarIcon } from "../../assets/images/index";

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
            <View style={localStyles.loadingCarContainer}>
                <Text>Please add a car</Text>
                       {/* <Image source = {emptyCarIcon}
            style = {localStyles.emptyCarIcon}/> */}
            </View>
            :
                (userCars[0].nickname === "dummy") ? 
                    <View style={localStyles.loadingCarContainer}>
                        <ActivityIndicator></ActivityIndicator>
                    </View>
                 :
                <View>
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
                </View>
            }
        </View>
    );

}

const localStyles = StyleSheet.create({
    "container": {
        flex:1,
    },
    loadingCarContainer: {
        flex:1,
        justifyContent: 'center'
    },
    "cardStyle" : {
        backgroundColor: 'transparent',
        justifyContent:'center',
        alignItems: 'center',
        height: 300,
    },
    "swiperStyle" : {
        backgroundColor: 'transparent',
    },
    emptyCarIcon: {
        marginTop: 100,
        width: 250,
        height: 160,
        resizeMode: 'contain',
        opacity: 0.2,
      },
})