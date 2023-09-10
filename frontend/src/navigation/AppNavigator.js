import { useContext } from 'react';
import { UserContext } from '../model/User';

import { createNativeStackNavigator } from '@react-navigation/native-stack';

import LogInScreen from '../view/LogInScreen';
import RegisterScreen from '../view/RegisterScreen';
import HomeNavigator from './HomeNavigator';
import AutomateBookingScreen from '../view/AutomateBookingScreen';
import AddCarScreen from '../view/AddCarScreen';

const Stack = createNativeStackNavigator();

export default AppStack = () => {

    const { userToken, userId, userData, isSuccessful } = useContext(UserContext);

    return (
        <Stack.Navigator>

            { (userData === null) ? 

                // Onboarding screens
                
                (<Stack.Group>

                    <Stack.Screen
                        name="LogInScreen" 
                        component={LogInScreen}
                    />

                    <Stack.Screen
                        name="RegisterScreen"
                        component={RegisterScreen}
                    />

                </Stack.Group>) :


                // Screens for logged in users

                (<Stack.Group>
                    
                    <Stack.Screen
                        name="HomeNavigator"
                        component={HomeNavigator}
                    />

                    <Stack.Screen
                        name="AutomateBookingScreen"
                        component={AutomateBookingScreen}
                    />
        
        
                    <Stack.Screen
                        name="AddCarScreen"
                        component={AddCarScreen}
                    />

                </Stack.Group>)
            }

        </Stack.Navigator>
    );

};