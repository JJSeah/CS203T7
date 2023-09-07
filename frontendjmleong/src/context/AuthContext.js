import React, { createContext, useState } from 'react';
import EncryptedStorage from 'react-native-encrypted-storage';
import { BASE_URL } from '../config';
import axios from 'axios';

const tokenString = "userToken"
const userString = "userInfo"


export const AuthContext = createContext();

export const AuthProvider = ( { children } ) => {

    const [isLoading, setIsLoading] = useState(false);
    const [userToken, setUserToken] = useState(null);
    const [userInfo, setUserInfo] = useState(null);
    
    const [issue, setIssue] = useState("");
    const [message, setMessage] = useState("");
    
    const login = (email, password) => {
        setIsLoading(true); 
        axios.post(`${BASE_URL}`, {
            email, 
            password
        })
        .then(res => {
            let userInfo = res.data;
            setUserInfo(userInfo);
            setUserToken(userInfo.data.token);
                
            EncryptedStorage.setItem(userString, JSON.stringify(userInfo));
            EncryptedStorage.setItem(tokenString, userInfo.data.token);
                
            console.log(userInfo);
            console.log('User token: ' + userInfo.data.token)
        }).catch(e => {
            console.log("Login ")
        })
                    
        let userInfo = fakeUser.data;
        setUserInfo(userInfo);
        setUserToken("fakeToken");    
                    
        EncryptedStorage.setItem(userString, JSON.stringify(userInfo));
        EncryptedStorage.setItem(tokenString, userInfo.token);
                    
        console.log(JSON.stringify(userInfo))
                    
        setIsLoading(false);
    }
        
                
    const logout = () => {
        // setIsLoading(true);
        EncryptedStorage.removeItem(userString);
        EncryptedStorage.removeItem(tokenString);
        setUserToken(null);
    }
                
    const isLoggedIn = async() => {
        try {
            setIsLoading(true);
            let userToken = await EncryptedStorage.getItem(tokenString);
            let userInfo = await EncryptedStorage.getItem(userString);
                        
            userInfo = JSON.parse(userInfo)
                        
            if ( userInfo ) {
                setUserToken(userToken);
                setUserInfo(userInfo);
            }
            setIsLoading(false);
            
        } catch (e) {
            console.log(`isLoggedIn error ${e}`)
        }
    }
    
    return (
        <AuthContext.Provider value = { {login, logout, isLoading, userToken, userInfo } }>
            {children}
        </AuthContext.Provider>
    );
}

const fakeCars = [
    {
        "id": 1111,
        "nickname": "Car One",
        "model": "Tesla Model S",
        "chargingRate": 1000,
        "batteryPercentage" : 100,
        "batteryCapacity" : 10000,
        "carPlate": "SMU123"
    },
    {
        "id": 2222,
        "nickname": "Car Two",
        "model": "Tesla Model Y",
        "chargingRate": 1500,
        "batteryPercentage" : 80,
        "batteryCapacity" : 20000,
        "carPlate": "SMU456"
    }
]

const fakeUser = {
    "data": {
        "id": 1234,
        "firstName": "Zhe Cheng",
        "lastName": "Leong",
        "username": "ahleong_zc",
        "email": "zcleong.2022@scis.smu.edu.sg",
        "password": "ilovesmu",
        "cars": fakeCars,
        "paymentMethod": null,
    }
}
 