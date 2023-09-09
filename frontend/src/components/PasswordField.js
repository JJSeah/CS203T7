import { React, useState } from 'react'
import { View, Image, TextInput, StyleSheet, TouchableOpacity }  from 'react-native'
import { MaterialCommunityIcons } from '@expo/vector-icons';
import RegisterScreenViewController from '../viewController/RegisterScreenViewController';

export default PasswordField = ( { placeholder, values, onChangeText, onBlur, seePassword} ) => {

  // const {setSeePassword} = RegisterScreenViewController({navigation})

  return (
    <View>

        <TextInput style={styles.input}
            placeholder={placeholder} 
            value={values}
            onChangeText={onChangeText}
            onBlur={onBlur}
            secureTextEntry={seePassword}
        />
{/* 
        <TouchableOpacity style={styles.icon} onPress={
          ()=>{
            setSeePassword(!seePassword); 
          }
        }>  */}

        <MaterialCommunityIcons style={styles.icon}
          name={!seePassword ? 'eye-outline' : 'eye-off-outline'}
        />

        {/* </TouchableOpacity> */}

    </View>
  );

};

const styles = StyleSheet.create({
  input: {
      fontSize: 15,
      borderWidth: 3,
      borderRadius: 10,
      borderColor: '#3081EE',
      padding: 10,
      margin: 10,
      borderRadius: 8,
      fontWeight: 'bold',
      flexDirection: 'row'
  }, 
  icon:{
    size : 26, 
    color: 'black'
  }
})
