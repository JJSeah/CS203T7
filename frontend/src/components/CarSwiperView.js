import React from 'react';
import { View, Text, SafeAreaView, Button } from 'react-native'
import Swiper from "react-native-deck-swiper"


export default CarSwiperView = ( { data } ) => {

    return (
        <SafeAreaView>
            <View>

                <Carousel
                    data={data}
                    sliderWidth={300}
                    itemWidth={300}

                    renderItem={(car) => (
                        <View>
                            <Text>{car.nickname}</Text>
                        </View>
                    )}
                />

                <Button
                    title="testing"
                    onPress={() => {console.log(cards[1].nickname)}}
                />
            </View>
        </SafeAreaView>
    );
}