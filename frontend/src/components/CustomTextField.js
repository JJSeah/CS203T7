import React from 'react';
import { StyleSheet, TextInput, View } from 'react-native';

export default CustomTextField = ( { placeholder, value, onChangeText, onBlur } ) => {

  return (
    <View>

        <TextInput style={styles.input}
            placeholder={placeholder} 
            value={value}
            onChangeText={onChangeText}
            onBlur={onBlur}
        />

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
  }, 
})