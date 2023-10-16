import React, { createContext, useContext, useEffect, useState } from "react";

import axios from "axios";
import { BASE_URL } from "../constants/Config";
import { UserContext } from "./User";

export const CardRepository = () => {
    const{userToken, userId, userCards, setUserCards, setCurrentCard } = 
    useContext(UserContext);


const addCardToBackend = async(newCard, { navigation }) => {
    let url = `${BASE_URL}/api/card`;

    axios.post(url, {
        name: newCard.name,
        number: newCard.number,
        expiry: newCard.expiry,
        }, 
        {
        headers: { Authorization: `Bearer ${userToken}` },
        }
        )
        .then((res) => {
            loadCardsData()
            console.log(res.data)
            navigation.pop()
        })
        .catch((e) => {
            console.log(`Error adding card to back end ${e}`);
        });
    }

    const loadCardsData = async() => {
        let url = `${BASE_URL}/api/card/user/${userId}`;
        
        setUserCards(null)

        axios.get(url)
        .then((res) => {
            let data = res.data;

            data = data.reverse();
            setUserCards(data);

            if (data.length > 0) {
                setCurrentCard(data[0])
            } else {
                setCurrentCard(null)
            }
        })
        .catch((e) => {
            console.log(`Error adding card to back end ${e}`);
        })
    }

    return { addCardToBackend, loadCardsData };
};