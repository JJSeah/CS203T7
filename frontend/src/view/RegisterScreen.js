import React from 'react'
import { StyleSheet, Text, View, Button, TextInput, ScrollView, TouchableOpacity, Alert, secureTextEntry } from 'react-native'
import CustomTextField from '../components/CustomTextField'
import RegisterScreenViewController from '../viewController/RegisterScreenViewController'
import CustomLongButton from '../components/CustomLongButton'
import PasswordField from '../components/PasswordField'
import { Formik } from 'formik'
import * as Yup from 'yup'

const SignupSchema = Yup.object().shape({

  firstName: Yup.string()
    .min(2, 'Too Short')
    .max(50, 'Too Long')
    .required('Please enter your first name.'),

  lastName: Yup.string()
    .min(2, 'Too Short')
    .max(50, 'Too Long')
    .required('Please enter your last name.'),

  username: Yup.string()
    .min(6, 'Minimum of 6 characters')
    .max(15, 'Maximum of 15 characters')
    .required('Please enter your username.'),

  email: Yup.string()
    .matches(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/, 
    'Invalid email format.')
    .required('Please enter your email address.'),

  password: Yup.string()
    .min(8)
    .required('Please enter your password.')
    .matches(
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/, 
      'Must contain minimum 8 characters, at least one uppercase letter, one lowercase letter, one digit and one special character'
      ),

  confirmPassword: Yup.string()
    .min(8, 'Password must contain a minimum of 8 characters.')
    .oneOf([Yup.ref('password')], 'Your passwords do not match.')
    .required('Please confirm your password.')
});


export default RegisterScreen = ( { navigation } ) => {

  const {signUpButtonPressed} = RegisterScreenViewController({navigation})

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
        <Text style={styles.boldText}>Register</Text>
      </View>

      <View style = {styles.body}>
        <CustomTextField
          placeholder = 'First Name'
          value={values.firstName}
          onChangeText={handleChange('firstName')}
          onBlur={() => setFieldTouched('firstName')}/>
          

        {errors.firstName && touched.firstName &&  (
          <Text style={styles.textFailed}>{errors.firstName}</Text>
        )}

        <CustomTextField
          placeholder = 'Last Name'
          values ={values.lastName}
          onChangeText={handleChange('lastName')}
          onBlur={() => setFieldTouched('lastName')}/>

        {errors.lastName && touched.lastName && (
          <Text style={styles.textFailed}>{errors.lastName}</Text>
        )}
         
        <CustomTextField
          placeholder = 'Username'
          values ={values.username}
          onChangeText={handleChange('username')}
          onBlur={() => setFieldTouched('username')}/>

        {errors.username && touched.username && (
          <Text style={styles.textFailed}>{errors.username}</Text>
        )}

        <CustomTextField
          placeholder = 'Email'
          autoCapitalize={false}
          values ={values.email}
          onChangeText={handleChange('email')}
          onBlur={() => setFieldTouched('email')}/>  

        {errors.email && touched.email && (
          <Text style={styles.textFailed}>{errors.email}</Text>
        )}

        <PasswordField
          placeholder = 'Password'
          autoCapitalize={false}
          values={values.password}
          onChangeText={handleChange('password')}
          onBlur={() => setFieldTouched('password')}
          secureTextEntry={true}/>

        {errors.password && touched.password && (
          <Text style={styles.textFailed}>{errors.password}</Text>
        )}

        <PasswordField
          placeholder = 'Confirm Password'
          autoCapitalize={false}
          values={values.confirmPassword}
          onChangeText={handleChange('confirmPassword')}
          onBlur={() => setFieldTouched('confirmPassword')}
          secureTextEntry={true}/>

        {errors.confirmPassword && touched.confirmPassword && (
          <Text style={styles.textFailed}>{errors.confirmPassword}</Text>
        )}
        
      </View> 

      <CustomLongButton
        title="Sign Up"
        onPress={signUpButtonPressed}
        disabled={!isValid}
      />

    </ScrollView>
    )}
    </Formik>
  )
}

const styles = StyleSheet.create({
  container : {
    flex: 1, 
    backgroundColor: '#fff', 
  }, 
  header : {
    padding : 20,
  },
  boldText: {
    fontWeight: 'bold',
    fontSize: 40, 
    color: 'black', 
  }, 
  body:{
    backgroundColor: '#fff', 
    fontSize: 30, 
    padding: 10,
    marginVertical : 10,
  }, 
  textFailed:{
    color: 'red', 
    fontSize: 15, 
    fontWeight: 'bold', 
    marginLeft: 10,
  }, 
})