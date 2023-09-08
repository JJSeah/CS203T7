import { UserProvider } from './src/model/User';
import { NavigationContainer } from '@react-navigation/native';
import AppNavigator from './src/navigation/AppNavigator';

export default function App() {

  return (
    <UserProvider>

      <NavigationContainer>

        <AppNavigator/> 

      </NavigationContainer>

    </UserProvider>
  );

}
