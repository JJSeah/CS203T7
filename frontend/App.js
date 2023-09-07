import { StatusBar } from 'expo-status-bar';
import { Text, View } from 'react-native';
import AppNavigator from './src/navigation/AppNavigator';
import { NavigationContainer } from '@react-navigation/native';
import { UserProvider } from './src/model/User';

export default function App() {

  return (
    <UserProvider>
      <NavigationContainer>
        <AppNavigator/>
      </NavigationContainer>
    </UserProvider>
  );

}
