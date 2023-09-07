import React, { createContext, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ( { children } ) => {

    const [ userToken, setUserToken ] = useState(null);


    return (
        <UserContext.Provider>
            {children}
        </UserContext.Provider>
    );
}