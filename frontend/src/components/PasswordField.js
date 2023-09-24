import { React, useState } from 'react'
import { View, TextInput, StyleSheet, TouchableOpacity }  from 'react-native'
import { MaterialCommunityIcons } from '@expo/vector-icons'

export default PasswordField = ( { placeholder, values, onChangeText, onBlur } ) => {

  const [ hidePassword, setHidePassword ] = useState(true); 

  const toggleSeePassword = () => {
    setHidePassword(!hidePassword)
  }

  return (
    <View style={styles.container}>

        <TextInput style={styles.input}
            placeholder={placeholder} 
            placeholderTextColor='white'
            value={values}
            onChangeText={onChangeText}
            onBlur={onBlur}
            secureTextEntry={hidePassword}
        />

        <TouchableOpacity onPress={toggleSeePassword}> 

        <MaterialCommunityIcons style={styles.icon}
          name={!hidePassword ? 'eye-outline' : 'eye-off-outline'}
        />

        </TouchableOpacity>

    </View>
  );

};

const styles = StyleSheet.create({
  container: {
      borderWidth: 2.2,
      borderRadius: 10,
      borderColor: 'white',
      padding: 10,
      marginHorizontal: 17,
      marginTop: 15,
      borderRadius: 8,
      flexDirection: 'row', 
      alignItems: 'center'
  }, 
  input:{
    flex: 1, 
    fontSize: 15, 
    paddingLeft: 10,
    color: 'white', 
    fontFamily: 'Product-Sans-Regular'
  },
  icon:{
    fontSize : 18, 
    color: 'white',
    marginLeft: 10, 
    marginRight: 10
  }
})
