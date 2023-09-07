import { useState } from "react";

export default LogInViewController = ( { navigation } ) => {
        
    const [ email, setEmail ] = useState("");
    const [ password, setPassword ] = useState("");
    const [ isLoading, setIsLoading ] = useState(false);

    const logInButtonPressed = () => {
        console.log("Log in button pressed")
        navigation.navigate("HomeNavigator");
    }
    
    const registerButtonPressed = () => {
        console.log("Register button pressed")
        navigation.navigate("RegisterScreen");
    }

    const forgotPasswordButtonPressed = () => {
        console.log("Forgot password button pressed")
    }

    return {
        setEmail,
        setPassword,
        logInButtonPressed,
        registerButtonPressed,
        forgotPasswordButtonPressed
    };

}