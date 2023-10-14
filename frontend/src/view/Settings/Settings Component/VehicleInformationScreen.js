import React, { useContext } from "react";
import {
  StyleSheet,
  Text,
  View,
  ActivityIndicator,
  SafeAreaView,
  Alert,
} from "react-native";
import { UserContext } from "../../../model/User";
import CarInformation from "../../../components/CarInformation";
import CustomLongButton from "../../../components/CustomLongButton";
import AddCarScreen from "../../AddCarScreen";
import DeleteCarViewController from "../../../viewController/DeleteCarViewController";
import AddCarScreenViewController from "../../../viewController/AddCarScreenViewController";

export default VehicleInformationScreen = ( {navigation} ) => {
  const { userData, userCars, setCurrentCar, currentCar } =
    useContext(UserContext);

  const { deleteCarButtonPressed } = DeleteCarViewController();

  return (
    <SafeAreaView style={localStyles.container}>

      <View style={{flex:9}}>
      {userCars === null ? (
        <View>
          <ActivityIndicator />
        </View>
      ) : Array.isArray(userCars) && userCars.length > 0 ? (
        userCars.map((car) => (
          <View key={car.id} style={localStyles.sectionContainer}>
            <View style={localStyles.informationContainer}>
              <CarInformation title="Car Nickname" value={car.nickname} />
              <CarInformation
                title="BatteryCapacity"
                value={car.batteryCapacity}
              />
              <CarInformation title="Model" value={car.model} />
              <CarInformation title="Plate" value={car.plate} />
            </View>

            <CustomLongButton
              title="delete car"
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
              }}
            />
          </View>
        ))
      ) : (
        <Text>NO cars loaded</Text>
      )}
      </View>

      <View style={{flex:1}}>
        <CustomLongButton
          title="Add car"
          onPress={() => navigation.navigate("AddCarScreen")}
        />

      </View>


    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#141414",
  },
  sectionContainer: {
    marginBottom: 24,
    borderWidth: 1,
    borderColor: "#ccc",
    backgroundColor: "#f9f9",
  },
  informationContainer: {},
  buttonContainer: {},
});
