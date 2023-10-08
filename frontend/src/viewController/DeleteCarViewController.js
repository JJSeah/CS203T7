import { useContext, useState } from "react";
import { CarRepository } from "../model/CarRepository";
import { CarModelsData } from "../constants/CarModels";
import { UserContext } from "../model/User";


export default DeleteCarViewController = () => {

    const { deleteCar } = CarRepository();
    const deleteCarButtonPressed = (id) => {
        deleteCar(id);
    }

    return {
        deleteCarButtonPressed,
    };
}

