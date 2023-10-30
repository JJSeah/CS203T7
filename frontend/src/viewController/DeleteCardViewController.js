import { useContext, useState } from "react";
import { CardRepository } from "../model/CardRepository";
import { UserContext } from "../model/User";


export default DeleteCarViewController = () => {
    
    const { setUserCards } = useContext(UserContext);
    const { deleteCard } = CardRepository();

    const deleteCardButtonPressed = (id) => {
        setUserCards(null)
        deleteCard(id);
       
    }

    return {
        deleteCardButtonPressed,
    };
}