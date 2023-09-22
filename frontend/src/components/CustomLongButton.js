import React from 'react';
import { Button, Text, View, StyleSheet, TouchableHighlight } from 'react-native';

export default CustomLongButton = ( { title, onPress, disabled = false} ) => {
  
  return (
    <View style={styles.container}>

      <TouchableHighlight
        onPress={onPress}
        disabled={disabled}
        underlayColor="grey"
      >

        <View>
          <Text>{title}</Text>
        </View>

      </TouchableHighlight>

    </View>
  );
}

const styles = StyleSheet.create ({
  container: {
    borderRadius: 70, 
    borderColor: 'white',
    backgroundColor:'white',
    paddingVertical: 10, 
    paddingHorizontal: 12, 
    marginLeft: 25, 
    marginRight: 25, 
    alignItems: 'center',
  }, 
})
