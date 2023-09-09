import React, {useState} from 'react'; 
import RegisterViewModel from '../viewModel/RegisterViewModel'

export default RegisterScreenViewController = ( { navigation } ) => {

  const [firstName, setFirstName] = useState(""); 
  const [lastName, setLastName] = useState(""); 
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState(""); 
  const [checkValidEmail, setCheckValidEmail] = useState(true);
  const [password, setPassword] = useState("");
  const [seePassword, setSeePassword] = useState(true);
  const [checkValidPassword, setCheckValidPassword] = useState(false);

  const signUpButtonPressed  = () => {
    console.log("Sign Up button pressed")
    navigation.navigate("LogInScreen");
  }

  return {
      setFirstName,
      setLastName, 
      setUsername, 
      setEmail,
      setPassword,
      setCheckValidEmail,
      setSeePassword, 
      setCheckValidPassword, 
      signUpButtonPressed
  }

}