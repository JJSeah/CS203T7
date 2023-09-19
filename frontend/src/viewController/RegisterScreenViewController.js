import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";


export default RegisterViewController = ( { navigation } ) => {
        
    // const [ isLoading, setIsLoading ] = useState(true);
    const { signUp } = useContext(UserContext);

    const signUpButtonPressed = (firstName, lastName, username, email, password) => {
        console.log("Sign Up button pressed");
        signUp(firstName, lastName, username, email, password);
        navigation.pop();
    }  

    return {
        // isLoading,
        signUpButtonPressed,
    };

}