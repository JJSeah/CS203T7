import { useContext, useState } from "react";
import { Alert } from "react-native"
import { CardRepository } from "../model/CardRepository";
import { UserContext } from "../model/User";


export default DeleteCarViewController = () => {
    
    const { setUserCards, userCards, allAppointments } = useContext(UserContext);
    const { deleteCard } = CardRepository();

    const ongoingOrActiveAppointmentsPresent = () => {
        const unfinished = allAppointments.filter((appt) => {return (appt.status !== "completed" && appt.status !== "cancelled")})
        return unfinished.length > 0;
    }

    const deleteCardButtonPressed = (id) => {
        if (userCards.length === 1 && ongoingOrActiveAppointmentsPresent()) {
            Alert.alert(
                "Unable to delete card",
                "You have incomplete bookings",
                {
                  text: "Got it",
                  onPress: () => {}
                }
              )
        } else {
            setUserCards(null)
            deleteCard(id);
        }
       
    }

    return {
        deleteCardButtonPressed,
    };
}