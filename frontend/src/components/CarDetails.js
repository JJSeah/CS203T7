import React from 'react';
import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import FontAwesome from 'react-native-vector-icons/FontAwesome';

export default CarDetails = ( { icon, value, title } ) => {

  return (
    <View style = {localStyles.container}>
        <FontAwesome name ={icon} size={20} color = "#ccc"/>
        <Text>{value}</Text>
        <Text>{title}</Text>

    </View>
  );

}

const localStyles = StyleSheet.create({

});