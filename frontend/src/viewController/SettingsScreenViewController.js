
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

    const billingHistoryButtonPressed = () => {
        navigation.navigate("BillingHistoryScreen")
    }

    const chargingHistoryButtonPressed = () => {
        navigation.navigate("ChargingHistoryScreen")
    }

    return {
        profileButtonPressed,
        vehicleInformationButtonPressed,
        paymentMethodsButtonPressed,
        notificationButtonPressed,
        billingHistoryButtonPressed,
        chargingHistoryButtonPressed,
    };
}