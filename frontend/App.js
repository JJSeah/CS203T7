import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import OnboardingNavigator from './src/navigations/OnboardingNavigator';

export default function App() {
  return (
    <NavigationContainer>
      <OnboardingNavigator/>
    </NavigationContainer>
  );
}
