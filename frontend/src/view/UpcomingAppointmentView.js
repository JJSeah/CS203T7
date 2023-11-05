import React, { useState, useEffect, useContext } from "react";
import {
  Text,
  View,
  StyleSheet,
  TouchableOpacity,
  Button,
  Image,
} from "react-native";
import { UserContext } from "../model/User";
// import { styles } from '../components/Design';
import { BarCodeScanner } from "expo-barcode-scanner";
import UpcomingAppointmentViewController from "../viewController/UpcomingAppointmentViewController";
import CustomLongButton from "../components/CustomLongButton";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import axios from "axios";
import { BASE_URL } from "../constants/Config";
import { useRoute } from "@react-navigation/native";
import FontLoader from '../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';

SplashScreen.preventAutoHideAsync();

export default UpcomingAppointmentView = ({ navigation }) => {
  useEffect(() => {
    const loadFonts = async() => {
      await FontLoader();
      setIsReady(true);
      await SplashScreen.hideAsync();
    }; 
    loadFonts(); 
  }, []);

  const [hasPermission, setHasPermission] = useState(null);
  const [ isReady, setIsReady ] = useState(false);   

  const { scanQrCodeCorrectCharger, scanQrCodeIncorrectCharger } =
    UpcomingAppointmentViewController({
      navigation,
    });
  const route = useRoute();
  const appt = route.params;

  const askForCameraPermission = () => {
    (async () => {
      const { status } = await BarCodeScanner.requestPermissionsAsync();
      setHasPermission(status == "granted");
    })();
  };

  useEffect(() => {
    askForCameraPermission();
  }, []);

  if (hasPermission === null) {
    return <View />;
  }

  if (hasPermission === false) {
    return (
      <View style={styles.container}>
        <Text style={styles.text}>Camera permission not granted</Text>
        <Button
          title={"Allow camera"}
          onPress={() => askForCameraPermission()}
        />
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <View
        style={{
          flex: 1.5,
          // backgroundColor: "red",
          justifyContent: "flex-end",
          alignItems: "center",
        }}
      >
        <View
          style={{justifyContent: "center", alignItems: "center"}} 
        >
          {/* <View>
          <Text style={styles.text}>Go to Charger {appt.charger.id}</Text>
          </View> */}
          <Text style={styles.text}>Scan the barcode to start charging</Text>
        </View>
        <View style={styles.cameraContainer}>
          <BarCodeScanner onBarCodeScanned={() => {}} style={styles.camera} />
        </View>
      </View>

      <View
        style={{
          flex: 1,
          paddingBottom: 20,
          justifyContent: "center",
          alignItems: "center",
          flexDirection: "column",
        }}
      >
        <View style={{ flex: 1, flexDirection: "row" }}>
          <CustomLongButton
            title="Scan (Correct charger)"
            onPress={() => {
              scanQrCodeCorrectCharger(appt);
            }}
          />
        </View>

        <View style={{flex: 1, flexDirection: "row" }}>
          <CustomLongButton
            title="Scan (Incorrect charger)"
            onPress={() => {
              scanQrCodeIncorrectCharger(appt);
            }}
          />
        </View>
      </View>
    </View> 
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#141414",
  },
  text: {
    fontSize: 14,
    marginBottom: 20,
    color: "white",
    fontSize: 18,
    fontWeight: "bold",
    fontFamily: 'Product-Sans-Regular'
  },
  cameraContainer: {
    width: "80%",
    aspectRatio: 1,
    height: 300,
    overflow: "hidden",
    borderRadius: 10,
  },
  camera: {
    flex: 1,
  },
  button: {
    padding: 10,
    borderRadius: 5,
  },
  qrCode: {
    width: 350,
    height: 350,
  },
});
