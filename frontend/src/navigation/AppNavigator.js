import { useContext } from 'react';
import { UserContext } from '../model/User';

import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LogInScreen from '../view/LogInScreen';
import PaymentScreen from '../view/MakePaymentScreen';
import RegisterScreen from '../view/RegisterScreen';
import HomeNavigator from './HomeNavigator';
import AutomateBookingScreen from '../view/AutomateBookingScreen';
import AddCarScreen from '../view/AddCarScreen';
import ProfileScreen from '../view/Settings/Settings Component/ProfileScreen';
import VehicleInformationScreen from '../view/Settings/Settings Component/VehicleInformationScreen';
import PaymentMethodsScreen from '../view/Settings/Settings Component/PaymentMethodsScreen';
import NotificationScreen from '../view/Settings/Settings Component/NotificationScreen';
import GetHelpScreen from '../view/Settings/Settings Component/GetHelpScreen';
import PrivacyPolicyScreen from '../view/Settings/Settings Component/PrivacyPolicyScreen';
import AboutScreen from '../view/Settings/Settings Component/AboutScreen';
import EditProfileScreen from '../view/Settings/Settings Component/EditProfileScreen';
import ManualBookingScreen from '../view/ManualBooking/ManualBookingScreen';
import UpcomingAppointmentView from '../view/UpcomingAppointmentView';
import SelectStationScreen from '../view/ManualBooking/SelectStationScreen';
import ChargingCarView from '../view/ChargingCarView';
import HistoryScreen from '../view/HistoryScreen';
import AddCardScreen from '../view/Settings/Settings Component/AddCardScreen';
import MakePaymentScreen from '../view/MakePaymentScreen';
import ReminderScreen from '../view/ReminderScreen';

const Stack = createNativeStackNavigator();

export default AppStack = () => {

    const { userData, isSuccessful } = useContext(UserContext);

    return (
        <Stack.Navigator>

            { (userData === null) ? 

                // Onboarding screens
                
                (<Stack.Group>

                    <Stack.Screen
                        name="LogInScreen" 
                        component={LogInScreen}
                        options={{headerShown:false}}
                    />

                    <Stack.Screen
                        name="RegisterScreen"
                        component={RegisterScreen}
                        // options={{headerShown:false}}
                    />

                </Stack.Group>) :


                // Screens for logged in users

                (
                <Stack.Group>
                    
                    <Stack.Screen
                        name="HomeNavigator"
                        component={HomeNavigator}
                        options={{headerShown:false}}
                    />

                    <Stack.Screen
                        name="AutomateBookingScreen"
                        component={AutomateBookingScreen}
                        options={{headerShown:false}}
                    />

                    <Stack.Screen
                        name="ChargingCarView"
                        component={ChargingCarView}
                        options={{headerShown:true}}
                    />

                    <Stack.Screen
                        name="UpcomingAppointmentView"
                        component={UpcomingAppointmentView}
                        options={{headerShown:true}}
                    />

                    <Stack.Screen
                        name="HistoryScreen"
                        component={HistoryScreen}
                        options={{headerShown:true}}
                    />           

                    <Stack.Group
                        screenOptions={{presentation: 'modal'}}
                    >
                        <Stack.Screen name="AddCarScreen"
                            component={AddCarScreen}
                            options={{headerShown:false}}
                        />

                        <Stack.Screen name="SelectStationScreen"
                            component={SelectStationScreen}
                            options={{headerShown:false}}
                        />
                    </Stack.Group>
        
                    <Stack.Screen
                            name="ManualBookingScreen"
                            component={ManualBookingScreen}
                            options={{headerShown:false}}
                    />
        

                    <Stack.Screen
                        name="ProfileScreen"
                        component={ProfileScreen}
                    />

                    <Stack.Screen
                        name="MakePaymentScreen"
                        component={MakePaymentScreen}
                    />

                    <Stack.Screen
                        name="VehicleInformationScreen"
                        component={VehicleInformationScreen}
                    />
                    
                    <Stack.Screen
                        name="PaymentMethodsScreen"
                        component={PaymentMethodsScreen}
                    />

                    <Stack.Screen
                        name="NotificationScreen"
                        component={NotificationScreen}
                    />

                    <Stack.Screen
                        name="PrivacyPolicyScreen"
                        component={PrivacyPolicyScreen}
                    />

                    <Stack.Screen
                        name="GetHelpScreen"
                        component={GetHelpScreen}
                    />

                    <Stack.Screen
                        name="AboutScreen"
                        component={AboutScreen}
                    />

                    <Stack.Screen
                        name="EditProfileScreen"
                        component={EditProfileScreen}
                    />

                    <Stack.Screen
                        name="AddCardScreen"
                        component={AddCardScreen}
                    />

                    <Stack.Screen
                        name="ReminderScreen"
                        component={ReminderScreen}
                        options={{headerShown:false}}
                    />


                </Stack.Group>)
            }

        </Stack.Navigator>
    );

};