import { useContext, useState } from "react";
import { UserContext } from "../model/User";


export default RegisterViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(false);
    const { signUp } = useContext(UserContext);

    const signUpButtonPressed = (firstName, lastName, username, email, password) => {
        console.log("Sign Up button pressed");
        signUp(firstName, lastName, username, email, password);
        navigation.pop();
    }  

    return {
        isReady, 
        setIsReady,
        signUpButtonPressed,
    };
}