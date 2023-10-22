import { useContext, useState } from "react";
import { CardRepository } from "../model/CardRepository";

export default AddCardViewController = ( { navigation }) => {
    const { addCardToBackend } = CardRepository();
    const [ isReady, setIsReady ] = useState(false);

    const [ name, setName ] = useState("")
    const [ number, setNumber ] = useState(0)
    const [ expiry, setExpiry ] = useState(null)


    const confirmAddCardButtonPressed = async(cardHolderName, cardNumber, cardExpirationDate, id) => {
        const [month, year] = cardExpirationDate.split('/');
        const formattedExpirationDate = '20' + year + '-' + month + '-01';
        const newCard = {
            "name" : cardHolderName,
            "number": cardNumber,
            "expiry": formattedExpirationDate,
        }
        addCardToBackend(newCard, id, { navigation } );
    }

    return {
        isReady, setIsReady,
        name, setName,
        number, setNumber,
        expiry, setExpiry,
        confirmAddCardButtonPressed,
    };
}