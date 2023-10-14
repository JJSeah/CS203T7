import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";

export default EditProfileViewController = ( { navigation } ) => {

    const { updateProfile } = useContext(UserContext);

    const confirmEditProfileButtonPressed = (newFirstName, newLastName, newEmail) => {
        updateProfile(newFirstName, newLastName, newEmail);
        navigation.pop();
    }

    return {
        confirmEditProfileButtonPressed;
    };

}