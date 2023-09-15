import { useState } from "react";
import { CarRepository } from "../model/CarRepository";

export default AddCarScreenViewController = ( { navigation } ) => {

    const { addCarToBackend } = CarRepository();

    const [ nickname, setNickname ] = useState("")
    const [ model, setModel ] = useState("")
    const [ chargingRate, setChargingRate ] = useState(100)
    const [ batteryPercentage, setBatteryPercentage ] = useState(100)
    const [ batteryCapacity, setBatteryCapacity ] = useState(100)
    const [ carPlate, setCarPlate ] = useState("")

    const clearAllFieldsPressed = () => {
        setNickname("")
        setModel("")
        setChargingRate(0)
        setBatteryPercentage(100)
        setBatteryCapacity(80)
        setCarPlate("")
    }
    
    const addCarButtonPressed = () => {
        addCarToBackend();
        navigation.pop();
    }
    
    return {
        nickname,
        setNickname,
        model,
        setModel,
        chargingRate,
        setChargingRate,
        batteryCapacity,
        setBatteryCapacity,
        batteryPercentage,
        setBatteryCapacity,
        carPlate,
        setCarPlate,
        addCarButtonPressed,
        clearAllFieldsPressed
    };

}