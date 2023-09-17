import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";


export default RegisterViewController = ( { navigation } ) => {
        
    // const [ isLoading, setIsLoading ] = useState(true);
    const { signUp } = useContext(UserContext);

    const signUpButtonPressed = () => {
        console.log("Sign Up button pressed");
        signUp(firstName, lastName, username, email, password);
    }
    

    return {
        // isLoading,
        signUpButtonPressed,
    };

}