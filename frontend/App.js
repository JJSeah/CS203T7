import OnboardingNavigator from './src/navigations/OnboardingNavigator';
import { AuthProvider } from './src/context/AuthContext';
import { LogInContext } from './src/viewController/LogInViewController';
import AppNavigator from './src/navigations/AppNavigator';

export default function App() {
  return (
    <AuthProvider>
      <AppNavigator/>
    </AuthProvider>
  );
}

