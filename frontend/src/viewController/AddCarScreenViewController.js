import { useContext, useState } from "react";
import { CarRepository } from "../model/CarRepository";
import { CarModelsData } from "../constants/CarModels";
import { UserContext } from "../model/User";
import { Picker } from "@react-native-picker/picker";

export default AddCarScreenViewController = ( { navigation } ) => {

    const { addCarToBackend } = CarRepository();

    const [ nickname, setNickname ] = useState("")
    const [ model, setModel ] = useState("")
    const [ chargingRate, setChargingRate ] = useState(100)
    const [ batteryPercentage, setBatteryPercentage ] = useState(0)
    const [ batteryCapacity, setBatteryCapacity ] = useState(100)
    const [ carPlate, setCarPlate ] = useState("")
    const [ selectedCar, setSelectedCar] = useState(null)

    const clearAllFieldsPressed = () => {
        setNickname("")
        setModel("")
        setChargingRate(0)
        setBatteryPercentage(100)
        setBatteryCapacity(80)
        setCarPlate("")
    }
    
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

    const picker = () => {
        return (
            <Picker>
              selectedValue={batteryPercentage}
              onValueChange={(itemValue, itemIndex) => setBatteryPercentage(itemValue)}
              {[...Array(100).keys()].map((value) => {
                <Picker.Item key={value} label={value.toString()} value={value}/>
              })}
            </Picker>
        )
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
        picker
    };

}