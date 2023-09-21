import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomeScreen from "../view/HomeScreen";
import HistoryScreen from "../view/HistoryScreen";
import MapScreen from "../view/MapScreen";
import SettingsScreen from "../view/Settings/SettingsScreen";
import Ionicons from '@expo/vector-icons/Ionicons';
import { FontAwesome5 } from '@expo/vector-icons';

const Tab = createBottomTabNavigator();

export default MyTabs = () => {

    return (
        <Tab.Navigator>

            <Tab.Screen 
                name="HomeScreen"     
                component={HomeScreen}  
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <Ionicons name="ios-home"/>
                    },
                    headerShown:false,
                }}
            />

            <Tab.Screen 
                name="MapScreen"     
                component={MapScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <Ionicons name="map"/>
                    },
                    headerShown:false
                }}
            />

            <Tab.Screen 
                name="HistoryScreen"     
                component={HistoryScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <FontAwesome5 name="clipboard-list"/>
                    },
                    headerShown:false
                }}
            />

            <Tab.Screen 
                name="SettingsScreen"     
                component={SettingsScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <Ionicons name="settings"/>
                    },
                    headerShown:false
                }}
            />


        </Tab.Navigator>
    );
}