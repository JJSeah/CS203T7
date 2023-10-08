import { useContext, useEffect, useState } from "react";
import { UserContext } from "../model/User";

export default EditProfileViewController = ( { navigation } ) => {

    const { updateProfile } = useContext(UserContext);

    const editProfileButtonPressed = (newFirstName, newLastName, newEmail) => {
        console.log("Edit Profile button pressed");
        updateProfile(newFirstName, newLastName, newEmail);
        navigation.pop();
    }

    return {
        editProfileButtonPressed;
    };

}