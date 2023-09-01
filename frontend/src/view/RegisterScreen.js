import React from 'react'
import { StyleSheet, Text, View, Button, TextInput, ScrollView} from 'react-native'
import TextField from '../components/TextField'
import RegisterViewController from '../viewController/RegisterViewController'
import PasswordField from '../components/PasswordField'

export default RegisterScreen = ( { navigation} ) => {

  const {setFirstName, setLastName, setUsername, setEmail, setPassword, setSeePassword, setCheckValidEmail} = RegisterViewController( { navigation } )

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
          onChangeText={setEmail}/>  

        <PasswordField
          placeholder = 'Password'
          onChangeText={setPassword}/>
    </View>
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

})