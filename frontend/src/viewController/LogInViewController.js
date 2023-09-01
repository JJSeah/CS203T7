import React, { useEffect, useState } from 'react' 
import LogInViewModel from '../viewModels/LogInViewModel'

export default LogInViewController = ( { navigation } ) => {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [securePasswordEntry, setSecurePasswordEntry] = useState(true)

    useEffect(() => {

    });
    // const {
    //     lol
    // } = LogInViewModel()

    const logInButtonPressed = () => {
        navigation.navigate("TabNavigator");
    }

    const resetFields = () => {
    }

    return {
        email,
        setEmail,
        setPassword,
        logInButtonPressed,
        securePasswordEntry,
        setSecurePasswordEntry,
        resetFields
    }

}