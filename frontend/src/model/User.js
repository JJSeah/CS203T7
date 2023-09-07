import React, { createContext, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ( { children } ) => {

    const [ userToken, setUserToken ] = useState(null);

    const logInUser = () => {
        // setUserToken("token")
    }

    const logOutUser = () => {
        setUserToken(null);
    }


    return (
        <UserContext.Provider 
            value={{ userToken, logInUser }}
        >
            { children }
        </UserContext.Provider>
    );
}