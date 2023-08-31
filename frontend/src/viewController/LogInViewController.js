import React, { useState } from 'react' 
import LogInViewModel from '../viewModels/LogInViewModel'

export default LogInViewController = ( { navigation } ) => {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [securePasswordEntry, setSecurePasswordEntry] = useState(true)

    const {
        lol
    } = LogInViewModel()


    const logInUser = () => {
        navigation.navigate("TabNavigator");
    }

    return {
        setEmail,
        setPassword,
        logInUser,
        securePasswordEntry,
        setSecurePasswordEntry
    }

}