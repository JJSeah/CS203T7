import React, { useContext } from "react";
import { Text, View, StyleSheet } from "react-native";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";
import { useRoute } from "@react-navigation/native";

export default PaymentScreen = ({navigation}) => {
  const { userCards, userCars } = useContext(UserContext);
  const route = useRoute()
  const apptId = route.params?.apptId

  return (
    <SafeAreaView style={localStyles.safeArea}>
      <View>
        <Text>
          {apptId}
        </Text>
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
