import { createNativeStackNavigator } from "@react-navigation/native-stack";
import WelcomeScreen from "../view/WelcomeScreen";
import RegisterScreen from "../view/RegisterScreen";
import TabNavigator from "./TabNavigator";

const Stack = createNativeStackNavigator();

const MyStack = () => {
    return (
        <Stack.Navigator>
            
            <Stack.Screen
                name="WelcomeScreen" 
                component={WelcomeScreen}
            />

            <Stack.Screen
                name="RegisterScreen"
                component={RegisterScreen}
            />

            <Stack.Screen
                name="TabNavigator"
                component={TabNavigator} 
            />

        </Stack.Navigator>
    );
};

export default MyStack;

