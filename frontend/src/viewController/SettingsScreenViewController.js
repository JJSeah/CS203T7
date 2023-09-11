
export default SettingsScreenViewController = ( { navigation } ) => {

    const profileButtonPressed = () => {
        navigation.navigate("ProfileScreen")
    }

    const vehicleInformationButtonPressed = () => {
        navigation.navigate("VehicleInformationScreen")
    }

    return {
        profileButtonPressed,
        vehicleInformationButtonPressed
    };

}