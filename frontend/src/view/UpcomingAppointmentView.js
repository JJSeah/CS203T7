import React, { useState, useEffect, useContext } from 'react';
import { Text, View, StyleSheet, TouchableOpacity, Button } from 'react-native';
import { UserContext } from '../model/User';
// import { styles } from '../components/Design';
import { BarCodeScanner } from 'expo-barcode-scanner'; 
import { Camera } from 'expo-camera';
import UpcomingAppointmentViewController from '../viewController/UpcomingAppointmentViewController';

export default UpcomingAppointmentView = ({ navigation }) => {
 
  const [ hasPermission, setHasPermission ] = useState(null); 
  const [ scanned, setScanned ] = useState(false);
  const { upcomingAppointmentDetails } = useContext(UserContext);

  const {chargingProgressButtonPressed} = UpcomingAppointmentViewController({navigation})

  useEffect(() => {
    (async() => {
      const { status } = await BarCodeScanner.requestPermissionsAsync();
      setHasPermission(status === 'granted');
    })();
  }, []);

  const handleBarCodeScanned = ({ type, data }) => {
    // setScanned(true); 
    alert(`Barcode type: ${type} and data: ${data}`);
  }; 

  // const renderCamera = () => {
  //   return (
  //     <View style={styles.cameraContainer}>
  //     <BarCodeScanner
  //       onBarCodeScanned={scanned ? undefined : handleBarCodeScanned}
  //       style={styles.camera}
  //     />
  //     </View> 
  //   );
  // }; 

  if(hasPermission === null){
    return <View />;
  }

  if(hasPermission === false){
    return (
      <View style={styles.container}>
        <Text style={styles.text}>Camera permission not granted</Text>
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <Text>Appointment details: </Text>
      {/* <Text>The cost of charging is ${upcomingAppointmentDetails.costOfCharging}</Text> */}
      {/* <Text>The distance to stations is {upcomingAppointmentDetails.distance} m</Text>
      <Text>The estimated time of charging is {upcomingAppointmentDetails.estimateTimeOfCharging} hours</Text>
      <Text>The estimated time to arrive is {upcomingAppointmentDetails.timeToArrive} seconds</Text> */}

      <Text style={styles.text}>Scan the barcode to start charging.</Text>
      {/* {renderCamera()} */}
      <View style={styles.cameraContainer}>
        <BarCodeScanner 
        onBarCodeScanned={scanned ? undefined : handleBarCodeScanned}
        style={styles.camera}
        />
      </View>
      <TouchableOpacity 
        style={styles.button}
        onPress={() => setScanned(false)}
        disabled={scanned}
      >
        <Button title="Scan QR"
         onPress={chargingProgressButtonPressed}/>
      </TouchableOpacity>
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
  }
})
