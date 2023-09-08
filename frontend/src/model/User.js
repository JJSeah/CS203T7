import React, { createContext, useEffect, useState } from 'react';
import * as SecureStore from "expo-secure-store"
import axios from "axios";
import { LOG_IN_URL, LOAD_USER_DATA_URL } from '../constants/Config';

const userTokenString = "userToken"
const userIdString = "userId"

export const UserContext = createContext();

export const UserProvider = ( { children } ) => {

    const [ userToken, setUserToken ] = useState(null);
    const [ userId, setUserId ] = useState(null);
    const [ userInfo, setUserInfo ] = useState(null);
    const [ isSuccessful, setIsSuccessful ] = useState(false);

    const logIn = (email, password) => {
        // axios.post(LOG_IN_URL, {
        //     email, 
        //     password 
        // })
        // .then( res => {
        //     let data = res.data;

        //     let token = data.token;
        //     setUserToken(token);

        //     // setUserId(id);

        //     SecureStore.setItemAsync(userTokenString, token);
            
        //     // SecureStore.setItemAsync(userIdString, JSON.stringify(userId));
        // })
        // .catch(e => {
        //     console.log(`Log in error ${e}`)
        // })

        // // loadUserData(userId)

        SecureStore.setItemAsync(userTokenString, "userTokenTemp")
        setUserToken("userTokenTemp")

    }

    const loadUserData = (id) => {
        axios.get(`${LOAD_USER_DATA_URL}/${id}`)
        .then( res => {
            let data = res.data
            console.log(data)
        })
        .catch(e => {
            console.log(`Load user data error ${e}`)
        })
    }

    const logOut = () => {
        setUserToken(null);
        setUserId(null);

        SecureStore.deleteItemAsync(userTokenString);
        SecureStore.deleteItemAsync(userIdString);
    }

    const isLoggedIn = async() => {
        try {
            let token = await SecureStore.getItemAsync(userTokenString);
            // let userId = await SecureStore.getItemAsync(userIdString);

            setUserToken(token)

            if (token !== null) {
                console.log("User already logged in")
            } else {
                console.log("User already logged out before")
            }

            // setUserId(userId)
            // console.log(`This is when app launches${userId}`)
        } catch (e) {
            console.log(`User is already logged in error ${e}`);
        }
    }

    // This is for when the app launches
    useEffect(() => {
        isLoggedIn();
    }, []);

    const registernNewUser = ( { firstName, lastName, username, email, password } ) => {
        let newUser = {
            "firstName": firstName,
            "lastName": lastName,
            "username": username,
            "email": email,
            "password": password
        }
    }

    return (
        <UserContext.Provider 
            value={{ userToken, logIn, logOut }}
        >
            { children }
        </UserContext.Provider>
    );
}