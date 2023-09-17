import React, { createContext, useEffect, useState } from 'react';
import * as SecureStore from "expo-secure-store"
import axios from "axios";
import { BASE_URL } from '../constants/Config';

const userTokenString = "userToken"
const userIdString = "userId"

export const UserContext = createContext();

export const UserProvider = ( { children } ) => {

    const [ userToken, setUserToken ] = useState(null);
    const [ userId, setUserId ] = useState(null);
    const [ userData, setUserData ] = useState(null);
    const [ userCars, setUserCars ] = useState([]);
    const [ allStations, setAllStations ] = useState([]);
    
    const [ isSuccessful, setIsSuccessful ] = useState(false);

    const signUp = (firstName, lastName, username, email, password) => {
        let url = `${BASE_URL}/auth/signup`;

        axios.post(url, {
            firstName,
            lastName, 
            username,
            email,
            password
        })
        .then (res => {
            
            console.log('res', res.data)
        })
        .catch (e => {
            console.log(`Sign up error ${e}`)
        })
    }
    
    useEffect(() => {

        if (userToken === null && userId === null) {
            isLoggedIn();
        }

        if (userToken !== null && userId !== null) {
            loadUserData()
            SecureStore.setItemAsync(userTokenString, userToken);
            SecureStore.setItemAsync(userIdString, userId);
        }

    }, [userToken, userId]);

    const logIn = (email, password) => {

        let url = `${BASE_URL}/auth/login`


        axios.post(url, {
            email, 
            password 
        })
        .then( res => {
            let data = res.data;

            let token = data.token;
            let id = data.id;

            setUserToken(token);
            setUserId(JSON.stringify(id));
        })
        .catch(e => {
            console.log(`Log in error ${e}`)
        })

        // SecureStore.setItemAsync(userTokenString, "userTokenTemp")
        // setUserToken("userTokenTemp")
    }

    const loadUserData = () => {
        let url = `${BASE_URL}/api/user/${userId}`

        axios.get(url
        // {
        //     headers: {
        //         'Authorization': `Bearer ${userToken}`
        //     }
        // }
        )
        .then( res => {
            let data = res.data
            let userData = data.user
            let userCars = data.car
            let userCard = data.card

            setUserData(userData)
            setUserCars(userCars)

            getAllStations()

        })
        .catch(e => {
            console.log(`Load user data error ${e}`)
        })

    }


    const logOut = () => {
        setUserToken(null);
        setUserId(null);
        setUserData(null);

        SecureStore.deleteItemAsync(userTokenString);
        SecureStore.deleteItemAsync(userIdString);
    }

    const isLoggedIn = async() => {
        try {
            let token = await SecureStore.getItemAsync(userTokenString);
            let userId = await SecureStore.getItemAsync(userIdString);

            setUserToken(token)
            setUserId(userId)

            if (token !== null && userId !== null) {
                console.log("When app launches, user already logged in")
            } else {
                console.log("User logged out previously")
            }

        } catch (e) {
            console.log(`User is already logged in error ${e}`); 
        }
    }

    const getAllStations = async() => {
        axios.get(`${BASE_URL}/api/stations/all`)
        .then ( res => {
            let data = res.data
            setAllStations(data)
        })
        .catch( e => {
            console.log(`Error loading stations ${e}`)
        })
    }


    return (
        <UserContext.Provider 
            value={{ userToken, userId, userData, userCars, allStations, logIn, logOut, signUp, setUserCars }}
        >
            { children }
        </UserContext.Provider>
    );
}