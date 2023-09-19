import React from 'react';
import { StyleSheet, TextInput, View } from 'react-native';

export default CustomTextField = ( { placeholder, value, onChangeText, onBlur, autoCapitalize = "none", autoCorrect = false} ) => {

  return (
    <View style={styles.container}>

        <TextInput style={styles.input}
            placeholder={placeholder} 
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
    borderColor: '#3081EE',
    padding: 10,
    marginHorizontal: 15,
    marginTop: 15,
    borderRadius: 8,
    flexDirection: 'row', 
    alignItems: 'center'
  }, 
  input:{
    flex: 1, 
    fontSize: 15, 
    paddingLeft: 10,
  },
})