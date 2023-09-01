import { NavigationContainer } from '@react-navigation/native';
import OnboardingNavigator from './src/navigations/OnboardingNavigator';

export default function App() {
  return (
    <NavigationContainer>
      <OnboardingNavigator/>
    </NavigationContainer>
  );
}

