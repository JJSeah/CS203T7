import React, { useContext } from "react";
import {
  Text,
  View,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
} from "react-native";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";
import { useRoute } from "@react-navigation/native";
import CustomLongButton from "../components/CustomLongButton";
import MakePaymentViewController from "../viewController/MakePaymentViewController";
import { Ionicons } from "@expo/vector-icons";

export default PaymentScreen = ({ navigation }) => {
  const { userCards, userCars } = useContext(UserContext);
  const { paymentCard, setPaymentCard, makePayment } =
    MakePaymentViewController({
      navigation,
    });
  const route = useRoute();
  const appt = route.params;
  const station = route.params.station;

  return (
    <SafeAreaView style={localStyles.safeArea}>
      <View style={localStyles.topContainer}>
        <View>
          <Text>{appt.id}</Text>
          <Text>{appt.id}</Text>

        </View>

        <View>
          <Text>For car {appt.car.nickname}</Text>
        </View>

        <View>
          <ScrollView>
            {userCards.map((card) => (
              <View key={card.id} style={localStyles.cardContainer}>
                <View>
                  <TouchableOpacity
                    onPress={() => {
                      setPaymentCard(card);
                    }}
                  >
                    <Text>{card.id}</Text>
                    <Text>{card.name}</Text>
                    <Text>{card.number}</Text>
                    <Text>{card.expiry.slice(2, 7)}</Text>
                  </TouchableOpacity>
                </View>

                <View>
                  {card.id === paymentCard.id ? (
                    <View>
                      <Ionicons
                        name="checkmark-circle"
                        size={24}
                        color="green"
                      />
                    </View>
                  ) : (
                    <></>
                  )}
                </View>
              </View>
            ))}
          </ScrollView>
        </View>
      </View>

      <View style={localStyles.bottomContainer}>
        <CustomLongButton
          title="Make payment"
          onPress={() => {
            makePayment(appt.id, paymentCard.id);
          }}
        />
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
  topContainer: {
    flex: 9,
    alignItems: "center",
  },
  bottomContainer: {
    alignItems: "center",
    flex: 1,
  },
  cardContainer: {
    flexDirection: "row",
    alignItems: "stretch",
  },
});
