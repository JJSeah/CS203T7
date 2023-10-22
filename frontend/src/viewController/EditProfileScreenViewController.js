import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";

export default EditProfileViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(false);
    const { updateProfile } = useContext(UserContext);

    const confirmEditProfileButtonPressed = async(newFirstName, newLastName, newUsername, id) => {
        updateProfile(newFirstName, newLastName, newUsername, id) 
        navigation.pop();
    }

    return {
        isReady,
        setIsReady,
        confirmEditProfileButtonPressed,
    };

}