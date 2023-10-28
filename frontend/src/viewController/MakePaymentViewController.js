import { useContext, useEffect, useState } from "react";
import { CardRepository } from "../model/CardRepository";
import { UserContext } from "../model/User";
import { BASE_URL } from "../constants/Config";
import axios from "axios";

export default MakePaymentViewController = ({ navigation }) => {
  const [paymentCard, setPaymentCard] = useState({ id: -1 });
  const { userCards, loadAllAppointments, userToken } = useContext(UserContext);

  useEffect(() => {
    if (userCards.length > 0) {
      setPaymentCard(userCards[0]);
    }
  }, []);

  const makePayment = async (apptId, paymentCardId) => {
    let url = `${BASE_URL}/api/appointment/completed/${apptId}/${paymentCardId}`;

    axios
      .put(url, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        console.log(res)
        loadAllAppointments();
        navigation.navigate("HomeNavigator");
      })
      .catch((e) => {
        console.log(`failed to make payment: ${e}`);
      });
  };

  return {
    paymentCard,
    setPaymentCard,
    makePayment,
  };
};
