import React from 'react'
import { StyleSheet, Text, View, Button, TextInput, ScrollView, TouchableOpacity} from 'react-native'
import TextField from '../components/TextField'
import RegisterViewController from '../viewController/RegisterViewController'
import PasswordField from '../components/PasswordField'

export default RegisterScreen = ( { navigation } ) => {

  const {setFirstName, setLastName, setUsername, setEmail, setCheckValidEmail, setPassword, setSeePassword, setCheckValidPassword} = RegisterViewController( { navigation } )

  return (

    <ScrollView style = {styles.container}>
      <View style = {styles.header}>
        <Text style={styles.boldText}>Sign Up</Text>
      </View>

      <View style = {styles.body}>
        <TextField
          placeholder = 'First Name'
          onChangeText={setFirstName}/>

        <TextField
          placeholder = 'Last Name'
          onChangeText={setLastName}/>
         
        <TextField
          placeholder = 'Username'
          onChangeText={setUsername}/>

        <TextField
          placeholder = 'Email'
          onChangeText={setEmail => handleCheckEmail(setEmail)}/>  
      </View> 

      {checkValidEmail ? <Text style={styles.textFailed}>Email format is incorrect.</Text>
      : <Text style={styles.textFailed} ></Text>} 

      <View>
        <PasswordField
          placeholder = 'Password'
          onChangeText={setPassword => handleCheckPassword(setPassword)}/>
      </View>

      {checkValidPassword ? <Text style= {styles.textFailed}>checkValidityPassword(setPassWord)</Text>
      : <Text style={styles.textFailed} ></Text>}

    </ScrollView>
 
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
    padding: 20,
    marginVertical : 20,
  }, 
  textFailed:{
    color: 'red', 
    fontSize: 15, 
    fontWeight: 'bold', 
  }

})