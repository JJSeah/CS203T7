import { useContext, useState } from "react";
import { CarRepository } from "../model/CarRepository";
import { CarModelsData } from "../constants/CarModels";
import { UserContext } from "../model/User";
import { Picker } from "@react-native-picker/picker";

export default AddCarScreenViewController = ( { navigation } ) => {

    const { addCarToBackend } = CarRepository();

    const [ nickname, setNickname ] = useState("")
    const [ model, setModel ] = useState("")
    const [ chargingRate, setChargingRate ] = useState(0)
    const [ batteryPercentage, setBatteryPercentage ] = useState(0)
    const [ batteryCapacity, setBatteryCapacity ] = useState(0)
    const [ carPlate, setCarPlate ] = useState("")
    const [ selectedCar, setSelectedCar] = useState(null)
    const [ carPlateMessage, setCarPlateMessage ] = useState("")
    const [ validInput, setValidInput ] = useState(false)


    const clearAllFieldsPressed = () => {
        setNickname("")
        setModel("")
        setChargingRate(0)
        setBatteryPercentage(100)
        setBatteryCapacity(80)
        setCarPlate("")
    }

    const isSingaporeCarPlateNumber = (input) => {
        const pattern = /^[A-Z]{2,3} \d{1,4}[A-Z]{1}$/;
        return pattern.test(input);
      };
    
    const addCarButtonPressed = () => {
        const newCar = {
            "nickname": nickname,
            "model": model,
            "plate": carPlate,
            "chargingRate": chargingRate,
            "batteryPercentage": batteryPercentage,
            "batteryCapacity": batteryCapacity
        }
        addCarToBackend(newCar, { navigation } );
        // navigation.pop();
    }

    const dropdownSelectListPressed = (model) => {
        const selectedCar = CarModelsData.find(car => model == car.model)
        setModel(selectedCar.model)
        setChargingRate(selectedCar.chargingRate)
        setBatteryCapacity(selectedCar.batteryCapacity)
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
        setBatteryPercentage,
        carPlate,
        setCarPlate,
        addCarButtonPressed,
        clearAllFieldsPressed,
        CarModelsData,
        dropdownSelectListPressed,
        isSingaporeCarPlateNumber,
        validInput, setValidInput,
        carPlateMessage, setCarPlateMessage
    };

}