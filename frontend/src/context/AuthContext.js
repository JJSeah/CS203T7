import React, { createContext, useState } from 'react';
// import EncryptedStorage from 'react-native-encrypted-storage';
import AsyncStorage from '@react-native-async-storage/async-storage';

const tokenString = "userToken"

export const AuthContext = createContext();

export const AuthProvider = ( { children } ) => {

    const [isLoading, setIsLoading] = useState(false);
    const [userToken, setUserToken] = useState(null);

    const login = () => {
        setIsLoading(true); 
        setUserToken("leong");    
        AsyncStorage.setItem(tokenString, "leong");
        // EncryptedStorage.setItem(tokenString, "leong")
        setIsLoading(false);
    }

    const logout = () => {
        setIsLoading(true);
        AsyncStorage.removeItem(tokenString);
        // EncryptedStorage.removeItem(tokenString);
        setUserToken(null);
    }

    const isLoggedIn = async() => {
        try {
            setIsLoading(true);
            // let userToken = await EncryptedStorage.getItem(tokenString);
            let userToken = await AsyncStorage.getItem(tokenString);
            setUserToken(userToken);
            setIsLoading(false);
        } catch (e) {
            console.log("isLoggedIn error ${e}")
        }
    }


    return (
        <AuthContext.Provider value = { {login, logout, isLoading, userToken} }>
            {children}
        </AuthContext.Provider>
    );
}
 