import * as React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LogInScreen from '../view/LogInScreen';
import RegisterScreen from '../view/RegisterScreen';
import HomeNavigator from './HomeNavigator';
import AutomateBookingScreen from '../view/AutomateBookingScreen';
import AddCarScreen from '../view/AddCarScreen';

const Stack = createNativeStackNavigator();

export default AppStack = () => {

    return (
        <Stack.Navigator>

            <Stack.Screen
                name="LogInScreen" 
                component={LogInScreen}
            />

            <Stack.Screen
                name="RegisterScreen"
                component={RegisterScreen}
            />

            <Stack.Screen
                name="AutomateBookingScreen"
                component={AutomateBookingScreen}
            />


            <Stack.Screen
                name="HomeNavigator"
                component={HomeNavigator}
            />

            <Stack.Screen
                name="AddCarScreen"
                component={AddCarScreen}
            />

        </Stack.Navigator>
    );

};