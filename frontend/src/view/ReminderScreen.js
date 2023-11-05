import React, { useContext } from "react";
import { Text, View, StyleSheet, Button } from "react-native";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";
import CustomLongButton from "../components/CustomLongButton";
import { MaterialCommunityIcons } from "@expo/vector-icons";

export default ReminderScreen = ({ navigation }) => {
  const { userCards, userCars } = useContext(UserContext);
  return (
    <SafeAreaView style={localStyles.safeArea}>
      <View style={localStyles.top}>
        <View
          style={{flex: 1, justifyContent: "center"}} 
        >
          <Text
            style={localStyles.label2}
          >Please do the following steps</Text>
        </View>

        <View
          style={{flex: 9}} 
        >
          {userCards.length === 0 ? (
            <View style={localStyles.container}>
              <Text style={localStyles.label}>Add a card</Text>
              <Text style={localStyles.text}>1. Go to Settings</Text>
              <Text style={localStyles.text}>2. Payment Methods</Text>
              <Text style={localStyles.text}>3. Add card </Text>
              <MaterialCommunityIcons
                name="credit-card-chip-outline"
                size={100}
                color="white"
                padding={10}
              />
            </View>
          ) : (
            <></>
          )}

          {userCars.length === 0 ? (
            <View style={localStyles.container}>
              <Text style={localStyles.label}>Add a car</Text>
              <Text style={localStyles.text}>1. Go to Settings</Text>
              <Text style={localStyles.text}>2. Vehicle Information</Text>
              <Text style={localStyles.text}>3. Add car</Text>
              <MaterialCommunityIcons
                name="car-sports"
                size={100}
                color="white"
                padding={10}
              />
            </View>
          ) : (
            <></>
          )}
        </View>
      </View>

      <View style={localStyles.bottom}>
        <View style={{ flex: 1 }}>
          <CustomLongButton
            title="Go back"
            onPress={() => {
              navigation.pop();
            }}
          />
        </View>
      </View>
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  safeArea: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#141414",
  },
  top: {
    flex: 9,
    flexDirection: "column",
  },
  bottom: {
    flex: 1,
    flexDirection: "row",
  },
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center"
  },
  label: {
    color: "white",
    fontSize: 22,
    fontWeight: "bold",
  },
  label2: {
    color: "white",
    textDecorationLine: "underline",
    fontSize: 22,
    fontWeight: "bold",
  },
  label3: {
    textDecorationLine: "underline",
    color: "teal",
    fontSize: 18,
    fontWeight: "bold",
  },
  label4: {
    color: "teal",
    fontSize: 18,
    fontWeight: "bold",
  },
  details: {
    color: "white",
  },
  text: {
    color: "white",
    fontWeight: "bold",
    fontSize: 15,
  },
});
