import React, {useState} from 'react'; 
import RegisterViewModel from '../viewModel/RegisterViewModel'

export default RegisterScreenViewController = ( { navigation } ) => {

  const [seePassword, setSeePassword] = useState(false);


  const signUpButtonPressed  = () => {
    console.log("Sign Up button pressed")
    navigation.navigate("LogInScreen");
  }

  return {
      seePassword,
      setSeePassword, 
      signUpButtonPressed
  }

}