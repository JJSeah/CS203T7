import React, { useState, useEffect, useContext } from 'react';
import { Text, View, StyleSheet, TouchableOpacity, Button, Image } from 'react-native';
import { UserContext } from '../model/User';
// import { styles } from '../components/Design';
import { BarCodeScanner } from 'expo-barcode-scanner'; 
import UpcomingAppointmentViewController from '../viewController/UpcomingAppointmentViewController';
import { MaterialCommunityIcons } from '@expo/vector-icons';
import axios from "axios";
import { BASE_URL } from '../constants/Config';


export default UpcomingAppointmentView = ({ navigation }) => {
 
  const [ hasPermission, setHasPermission ] = useState(null); 
  const [ scanned, setScanned ] = useState(false);
  // const [ qrCodeData, setQRCodeData ] = useState(null);
  const { upcomingAppointmentDetails } = useContext(UserContext);
  const { userToken, userID } = useContext(UserContext);
  const { chargingProgressButtonPressed } = UpcomingAppointmentViewController({navigation});

  const askForCameraPermission = () => {
    (async () => {
      const { status } = await BarCodeScanner.requestPermissionsAsync(); 
      setHasPermission(status == 'granted'); 
    })()
  }

  useEffect(() => {
    askForCameraPermission();
  }, []);

  const handleBarCodeScanned = ({ type, data }) => {
    setScanned(true); 
    console.log('Type: ' + type + '\nData: ' + data)
  }; 

  const getQRCode = async() => {
    
  return axios.get(`${BASE_URL}/api/appointment/checkComingAppt`,
   {
      headers: {
        'Authorization': `Bearer ${userToken}`
      }
   }
   )
   .then ( res => {
    let data = res.data
    console.log(data);
   })
   .catch (e => {
    console.log(`catch exception: ${e}`)
   })
  };

  const resetQRCode = () => {
    setQRCodeData(null);
    setScanned(false);
  }

  if(hasPermission === null){
    return <View />;
  }

  if(hasPermission === false){
    return (
      <View style={styles.container}>
        <Text style={styles.text}>Camera permission not granted</Text>
        <Button title={'Allow camera'} onPress={() => askForCameraPermission()}/>
      </View>
    );
  };

  return (
    <View style={styles.container}>

      <Text style={styles.text}>Scan the barcode to start charging.</Text>
      {/* {renderCamera()} */}
      <View style={styles.cameraContainer}>
        <BarCodeScanner
        onBarCodeScanned={scanned ? undefined : handleBarCodeScanned}
        style={styles.camera}
        />
      </View>

      {/* {qrCodeData ? (
        <Image source={{ uri: qrCodeData }} style={styles.qrCode} />
      ): (
        <Button title="Generate QR Code" onPress={getQRCode} />
      )}  */}

    <Button title="Generate QR Code" onPress={getQRCode} />
{/* <Button title="Generate QR Code" onPress={getQRCode} />
<Text>{qrCodeData}</Text> */}

      {/* {qrCodeData && (
        <Button title="reset" onPress={resetQRCode} />
      )} */}
{/* 
      <Button title="reset" onPress={() => setScanned(false)} disabled={!scanned} /> */}
      {/* <TouchableOpacity 
        style={styles.button}
        onPress={() => setScanned(false)}
        disabled={scanned}
      >
        <Button title="scanned qr code"
         onPress={chargingProgressButtonPressed}/>
      </TouchableOpacity> */}
    </View>
  );
}; 

const styles = StyleSheet.create({
  container: { 
    flex: 1,
    alignItems: 'center', 
    justifyContent: 'center',
  }, 
  text: {
    fontSize: 14, 
    marginBottom: 20, 
  },
  cameraContainer: {
    width: '80%', 
    aspectRatio: 1, 
    height: 300,
    overflow: 'hidden', 
    borderRadius: 10, 
    marginBottom: 40, 
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
    marginBottom: 20, 
  }
})
