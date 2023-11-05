import React from 'react';
import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import FontAwesome from 'react-native-vector-icons/FontAwesome';

export default InBetweenSpace = ( {title, value} ) => {
    return (
        <View style = {localStyles.container}>
            <Text style = {localStyles.subHeaderText}>{title}</Text>
            <Text style = {localStyles.subHeaderText}>{value}</Text>
        </View>
    );
}

const localStyles = StyleSheet.create({
    container: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        padding: 16,
    },
    titleText: {
        fontWeight: 'bold',
        fontSize: 16,
    },
    valueText: {
        fontSize: 16,
    },
    subHeaderText: {
        fontFamily: "Product-Sans-Regular",
        fontSize: 14,
        color: "white",
        marginBottom: 10,
      },
});