import React from 'react';
import { View, Text, SafeAreaView, Button, Image, StyleSheet } from 'react-native'; 
import { TESLA1 } from '../../assets/images/index';

export default SingleCarSwiperView = ( { car } ) => {

    return (
        <View key={car.id}>
          <Image
            source={TESLA1}
            style = {localStyles.carStyle}
            resizeMode='contain'/>
        </View>
    );
}

const localStyles = StyleSheet.create({
    carStyle: {
        flex: 1,
        marginBottom: 100,
      },
})