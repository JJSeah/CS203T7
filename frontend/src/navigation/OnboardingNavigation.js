import AppNavigator from './AppNavigator';
import { NavigationContainer } from '@react-navigation/native';
import { UserContext, UserProvider } from '../model/User'
import { useContext } from 'react';
import HomeNavigator from './HomeNavigator';

export default OnBoardingNavigation = () => {

  const { userToken } = useContext(UserContext);

  return (
      <NavigationContainer>
        {userToken === null ? <AppNavigator/> : <HomeNavigator/>}
      </NavigationContainer>
  );

}