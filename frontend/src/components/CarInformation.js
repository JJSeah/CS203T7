import React from 'react';
import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import FontAwesome from 'react-native-vector-icons/FontAwesome';

export default CarInformation = ( {title, value} ) => {


    return (
        <View style = {localStyles.container}>
            <Text style = {localStyles.titleText}>{title}</Text>
            <Text style = {localStyles.valueText}>{value}</Text>
        </View>
    );
}

const localStyles = StyleSheet.create({
    container: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginLeft: 10,
        marginRight: 10,
    },

    titleText: {
        fontFamily: "Product-Sans-Regular",
        fontSize: 14,
        color: "white",
        fontWeight: 'bold',
      },

        valueText: {
        fontFamily: "Product-Sans-Regular",
        fontSize: 14,
        color: "white",
        marginBottom: 10,
      },
});