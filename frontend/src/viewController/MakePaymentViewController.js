import { useContext, useEffect, useState } from "react";
import { CardRepository } from "../model/CardRepository";
import { UserContext } from "../model/User";
import { BASE_URL } from "../constants/Config";
import axios from "axios";

export default MakePaymentViewController = ({ navigation }) => {
  const [ isReady, setIsReady ] = useState(false);      
  const [ paymentCard, setPaymentCard ] = useState({ id: -1 });
  const { userCards, getAllAppointments, userToken } = useContext(UserContext);
  const [ amount, setAmount ] = useState(null);

  useEffect(() => {
    if (userCards.length > 0) {
      setPaymentCard(userCards[0]);
    }
  }, []);

  const checkPaymentAmount = async (apptId) => {
    let url = `${BASE_URL}/api/appointment/cost/${apptId}`;

    axios
      .get(url, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        let data = res.data
        setAmount(data)
      })
      .catch((e) => {
        console.log(`failed to load cost : ${e}`);
      });
  }

  const makePayment = async (apptId, paymentCardId) => {
    let url = `${BASE_URL}/api/appointment/completed/${apptId}/${paymentCardId}`;

    axios
      .put(url, {
        headers: {
          Authorization: `Bearer ${userToken}`,
        },
      })
      .then((res) => {
        getAllAppointments();
        navigation.navigate("HomeNavigator");
      })
      .catch((e) => {
        console.log(`failed to make payment: ${e}`);
      });
  };

  return {
    isReady, 
    setIsReady,
    paymentCard,
    setPaymentCard,
    makePayment,
    checkPaymentAmount,
    amount
  };
};
