import React, {useState} from 'react'; 
import RegisterViewModel from '../viewModels/RegisterViewModel'

export default RegisterViewController = ( { navigation } ) => {

  const [firstName, setFirstName] = useState(""); 
  const [lastName, setLastName] = useState(""); 
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState(""); 
  const [checkValidEmail, setCheckValidEmail] = useState(false);
  const [password, setPassword] = useState("");
  const [seePassword, setSeePassword] = useState(true);
  const [checkValidPassword, setCheckValidPassword] = useState(false);


  return {
      setFirstName,
      setLastName, 
      setUsername, 
      setEmail,
      setPassword,
      setCheckValidEmail,
      setSeePassword, 
      setCheckValidPassword
  }

}