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

    const [ currentCar, setCurrentCar ] = useState(null);

    const [ allStations, setAllStations ] = useState([]);
    const [ userCoordinates, setUserCoordinates ] = useState (null);
    const [ closestStation, setClosestStation ] = useState(null);
    const [ upcomingAppointment, setUpcomingAppointment ] = useState(null);
    
    const [ isSuccessful, setIsSuccessful ] = useState(false);
    
    const signUp = async(firstName, lastName, username, email, password) => {
        let url = `${BASE_URL}/auth/signup`;

        axios.post(url, {
            "firstName": firstName,
            "lastName": lastName, 
            "username": username,
            "email": email,
            "password" : password
        })
        .then (res => { 
        })
        .catch (e => {
            console.log(`Sign up error ${e}`)
        })
    }
    
    useEffect(() => {

        console.log("initial app")
        if (userToken === null && userId === null) {
            isLoggedIn();
        }

        if (userToken !== null && userId !== null) {
            loadUserData()
            SecureStore.setItemAsync(userTokenString, userToken);
            SecureStore.setItemAsync(userIdString, userId);
        }

    }, [userToken, userId]);

    const logIn = async(email, password) => {

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

            console.log(data)

        })
        .catch(e => {
            console.log(`Log in error ${e}`)
        })

    }


    const loadUserData = async() => {
        let url = `${BASE_URL}/api/user/${userId}`

        axios.get(url,
        {
            headers: {
                'Authorization': `Bearer ${userToken}`
            }
        }
        )
        .then( res => {
            let data = res.data
            let userData = data.user
            let userCars = data.car
            let userCard = data.card

            setUserData(userData)
            setUserCars(userCars)

            if (userCars.length > 0) {
                setCurrentCar(userCars[0])
            } 

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




    // Edit profile
    // const updateProfile = async(newFirstName, newLastName, newEmail) => {
    //     let url = `${BASE_URL}/api/user/${userId}`

    //     axios.put(url, {
    //         "firstName": newFirstName,
    //         "lastName": newLastName,
    //         "email": newEmail,
    //     })
    //     .then( res => {
    //         console.log("Successfully updated backend")

    //         let reload = async() => {
    //             loadUserData()
    //         }

    //         reload()
    //     })
    // }

    const getAllStations = async() => {
        axios.get(`${BASE_URL}/api/stations/all`, 
        {
            headers: {
                'Authorization': `Bearer ${userToken}`
            }
        }
        )
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
            value={{ userToken, userId, userData, userCars, allStations, 
                logIn, logOut, signUp, setUserCars, 
                userCoordinates, setUserCoordinates,
                closestStation, setClosestStation,
                upcomingAppointment, setUpcomingAppointment,
                currentCar, setCurrentCar
            }}
        >
            { children }
        </UserContext.Provider>
    );
}