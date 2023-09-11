import React, {useContext, useState} from 'react'; 
import { UserContext } from '../model/User';
// import RegisterViewModel from '../viewModel/RegisterViewModel'

export default RegisterScreenViewController = ( { navigation } ) => {

  const [seePassword, setSeePassword] = useState(false);
  
  const { signUp } = useContext(UserContext);

  const signUpButtonPressed = () => {
    console.log("Sign Up button pressed")
    navigation.navigate("LogInScreen");
  }

  return {
      seePassword,
      setSeePassword, 
      signUpButtonPressed
  }

}