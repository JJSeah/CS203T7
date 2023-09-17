
export default SettingsScreenViewController = ( { navigation } ) => {

    const profileButtonPressed = () => {
        navigation.navigate("ProfileScreen")
    }

    const vehicleInformationButtonPressed = () => {
        navigation.navigate("VehicleInformationScreen")
    }

    const paymentMethodsButtonPressed = () => {
        navigation.navigate("PaymentMethodsScreen")
    }

    const notificationButtonPressed = () => {
        navigation.navigate("NotificationScreen")
    }

    const getHelpButtonPressed = () => {
        navigation.navigate("GetHelpScreen")
    }

    const privacyPolicyButtonPressed = () => {
        navigation.navigate("PrivacyPolicyScreen")
    }

    const aboutButtonPressed = () => {
        navigation.navigate("AboutScreen")
    }

    const editProfileButtonPressed = () => {
        navigation.navigate("EditProfileScreen")
    }

    return {
        profileButtonPressed,
        vehicleInformationButtonPressed,
        paymentMethodsButtonPressed,
        notificationButtonPressed,
        getHelpButtonPressed,
        privacyPolicyButtonPressed,
        aboutButtonPressed,
        editProfileButtonPressed,
    };
}