import React, { useContext, useState } from 'react' 
import { AuthContext } from '../context/AuthContext'

export default SettignsViewController = () => {

    const { logout } = useContext(AuthContext)

    const logOutButtonPressed = () => {
        logout();
    }

    return {
        logOutButtonPressed,
    }
}
