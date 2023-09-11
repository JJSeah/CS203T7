import React from 'react';
import { Button, Text, View, StyleSheet } from 'react-native';

export default CustomLongButton = ( { title, onPress, disabled = false} ) => {
  
  return (
    <View style={styles.container}>

      <Button 
        title={title}
        onPress={onPress}
        disabled={disabled}
        color={'#4169e1'}
      />

    </View>
  );
}

const styles = StyleSheet.create ({
  container: {
    borderRadius: 50, 
    borderColor: '#3081EE',
    paddingVertical: 10, 
    paddingHorizontal: 12, 
    marginLeft: 10, 
    marginRight: 10, 
  }, 
})
