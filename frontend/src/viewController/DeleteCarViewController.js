import { useContext, useState } from "react";
import { CarRepository } from "../model/CarRepository";
import { UserContext } from "../model/User";
import { Alert } from "react-native";


export default DeleteCarViewController = () => {
    
    const { userCars, setUserCars, allAppointments } = useContext(UserContext);
    const { deleteCar } = CarRepository();


    const carIsInOngoingOrActiveAppt = ( appts, id) => {
        const list = appts.filter((appt) => { return appt.car.id === id} )
        return list.length !== 0
    }
    const deleteCarButtonPressed = (id) => {

        const ongoingOrActiveAppt = allAppointments.filter((appt) => { return appt.status === "ongoing" || appt.status === "Active" })

        if (carIsInOngoingOrActiveAppt(ongoingOrActiveAppt, id)){
            Alert.alert("You cannot delete this car now",
            "Please check your activity screen",
            )
            return;
        }

        setUserCars(null)
        deleteCar(id);
    }

    return {
        deleteCarButtonPressed,
    };
}

