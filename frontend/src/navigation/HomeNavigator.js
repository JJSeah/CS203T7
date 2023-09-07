import { createBottomTabNavigator } from '@react-navigation/bottom-tabs'
import HomeScreen from "../view/HomeScreen"
import HistoryScreen from "../view/HistoryScreen"
import MapScreen from "../view/MapScreen"
import SettingsScreen from "../view/SettingsScreen"

const Tab = createBottomTabNavigator();

export default MyTabs = () => {

    return (
        <Tab.Navigator>

            <Tab.Screen 
                name="HomeScreen"     
                component={HomeScreen}
            />


            <Tab.Screen 
                name="HistoryScreen"     
                component={HistoryScreen}
            />

            <Tab.Screen 
                name="MapScreen"     
                component={MapScreen}
            />


            <Tab.Screen 
                name="SettingsScreen"     
                component={SettingsScreen}
            />


        </Tab.Navigator>
    );
}