import React, { useState, useEffect, useImperativeHandle } from "react";
import {
  Text,
  View,
  StyleSheet,
  Dimensions,
  Button,
  ActivityIndicator,
  TouchableOpacity
} from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import Svg, { Circle, Text as SvgText } from "react-native-svg";
// import { styles } from "../components/Design";
import Animated, {
  withTiming,
  useSharedValue,
  useAnimatedProps,
  useDerivedValue,
  Easing,
  runOnJS,
} from "react-native-reanimated";
import { ReText } from "react-native-redash";
import { styles } from "../components/Design"; 
import ChargingCarViewController from "../viewController/ChargingCarViewController";
import { useRoute } from "@react-navigation/native";
import { useIsFocused } from "@react-navigation/native";
import { CarRepository } from "../model/CarRepository";
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';
import { Ionicons } from '@expo/vector-icons';

SplashScreen.preventAutoHideAsync();

const backgroundStrokeColor = "white";
const strokeColor = "#B2D3C2";

const { width, height } = Dimensions.get("window");
const circleLength = 1000;
const radius = circleLength / (2 * Math.PI);

const AnimatedCircle = Animated.createAnimatedComponent(Circle);

export default ChargingCarView = ({ navigation }) => {
  const route = useRoute();
  const appt = route.params;
  const passedInCar = route.params?.car;

  const { loadCarsData } = CarRepository();

  const {
    isReady, 
    setIsReady, 
    checkCarBatteryStatus,
    completeChargingButtonPressed,
    buttonState,
    setButtonState,
    chargingCar,
  } = ChargingCarViewController({ navigation });
  // const [ buttonState, setButtonState ] = useState('STOP');

  const batteryPercentage = chargingCar?.batteryPercentage || 0;
  const progress = useSharedValue(0);

  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
    loadFonts(); 
  }, []);

  useEffect(() => {

    progress.value = withTiming(
      batteryPercentage / 100,
      { duration: 1000, easing: Easing.linear },
      () => {
        if (progress.value >= 1) {
          runOnJS(setButtonState)("FINISH");
        } else {
          runOnJS(setButtonState)("STOP")
        }
      }
    );

  }, [batteryPercentage])

  useEffect(() => {
    checkCarBatteryStatus(passedInCar.id);

    const interval = setInterval(() => {
      checkCarBatteryStatus(passedInCar.id);
    }, 6000);

    return () => {
      console.log("Moving away from screen");
      clearInterval(interval);
      loadCarsData();
    };
  }, []);


  const animatedProps = useAnimatedProps(() => ({
    strokeDashoffset: circleLength * (1 - progress.value),
  }));

  const progressText = useDerivedValue(() => {
    return `${Math.floor(batteryPercentage)}%`;
  });

  return (
    <SafeAreaView style={[chargingStyles.container, {flex:1}]}>
      {chargingCar === null ? (
        <View
        >
          <ActivityIndicator />
          {/* <Text style={chargingStyles.loadingText}>Loading charging car</Text> */}
        </View>
      ) : (
        <View style={{ flex: 1}}>
          <View style={{flexDirection: 'row', marginHorizontal: 100}}>
            <Ionicons name="car-sport-outline" size={40} color="white" />
            <Text style={chargingStyles.carNickname}>{chargingCar.nickname}</Text>
          </View>

          <View style={{ flex: 1 }}>
            <Svg>
              <Circle
                cx={width / 2}
                cy={height / 4}
                r={radius}
                stroke={backgroundStrokeColor}
                strokeWidth={50}

              />

              <AnimatedCircle
                cx={width / 2}
                cy={height / 4}
                r={radius}
                stroke={strokeColor}
                strokeWidth={50}
                strokeDasharray={circleLength}
                animatedProps={animatedProps}
                strokeLinecap={"round"}
              />

              <Circle
                  cx={width / 2}
                  cy={height / 4}
                  r={radius} 
                  fill="#141414" 
                />

              <SvgText
                x={width / 2}
                y={height / 4}
                textAnchor="middle"
                fontSize={17}
                fill="white"
              >
                <ReText
                  style={[chargingStyles.progressText, { fontFamily: 'Product-Sans-Regular' }]}
                  text={progressText}
                />
              </SvgText>
            </Svg>
          </View>

          <View style={{ borderWidth: 2, borderColor: 'red', borderRadius: 10, marginHorizontal:80 }}>
            <Button
              title={buttonState}
              onPress={() => {
                completeChargingButtonPressed(appt);
              }}
              color={buttonState === 'STOP' ? 'red' : 'green'}
            />
          </View>

        </View>
      )}
    </SafeAreaView>
  );
};

const chargingStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  // loadingText:{
  //   fontSize: 30, 
  //   color: 'white',
  //   fontWeight: 'bold',
  //   fontFamily: 'Product-Sans-Regular', 
  //   alignItems: 'center'
  // },
  carNickname: {
    color: 'white', 
    fontSize: 30, 
    fontFamily: 'Product-Sans-Regular', 
    marginLeft: 10,
  },
  progressText: {
    fontSize: 80,
    color: "white",
    textAlign: "center",
    margin: 160,
    marginHorizontal: 40,
  },
});
