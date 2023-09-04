import React, {useState} from 'react'; 
import RegisterViewModel from '../viewModels/RegisterViewModel'

const handleCheckEmail = (text) => {
  let re = /\S+@\S+\.\S+/;
  let regex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}
  $/im; 

  setEmail(text);
  
  if(re.test(text) || regex.test(text)){
    setCheckValidEmail = false; 
  } else {
    setCheckValidEmail = true; 
  }
}


const checkValidityPassword = (value) => {

  const isNonWhiteSpace = /^\S*S$/; 
  if(!isNonWhiteSpace.test(value)){
    return 'Password must not contain white spaces.'; 
  }

  const isContainsUppercase = /^(?=.*[A-Z]).*$/; 
  if(!isContainsUppercase.test(value)){
    return 'Password must have at least one Uppercase Character.';
  }

  const isContainsLowercase = /^(?=.*[a-z]).*$/; 
  if(!isContainsLowercase.test(value)){
    return 'Password must have at least one Lowercase Character.';
  }

  const isContainsNumber = /^(?=.*[0-9]).*$/; 
  if(!isContainsNumber.test(value)){
    return 'Password must have at least one Digit.';
  }

  const isValidLength = /^.{8,16}$/;
  if(!isValidLength.test(value)){
    return 'Password must be 8-16 Characters long.';
  }
  
  return null; 
}

const handleCheckPassword = (text) => {
  setPassWord(text);

  if(checkValidityPassword(text) != null){
    setCheckValidPassword = false; 
  } else {
    setCheckValidPassword = true; 
  }

}

