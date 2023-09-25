import React from 'react';
import { Text, View, StyleSheet, ScrollView, Image } from 'react-native';
import FontAwesome from 'react-native-vector-icons/FontAwesome';

import * as imageAssets from '../../assets/images';

export default CarDetails = ( { iconName, value, title } ) => { 

  const selectedIcon = imageAssets[iconName];

  return (
    <View style = {localStyles.container}>
  
      <View style = {localStyles.iconContainer}>
      <Image source = {selectedIcon}
        style = {localStyles.iconStyle}
        />
      </View>
      <View style = {localStyles.textContainer}></View>
        <Text style = {localStyles.valueStyle}>{value}</Text>
        <Text style = {localStyles.titleStyle}>{title}</Text>

    </View>
  );

}

const localStyles = StyleSheet.create({
  container: {
    flexDirection: 'column',
    alignItems: 'center',
  },
  iconContainer: {
    width: 35,
    height: 35,
    borderRadius: 20,
    borderWidth: 2,
    borderColor:'white',
    backgroundColor: 'grey',
    justifyContent: 'center',
    alignItems: 'center',
  },
  textContainer: {
    flexDirection: 'column',
    width: 100,
  },

  iconStyle: {
    width: 23,
    height: 23,
  },
  valueStyle: {
    textAlign: 'center',
    fontFamily: "Product-Sans-Regular",
    fontSize: 20,
    fontWeight: 'bold',
    color: "white",
    marginTop: 5,

  },
  titleStyle: {
    textAlign: 'center',
    fontFamily: "Product-Sans-Regular",
    fontSize: 14,
    fontWeight: 'bold',
    color: "grey",
  }
});