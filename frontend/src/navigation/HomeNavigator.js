import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomeScreen from "../view/HomeScreen";
import HistoryScreen from "../view/HistoryScreen";
import MapScreen from "../view/MapScreen";
import SettingsScreen from "../view/SettingsScreen";
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
                    }
                }}
            />

            <Tab.Screen 
                name="MapScreen"     
                component={MapScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <Ionicons name="map"/>
                    }
                }}
            />

            <Tab.Screen 
                name="HistoryScreen"     
                component={HistoryScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        // return <Ionicons name="clipboard-list"/>
                        return <FontAwesome5 name="clipboard-list"/>
                    }
                }}
            />

            <Tab.Screen 
                name="SettingsScreen"     
                component={SettingsScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <Ionicons name="settings"/>
                    }
                }}
            />


        </Tab.Navigator>
    );
}