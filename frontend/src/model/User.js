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


    return (
        <UserContext.Provider 
            value={{ userToken, logIn, logOut }}
        >
            { children }
        </UserContext.Provider>
    );
}