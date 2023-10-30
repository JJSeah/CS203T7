import React, { useState, useEffect, useImperativeHandle } from "react";
import {
  Text,
  View,
  StyleSheet,
  Dimensions,
  Button,
  ActivityIndicator,
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
import ChargingCarViewController from "../viewController/ChargingCarViewController";
import { useRoute } from "@react-navigation/native";
import { useIsFocused } from "@react-navigation/native";
import { CarRepository } from "../model/CarRepository";

const backgroundStrokeColor = "black";
const strokeColor = "white";

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
    checkCarBatteryStatus,
    stopButtonPressed,
    finishButtonPressed,
    buttonState,
    setButtonState,
    chargingCar,
  } = ChargingCarViewController({ navigation });
  // const [ buttonState, setButtonState ] = useState('STOP');

  const batteryPercentage = chargingCar?.batteryPercentage || 0;
  const progress = useSharedValue(0);

  useEffect(() => {

    checkCarBatteryStatus(passedInCar.id);

  }, []);

  useEffect(() => {

    progress.value = withTiming(
      batteryPercentage / 100,
      { duration: 1000, easing: Easing.linear },
      () => {
        if (progress.value >= 1) {
          runOnJS(setButtonState)("FINISH");
        }
      }
    );

    const interval = setInterval(() => {
      checkCarBatteryStatus(passedInCar.id);
    }, 6000);

    return () => {
      console.log("Moving away from screen");
      clearInterval(interval);
      loadCarsData();
    };
  }, [chargingCar, batteryPercentage]);

  // useEffect(() => {
  //     if(progress.value >= 0.99){
  //         setButtonState('FINISH');
  //     }
  // }, [progress.value]);

  const animatedProps = useAnimatedProps(() => ({
    strokeDashoffset: circleLength * (1 - progress.value),
  }));

  const progressText = useDerivedValue(() => {
    return `${Math.floor(batteryPercentage)}%`;
  });

  return (
    <SafeAreaView style={chargingStyles.container}>
      {chargingCar === null ? (
        <View
        >
          <ActivityIndicator />
          <Text>Loading charging car</Text>
        </View>
      ) : (
        <View style={{ flex: 1}}>
          <View>
            <Text>The car that is charging is</Text>
            <Text>{chargingCar.nickname}</Text>
            <Text>The battery percentage is</Text>
            <Text>{chargingCar.batteryPercentage}%</Text>
          </View>

          <View style={{ flex: 2 }}>
            <Svg>
              <Circle
                cx={width / 2}
                cy={height / 4}
                r={radius}
                stroke={backgroundStrokeColor}
                strokeWidth={30}
              />

              <AnimatedCircle
                cx={width / 2}
                cy={height / 4}
                r={radius}
                stroke={strokeColor}
                strokeWidth={15}
                strokeDasharray={circleLength}
                animatedProps={animatedProps}
                strokeLinecap={"round"}
              />

              <SvgText
                x={width / 2}
                y={height / 4}
                textAnchor="middle"
                fontSize={20}
                fill="white"
              >
                <ReText
                  style={chargingStyles.progressText}
                  text={progressText}
                />
              </SvgText>
            </Svg>
          </View>

          <View style={{ flex: 1 }}>
            <Button
              title={buttonState}
              onPress={() => {
                if (buttonState === "STOP") {
                  navigation.pop();
                  navigation.navigate("MakePaymentScreen", appt);
                } else if (buttonState === "FINISH") {
                  navigation.pop();
                  navigation.navigate("MakePaymentScreen", appt);
                }
              }}
              color={buttonState === "STOP" ? "red" : "green"}
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
    // alignItems: 'center',
    // justifyContent: 'center',
  },
  progressText: {
    fontSize: 70,
    color: "white",
    textAlign: "center",
    margin: 160,
    marginHorizontal: 35,
  },
});
