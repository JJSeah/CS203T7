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
    const [ userCards, setUserCards ] = useState([]);

    const [ currentCar, setCurrentCar ] = useState(null);

    const [ allStations, setAllStations ] = useState([]);
    const [ userCoordinates, setUserCoordinates ] = useState (null);
    const [ closestStation, setClosestStation ] = useState(null);
    const [ upcomingAppointmentDetails, setUpcomingAppointmentDetails ] = useState(null);
    const [ currentAppointment, setCurrentAppointment ] = useState(null);

    const [ allAppointments, setAllAppointments ] = useState([]);
    
    const [ isSuccessful, setIsSuccessful ] = useState(false);
    
    const signUp = async(firstName, lastName, username, email, password) => {
        let url = `${BASE_URL}/auth/signup`;

        axios.post(url, {
            "firstName": firstName,
            "lastName": lastName, 
            "usernames": username,
            "email": email,
            "password" : password
        })
        .then (res => { 
        })
        .catch (e => {
            console.log(`Sign up error ${e}`)
        })
    }

    const getAllAppointments = async() => {
        let url = `${BASE_URL}/api/appointment/user/${userId}`

        axios.get(url)
        .then (res => { 
            let data = res.data
            setAllAppointments(data)
        })
        .catch (e => {
            console.log(`Get all appointment error ${e}`)
        })
    }

    // const confirmAppointment = async() => {

    // }
    
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
        })
        .catch(e => {
            console.log(`Log in error ${e}`)
        })

    }

    const checkPassword = async(email, password) => {

        let url = `${BASE_URL}/auth/login`

        axios.post(url, {
            email, 
            password 
        })
        .then(() => {
            changePassword(password);
        }
        )
        .catch(e => {
            console.log(`Password doesn't match ${e}`)
        })

    }

    const changePassword = async(password) => {
        let url = `${BASE_URL}/api/user/${id}`

        axios.put(url, {
            "password": password
        })
        .then(() => {
            console.log("Successfully change password in backend")
            loadUserData()
        }).catch((e) => {
            console.log(`Error changing password ${e}`);
        });
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
            let userCards = data.card

            console.log(userCards)

            if (userCars.length > 0) {
                setCurrentCar(userCars[0])
            }
            setUserData(userData)
            setUserCars(userCars)
            setUserCards(userCards)
            getAllAppointments()
            
            getAllStations()
            return true
        })
        .catch(e => {
            console.log(`Load user data error ${e}`)
        })

    }

    const logOut = () => {
        setUserToken(null);
        setUserId(null);
        setUserData(null);
        setCurrentCar(null);

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

    const updateProfile = async(newFirstName, newLastName, newUsername, id) => {
        let url = `${BASE_URL}/api/user/${id}`

        axios.put(url, {
            "firstName": newFirstName,
            "lastName": newLastName,
            "usernames": newUsername,
        })
        .then(() => {
            console.log("Successfully updated profile in backend")
            loadUserData()
        }).catch((e) => {
            console.log(`Error updating profile ${e}`);
        });
    }


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
            value={{ userToken, userId, userData, userCars, userCards, allStations, 
                logIn, logOut, signUp, setUserCars, setUserCards,
                userCoordinates, setUserCoordinates,
                closestStation, setClosestStation,
                upcomingAppointmentDetails, setUpcomingAppointmentDetails,
                currentAppointment, setCurrentAppointment,
                currentCar, setCurrentCar, updateProfile, checkPassword, allAppointments, getAllAppointments
            }}
        >
            { children }
        </UserContext.Provider>
    );
}