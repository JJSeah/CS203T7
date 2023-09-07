import 'react-native-gesture-handler';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import HomeScreen from '../view/HomeScreen';
import MapScreen from '../view/MapScreen';
import HistoryScreen from '../view/HistoryScreen';
import SettingScreen from '../view/SettingScreen';
import Icon from 'react-native-vector-icons/AntDesign';
import { Platform } from 'react-native';

const Tab = createBottomTabNavigator();

const screenOptions = (route, color) => {
  let iconName;

  switch (route.name) {
    case 'HomeScreen':
      iconName = 'home';
      break;
    case 'MapScreen':
      iconName = 'appstore-o';
      break;
    case 'HistoryScreen':
      iconName = 'folder1';
      break;
    case 'SettingsScreen':
      iconName = 'folder1';
      break;
    default:
      break;
  }

  return <Icon name={iconName} color={color} size={24} />;
};


export default MyTabs = () => {

  return (
    <Tab.Navigator
      screenOptions={
        ({route}) => ({
        tabBarIcon: ({color}) => screenOptions(route, color),}) 
    }
      >
        {}
        <Tab.Screen name="HomeScreen" component={HomeScreen} />
        <Tab.Screen name="MapScreen" component={MapScreen} />
        <Tab.Screen name="HistoryScreen" component={HistoryScreen} />
        <Tab.Screen name="SettingsScreen" component={SettingScreen} />
        {}
    </Tab.Navigator>
  );
}