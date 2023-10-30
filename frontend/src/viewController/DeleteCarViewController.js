import { useContext, useState } from "react";
import { CarRepository } from "../model/CarRepository";
import { UserContext } from "../model/User";


export default DeleteCarViewController = () => {
    
    const { setUserCars } = useContext(UserContext);
    const { deleteCar } = CarRepository();

    const deleteCarButtonPressed = (id) => {
        setUserCars(null)
        deleteCar(id);
       
    }

    return {
        deleteCarButtonPressed,
    };
}

