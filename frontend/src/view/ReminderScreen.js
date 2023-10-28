import React, { useContext } from "react";
import { Text, View, StyleSheet } from "react-native";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";

export default ReminderScreen = () => {
  const { userCards, userCars } = useContext(UserContext);
  return (
    <SafeAreaView style={localStyles.safeArea}>
      <View style={localStyles.container}>
        {userCards.length === 0 ? (
          <View style={localStyles.container}>
            <Text>Please add a card</Text>
          </View>
        ) : (
          <></>
        )}

        {userCars.length === 0 ? (
          <View style={localStyles.container}>
            <Text>Please add a car</Text>
          </View>
        ) : (
          <></>
        )}
      </View>
    </SafeAreaView>
  );
};

const localStyles = StyleSheet.create({
  safeArea: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
  container: {
    flex: 1,
    alignItems: "center",
  },
});
