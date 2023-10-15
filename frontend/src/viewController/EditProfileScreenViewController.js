import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";

export default EditProfileViewController = ( { navigation } ) => {
    const [ isReady, setIsReady ] = useState(false);
    const { updateProfile } = useContext(UserContext);

    const confirmEditProfileButtonPressed = (newFirstName, newLastName, newEmail, id) => {
        updateProfile(newFirstName, newLastName, newEmail, id);
        navigation.pop();
    }

    return {
        isReady,
        setIsReady,
        confirmEditProfileButtonPressed,
    };

}