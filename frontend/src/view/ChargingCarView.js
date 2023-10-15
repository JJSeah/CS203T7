import React, { useState, useEffect } from "react";
import { Text, View, StyleSheet, Dimensions, Button } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import Svg, { Circle, Text as SvgText} from 'react-native-svg';
// import { styles } from "../components/Design";
import Animated, { withTiming, useSharedValue, useAnimatedProps, useDerivedValue, Easing } from 'react-native-reanimated'; 
import { ReText } from 'react-native-redash';
import ChargingCarViewController from "../viewController/ChargingCarViewController";


const backgroundStrokeColor = 'black';
const strokeColor = 'white';

const {width, height} = Dimensions.get('window');
const circleLength = 1000; 
const radius = circleLength / ( 2 * Math.PI);

const AnimatedCircle = Animated.createAnimatedComponent(Circle);

export default ChargingCarView = ( { navigation } ) => {
    const { stopButtonPressed, finishButtonPressed } = ChargingCarViewController({ navigation })
    const [ buttonState, setButtonState ] = useState('STOP');
    const progress = useSharedValue(0);

    useEffect(() => {
        progress.value = withTiming(1, {duration:2000, easing: Easing.linear })
    }, []);

    useEffect(() => {
        if(progress.value >= 0.99){
            setButtonState('FINISH');
        }
    }, [progress.value]);

    const animatedProps = useAnimatedProps(() => ({
        strokeDashoffset: circleLength * (1-progress.value)
    }));

    const progressText = useDerivedValue(() => {
        return `${Math.floor(progress.value * 100)}`; 
    });

  return (
    <SafeAreaView style={styles.container}>
        {/* <ReText style={styles.progressText} text={progressText}/> */}
      <Svg>
        <Circle 
        cx={width/2}
        cy={height/4}
        r={radius}
        stroke={backgroundStrokeColor}
        strokeWidth={30}
        /> 

        <AnimatedCircle 
        cx={width/2}
        cy={height/4}
        r={radius}
        stroke={strokeColor}
        strokeWidth={15}
        strokeDasharray={circleLength}
        animatedProps={animatedProps}
        strokeLinecap={'round'}
        /> 

        <SvgText
            x={width/2}
            y={height/4}
            textAnchor="middle"
            fontSize={20}
            fill="white" 
            >
            <ReText text={progressText}/>
               
        </SvgText>
        </Svg>

        <Button title={buttonState}
        onPress={() => {
        if (buttonState === 'STOP'){
            stopButtonPressed();
        } else if (buttonState === 'FINISH'){
            finishButtonPressed();
        }
        }}/>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
    container:{
        flex: 1, 
        alignItems: 'center', 
        justifyContent: 'center', 
    }, 
    progressText: {
        fontSize: 80, 
        color: 'white', 
        textAlign: 'center'
    }
})