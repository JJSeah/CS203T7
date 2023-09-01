import React from 'react'
import { View, Image, TextInput, StyleSheet, TouchableOpacity }  from 'react-native'
import { borderColor, placeholderTextColor } from "../shared/Colors";
import RegisterViewController from '../viewController/RegisterViewController';
const hidePasswordImagePath = "../../assets/images/hidePassword.png"
const showPasswordImagePath = "../../assets/images/hidePassword.png" 


export default PasswordField = ( {placeholder, onChangeText, seePassword, setSeePassword} ) => {

    // const 
    return (
        <View>
            <TextInput 
            style = {styles.input}
            placeholder= {placeholder}
            placeholderTextColor={placeholderTextColor}
            onChangeText={onChangeText}   
            secureTextEntry = {seePassword}
            />
            <TouchableOpacity 
             onPress={() => setSeePassword(!seePassword)}>
                <Image source = {seePassword ? require('../../assets/images/hidePassword.png') 
                : require('../../assets/images/hidePassword.png') } 
            
                style={styles.icon}
             />
            </TouchableOpacity>
      
        </View>

    ) 
}

const styles = StyleSheet.create({
    input: {
        fontSize: 15,
        borderWidth: 3,
        borderRadius: 10,
        borderColor: borderColor,
        padding: 20,
        margin: 15,
        borderRadius: 10,
        fontWeight: 'bold',
        color: 'red'
    }, 
    icon: {
        size: 10,
        color: 'black',  
        marginRight: 10, 
        
    }



})