import React, { useState, useEffect } from "react";
import { Text, View, StyleSheet, Dimensions, Button } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import Svg, { Circle, Text as SvgText} from 'react-native-svg';
// import { styles } from "../components/Design";
import Animated, { withTiming, useSharedValue, useAnimatedProps, useDerivedValue, Easing, runOnJS } from 'react-native-reanimated'; 
import { ReText } from 'react-native-redash';
import ChargingCarViewController from "../viewController/ChargingCarViewController";


const backgroundStrokeColor = 'black';
const strokeColor = 'white';

const {width, height} = Dimensions.get('window');
const circleLength = 1000; 
const radius = circleLength / ( 2 * Math.PI);

const AnimatedCircle = Animated.createAnimatedComponent(Circle);

export default ChargingCarView = ( { navigation } ) => {
    const { stopButtonPressed, finishButtonPressed, buttonState, setButtonState } = ChargingCarViewController({ navigation })
    // const [ buttonState, setButtonState ] = useState('STOP');
    const progress = useSharedValue(0);

    useEffect(() => {
        progress.value = withTiming(1, {duration:4000, easing: Easing.linear }, () => {
        if (progress.value >= 1){
            runOnJS(setButtonState)('FINISH');
        }
        });
    }, []);

    // useEffect(() => {
    //     if(progress.value >= 0.99){
    //         setButtonState('FINISH');
    //     }
    // }, [progress.value]);

    const animatedProps = useAnimatedProps(() => ({
        strokeDashoffset: circleLength * (1-progress.value)
    }));

    const progressText = useDerivedValue(() => {
        return `${Math.floor(progress.value * 100)}%`; 
    });

  return (
    <SafeAreaView style={chargingStyles.container}>
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
            <ReText style={chargingStyles.progressText} text={progressText}/>
               
        </SvgText>
        </Svg>

        <Button title={buttonState}
        onPress={() => {
        if (buttonState === 'STOP'){
            stopButtonPressed();
        } else if (buttonState === 'FINISH'){
            finishButtonPressed();
        }
        }}
        color={buttonState === 'STOP' ? 'red' : 'green'}/>
    </SafeAreaView>
  );
};

const chargingStyles = StyleSheet.create({
    container:{
        flex: 1, 
        alignItems: 'center', 
        justifyContent: 'center', 
    }, 
    progressText: {
        fontSize: 70, 
        color: 'white', 
        textAlign: 'center', 
        margin: 165, 
        marginHorizontal: 30, 
    }
})