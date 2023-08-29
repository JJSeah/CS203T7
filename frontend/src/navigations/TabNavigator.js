import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomeScreen from '../view/HomeScreen';
import MapScreen from '../view/MapScreen';
import HistoryScreen from '../view/HistoryScreen';
import SettingScreen from '../view/SettingScreen';

const Tab = createBottomTabNavigator();

export default MyTabs = () => {
  return (
    <Tab.Navigator 
      screenOptions={
        {
          headershown: false
        }
      }
    >
      <Tab.Screen name="HomeScreen" component={HomeScreen} />
      <Tab.Screen name="MapScreen" component={MapScreen} />
      <Tab.Screen name="HistoryScreen" component={HistoryScreen} />
      <Tab.Screen name="SettingsScreen" component={SettingScreen} />
    </Tab.Navigator>
  );
}