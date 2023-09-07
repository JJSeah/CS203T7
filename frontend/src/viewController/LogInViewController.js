import React, { useContext, useState } from 'react' 
import { AuthContext } from '../context/AuthContext'

export default LogInViewController = ( { navigation } ) => {
    
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [securePasswordEntry, setSecurePasswordEntry] = useState(true)

    const { login } = useContext(AuthContext)


    const logInButtonPressed = () => {
        login()
    }

    const resetFields = () => {
    }

    return {
        email, 
        setEmail, 
        password, 
        setPassword,
        logInButtonPressed, 
        securePasswordEntry,
        logInButtonPressed
    };

}

