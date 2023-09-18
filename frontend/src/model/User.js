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
    
    const [ isSuccessful, setIsSuccessful ] = useState(false);

    

    const signUp = (name, email) => {
        axios.post("url", {
            name,
            email,
        })
        .then (res => {

        })
        .catch ( e => {

        })
    }
    
    useEffect(() => {

        if (userToken === null && userId === null) {
            isLoggedIn();
        }

        if (userToken !== null && userId !== null) {
            loadUserData(userId)
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

    const loadUserData = (id) => {
        axios.get(`${BASE_URL}/api/user/${id}`, {

        }, 
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
            // console.log(userCars);
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

    // Edit profile
    // const updateProfile = (firstName, surname, email) => {
    //     let url = `${BASE_URL}/api/user/{id}`

    //     axios.put(url, {
    //         firstname,
    //         surname,
    //         email,
    //     })
    //     .then( res => {
    //         let data = res.data;

    //         let firstName = userData.firstName;
    //         let surname = userData.surname;
    //         Let email = userData.email;

    //         setFirstName(firstName);
    //         setSurname(surname);
    //         setEmail(email);

    //     })
    // }


    return (
        <UserContext.Provider 
            value={{ userToken, userId, userData, userCars, logIn, logOut }}
        >
            { children }
        </UserContext.Provider>
    );
}