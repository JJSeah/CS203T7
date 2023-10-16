import { useContext, useState } from "react";
import { CardRepository } from "../model/CardRepository";

export default AddCardViewController = ( { navigation }) => {
    const { addCardToBackend } = CardRepository();

    const [ name, setName ] = useState("")
    const [ number, setNumber ] = useState(0)
    const [ expiry, setExpiry ] = useState(null)

    const addCardButtonPressed = () => {
        const expiryDateString =
      ("0" + (startTime.getMonth() + 1)).slice(-2) + "-" +
      ("0" + startTime.getDate()).slice(-2);

        const newCard = {
            "name" : name,
            "number": number,
            "expiry": expiryDateString,
        }
        addCardToBackend(newCard, { navigation } );
    }

    return {
        name, setName,
        number, setNumber,
        expiry, setExpiry,
        addCardButtonPressed,
    };
}