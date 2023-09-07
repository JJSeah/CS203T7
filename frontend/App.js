import { UserProvider } from './src/model/User';
import OnboardingNavigator from './src/navigation/OnboardingNavigation'

export default function App() {

  return (
    <UserProvider>
      <OnboardingNavigator/>
    </UserProvider>
  );

}
