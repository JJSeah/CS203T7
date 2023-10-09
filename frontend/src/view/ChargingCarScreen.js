import React, { useState } from 'react';
import { Text, View, StyleSheet, Dimensions } from 'react-native';
import { UserContext } from '../model/User';
import { StatusBar } from 'expo-status-bar';
// import {CircularProgress} from 'react-native-circular-progress-indicator';
import Svg, { Circle } from 'react-native-svg';

const backgroundStrokeColor = 'red';
const strokeColor = 'pink'; 
const {width, height} = Dimensions.get('window');
const circleLength = 1000;
const radius = circleLength / (2 * Math.PI); 

export default ChargingCarScreen = ({ navigation }) => {
    // const [ value, setValue ] = useState(0);


  return (
    <View>
      <Text>Charging...</Text>
      <Svg>
        <Circle 
            cx={width/2}
            cy={height/2}
            r={radius}
            stroke={backgroundStrokeColor}
            strokeWidth={30}
        />
        <Circle 
            cx={width/2}
            cy={height/2}
            r={radius}
            stroke={strokeColor}
            strokeWidth={15}
            strokeDasharray={circleLength}
            strokeDashoffset={circleLength * 0.3}
        />
      </Svg>
    </View>
  );
}