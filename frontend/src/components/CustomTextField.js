import React from 'react';
import { StyleSheet, TextInput, View } from 'react-native';

export default CustomTextField = ( { placeholder, value, onChangeText, onBlur, autoCapitalize = "none", autoCorrect = false} ) => {

  return (
    <View style={styles.container}>

        <TextInput style={styles.input}
            placeholder={placeholder} 
            placeholderTextColor={'white'}
            value={value}
            onChangeText={onChangeText}
            onBlur={onBlur}
            autoCorrect={autoCorrect}
            autoCapitalize={autoCapitalize}
        />

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
    marginTop: 17,
    marginBottom: 3,
    borderRadius: 8,
    flexDirection: 'row', 
    alignItems: 'center',    
  }, 
  input:{
    flex: 1, 
    fontSize: 15, 
    paddingLeft: 10,
    color: 'white',
    fontFamily: 'Product-Sans-Regular'
  },
})