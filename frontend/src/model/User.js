import React, { createContext, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ( { children } ) => {

    const [ userToken, setUserToken ] = useState(null);

    const logIn = () => {
        setUserToken("token")
    }

    const logOut = () => {
        setUserToken(null);
    }

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