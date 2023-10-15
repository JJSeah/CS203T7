import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomeScreen from "../view/HomeScreen";
import MapScreen from "../view/MapScreen";
import SettingsScreen from "../view/Settings/SettingsScreen";
import Ionicons from '@expo/vector-icons/Ionicons';
import { FontAwesome5 } from '@expo/vector-icons';
import ActivityScreen from '../view/ActivityScreen';

const Tab = createBottomTabNavigator();

export default MyTabs = () => {

    return (
        <Tab.Navigator>

            <Tab.Screen 
                name="Home"     
                component={HomeScreen}  
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <Ionicons name="ios-home"/>
                    },
                    headerShown:false,
                }}
            />

            <Tab.Screen 
                name="Activity"     
                component={ActivityScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <FontAwesome5 name="clipboard-list"/>
                    },
                    headerShown:false
                }}
            />


            <Tab.Screen 
                name="Map"     
                component={MapScreen}
                options={{
                    tabBarIcon: ( {focused, color, size} ) => {
                        return <Ionicons name="map"/>
                    },
                    headerShown:false
                }}
            />
       
            <Tab.Screen 
                name="Settings"     
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