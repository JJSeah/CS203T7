import React, { useContext } from "react";

import axios from "axios";
import { BASE_URL } from "../constants/Config";
import { UserContext } from "./User";

export const CardRepository = () => {
  const { userToken, userId, setUserCards } = useContext(UserContext);

  const addCardToBackend = async (newCard, userId, { navigation }) => {
    let url = `${BASE_URL}/api/card/add/${userId}`;

    axios
      .post(
        url,
        {
          name: newCard.name,
          number: newCard.number,
          expiry: newCard.expiry,
        },
        {
          headers: { Authorization: `Bearer ${userToken}` },
        }
      )
      .then((res) => {
        loadCardData();
        console.log(res.data);
        navigation.pop();
      })
      .catch((e) => {
        console.log(`Error adding card to back end ${e}`);
      });
  };

  const loadCardData = async () => {
    let url = `${BASE_URL}/api/card/user/${userId}`;

    setUserCard(null);

    axios
      .get(url)
      .then((res) => {
        let data = res.data;
        setUserCards(data);
      })
      .catch((e) => {
        console.log(`Error adding card to back end ${e}`);
      });
  };

  return { addCardToBackend, loadCardData };
};
