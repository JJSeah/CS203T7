import React from 'react';
import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import FontAwesome from 'react-native-vector-icons/FontAwesome';

export default InBetweenSpace = ( {title, value} ) => {
    return (
        <View style = {styles.container}>
            <Text style = {styles.titleText}>{title}</Text>
            <Text style = {styles.valueText}>{value}</Text>
        </View>
    );
}

const styles = StyleSheet.create({
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
    }
});