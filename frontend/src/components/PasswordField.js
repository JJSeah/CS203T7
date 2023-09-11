import { React, useState } from 'react'
import { View, Image, TextInput, StyleSheet, TouchableOpacity }  from 'react-native'
import { MaterialCommunityIcons } from '@expo/vector-icons'

export default PasswordField = ( { placeholder, values, onChangeText, onBlur=true, secureTextEntry=true, seePassword, setSeePassword} ) => {

  return (
    <View style={styles.container}>
        <TextInput style={styles.input}
            placeholder={placeholder} 
            value={values}
            onChangeText={onChangeText}
            onBlur={onBlur}
            secureTextEntry={secureTextEntry && seePassword}
        />

        <TouchableOpacity onPress={seePassword}> 

        <MaterialCommunityIcons style={styles.icon}
          name={seePassword ? 'eye-outline' : 'eye-off-outline'}
        />

        </TouchableOpacity>

    </View>
  );

};

const styles = StyleSheet.create({
  container: {
      borderWidth: 3,
      borderRadius: 10,
      borderColor: '#3081EE',
      padding: 10,
      margin: 10,
      borderRadius: 8,
      flexDirection: 'row', 
      alignItems: 'center'
  }, 
  input:{
    flex: 1, 
    fontSize: 15, 
    paddingLeft: 10,
  },
  icon:{
    // size : 26, 
    color: 'black',
    marginLeft: 10, 
    marginRight: 10
  }
})
