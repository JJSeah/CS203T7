import React, { useContext, useEffect } from "react";
import {
  Text,
  View,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  ActivityIndicator
} from "react-native";
import { UserContext } from "../model/User";
import { SafeAreaView } from "react-native-safe-area-context";
import { useRoute } from "@react-navigation/native";
import CustomLongButton from "../components/CustomLongButton";
import MakePaymentViewController from "../viewController/MakePaymentViewController";
import { Ionicons } from "@expo/vector-icons";

export default PaymentScreen = ({ navigation }) => {
  const { userCards, userCars } = useContext(UserContext);
  const { paymentCard, checkPaymentAmount, amount, setPaymentCard, makePayment } =
    MakePaymentViewController({
      navigation,
    });
  const route = useRoute();
  const appt = route.params;
  const station = route.params.station;

  useEffect(() => {
    checkPaymentAmount(appt.id)
  }, [])

  return (
    <SafeAreaView style={localStyles.safeArea}>
      <View style={localStyles.topContainer}>
        <View>
          <Text>appointment id is{appt.id}</Text>
        </View>

        <View>
          <Text>For car {appt.car.nickname}</Text>
        </View>

        <View>
          {amount === null ?
          (
            <View>
              <ActivityIndicator/>
            </View>
          ):
            <View>
              <Text>
                The total cost is ${amount}
              </Text>
            </View>
          }
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
          disabled={amount === null}
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
