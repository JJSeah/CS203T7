import React, {useContext, useState} from 'react'
import { StyleSheet, Text, View, Button, TextInput, ScrollView, TouchableOpacity, Alert, secureTextEntry } from 'react-native'
import CustomTextField from '../components/CustomTextField'
import RegisterScreenViewController from '../viewController/RegisterScreenViewController'
import CustomLongButton from '../components/CustomLongButton'
import PasswordField from '../components/PasswordField'
import { Formik } from 'formik'
import * as Yup from 'yup'
import { UserContext } from '../model/User';
import { styles } from "../components/Design"; 
import * as Font from 'expo-font';
import AppLoading from 'expo-app-loading';
import FontLoader from '../constants/FontLoader';


const SignupSchema = Yup.object().shape({

  firstName: Yup.string()
    .required('Please enter your first name.'),

  lastName: Yup.string()
    .required('Please enter your last name.'),

  username: Yup.string()
    .min(6, 'Username must contain 6-15 characters.')
    .max(15, 'Username must contain 6-15 characters.')
    .required('Please enter your username.'),

  email: Yup.string()
    .matches(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/, 
    'Invalid email format.')
    .required('Please enter your email address.'),

  password: Yup.string()
    .min(8, 'Password must contain at least 8 characters.')
    .required('Please enter your password.')
    .matches(
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/, 
      'Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit and one special character'
      ),

  confirmPassword: Yup.string()
    .min(8, 'Password must contain at least 8 characters.')
    .oneOf([Yup.ref('password')], 'Your passwords do not match.')
    .required('Please confirm your password.')
});


export default RegisterScreen = ( { navigation } ) => {

  const {signUpButtonPressed} = RegisterScreenViewController({navigation})
  const [isReady, setIsReady] = useState(false);

  const loadFonts = async() => {
    await FontLoader();
  }; 
  if(!isReady){
    return (
      <AppLoading
        startAsync={loadFonts}
        onFinish={() => setIsReady(true)}
        onError={() => {}}
      />
    );
  }
  return (
  <Formik initialValues ={{
      firstName:'', 
      lastName:'',
      username:'', 
      email:'', 
      password:'',
      confirmPassword: ''
  }}
  validationSchema={SignupSchema}
  validateOnMount={true}
  onSubmit={values => console.log(values)}
  > 
   {({values, errors, touched, handleChange, setFieldTouched, isValid, handleSubmit}) => (
    <ScrollView style = {styles.container}>
      <View style = {styles.header}>
        <Text style={registerStyle.boldText}>Register</Text>
      </View>

      <View style = {registerStyle.body}>
        <CustomTextField
          placeholder = 'First Name'
          value={values.firstName}
          onChangeText={handleChange('firstName')}
          onBlur={() => setFieldTouched('firstName')}/>
          

        {errors.firstName && touched.firstName &&  (
          <Text style={registerStyle.textFailed}>{errors.firstName}</Text>
        )}

        <CustomTextField
          placeholder = 'Last Name'
          values ={values.lastName}
          onChangeText={handleChange('lastName')}
          onBlur={() => setFieldTouched('lastName')}/>

        {errors.lastName && touched.lastName && (
          <Text style={registerStyle.textFailed}>{errors.lastName}</Text>
        )}
         
        <CustomTextField
          placeholder = 'Username'
          values ={values.username}
          onChangeText={handleChange('username')}
          onBlur={() => setFieldTouched('username')}/>

        {errors.username && touched.username && (
          <Text style={registerStyle.textFailed}>{errors.username}</Text>
        )}

        <CustomTextField
          placeholder = 'Email'
          values ={values.email}
          onChangeText={handleChange('email')}
          onBlur={() => setFieldTouched('email')}/>  

        {errors.email && touched.email && (
          <Text style={registerStyle.textFailed}>{errors.email}</Text>
        )}

        <PasswordField
          placeholder = 'Password'
          values={values.password}
          onChangeText={handleChange('password')}
          onBlur={() => setFieldTouched('password')}
          secureTextEntry={true}/>

        {errors.password && touched.password && (
          <Text style={registerStyle.textFailed}>{errors.password}</Text>
        )}

        <PasswordField
          placeholder = 'Confirm Password'
          autoCapitalize={false}
          values={values.confirmPassword}
          onChangeText={handleChange('confirmPassword')}
          onBlur={() => setFieldTouched('confirmPassword')}
          secureTextEntry={true}/>

        {errors.confirmPassword && touched.confirmPassword && (
          <Text style={registerStyle.textFailed}>{errors.confirmPassword}</Text>
        )}
        
      </View> 

      <CustomLongButton
        title="Sign Up"
        onPress={() => { signUpButtonPressed(values.firstName, values.lastName, values.username, values.email, values.password)}}
        disabled={!isValid}
      />

    </ScrollView>
    )}
    </Formik>
  )
}

const registerStyle = StyleSheet.create({
  // container: {
  //   flex: 1, 
  //   backgroundColor: '#fff', 
  // }, 
  header: {
    padding : 20,
  },
  boldText: {
    fontWeight: 'bold',
    fontSize: 40, 
    color: 'white', 
  }, 
  body:{
    // backgroundColor: '#fff', 
    fontSize: 30, 
    padding: 10,
    marginVertical : 10,
  }, 
  textFailed:{
    color: 'red', 
    fontSize: 13.5, 
    fontWeight: 'bold', 
    marginLeft: 20,
  }, 
})