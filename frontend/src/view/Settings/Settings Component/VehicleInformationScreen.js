import React, { useContext } from "react";
import {
  StyleSheet, Text, 
  View, ScrollView,
  ActivityIndicator, SafeAreaView, 
  Alert, Image
} from "react-native";
import { UserContext } from "../../../model/User";
import CarInformation from "../../../components/CarInformation";
import CustomLongButton from "../../../components/CustomLongButton";
import AddCarScreen from "../../AddCarScreen";
import DeleteCarViewController from "../../../viewController/DeleteCarViewController";
import AddCarScreenViewController from "../../../viewController/AddCarScreenViewController";

import { TESLA1 } from "../../../../assets/images/index";
import IconButton from "../../../components/IconButton";


export default VehicleInformationScreen = ( {navigation} ) => {
  const {  userCars } =
    useContext(UserContext);

  const { deleteCarButtonPressed } = DeleteCarViewController();

  return (
    <SafeAreaView style={localStyles.container}>

      <ScrollView>
      <View style={localStyles.sectionContainer}>
      {userCars === null ? (
        <View>
          <ActivityIndicator />
        </View>
      ) : Array.isArray(userCars) && userCars.length > 0 ? (
        userCars.map((car) => (
          <View key={car.id}>
            <View style = {localStyles.informationContainer}>

            <View style = {localStyles.sectionHeader}>
            <Text style = {localStyles.headerText}>{car.nickname}</Text>
            <IconButton
              iconName="trashIcon"
              onPress={() => {
                Alert.alert(
                  "Delete car",
                  "Are you sure you want to delete " + car.nickname,
                  [
                    {
                      text: "Cancel",
                      onPress: () => {},
                    },
                    {
                      text: "Delete",
                      onPress: () => {
                        deleteCarButtonPressed(car.id);
                      },
                      style: "destructive",
                    },
                  ]
                  );
                }
              }
              />
            </View>

              <CarInformation title="Model" value={car.model}/>
              <CarInformation title="BatteryCapacity" value={`${car.batteryCapacity}/kwh`}/>
              <CarInformation title="Plate" value={car.plate}/>
                </View>

          </View> 
        ))
        ) : (
          <Text>NO cars loaded</Text>
          )}
      </View>



      <View style={localStyles.buttonContainer}>
        <CustomLongButton
          title="Add car"
          onPress={() => navigation.navigate("AddCarScreen")}
          />
      </View>
          </ScrollView>


    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },
  sectionContainer: {
  flex: 9,
  padding: 10,
  },
  buttonContainer: {
    flex: 1,
    
  },
  informationContainer: {
    marginBottom: 10,
    borderWidth: 1,
    borderRadius: 10,
    backgroundColor: "#f9f9f9",
  },

  sectionHeader: {
    backgroundColor: '#333333',
    borderTopLeftRadius: 9,
    borderTopRightRadius: 9,
    flexDirection: 'row',
    justifyContent:'space-between',
    alignItems: 'center',
  },
  headerText: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 20,
    fontWeight: 'bold',
    marginLeft: 10,
    marginBottom: 5,
    color: '#fefefe',
  },
});
