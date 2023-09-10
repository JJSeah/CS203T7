import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";


export default LogInViewController = ( { navigation } ) => {
        
    const [ email, setEmail ] = useState("ex@example.com");
    const [ password, setPassword ] = useState("mysecretpassword");
    const [ isLoading, setIsLoading ] = useState(true);
    
    const { logIn } = useContext(UserContext);

    const logInButtonPressed = () => {
        console.log("Log in button pressed");
        logIn(email, password);
    }
    
    const makeNewAccountButtonPressed  = () => {
        console.log("Register button pressed")
        navigation.navigate("RegisterScreen");
    }

    const forgotPasswordButtonPressed = () => {
        console.log("Forgot password button pressed")
    }

    return {
        isLoading,
        email, 
        password,
        setEmail,
        setPassword,
        logInButtonPressed,
        makeNewAccountButtonPressed,
        forgotPasswordButtonPressed
    };

}