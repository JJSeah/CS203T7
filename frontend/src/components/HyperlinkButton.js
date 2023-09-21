import React from 'react';
import { Button, Text, View, StyleSheet, TouchableHighlight } from 'react-native';

export default HyperlinkButton = ( { title, onPress, disabled = false} ) => {
  
  return (
    <View style={styles.container}>

      <TouchableHighlight
        onPress={onPress}
        disabled={disabled}
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
    borderRadius: 50, 
    borderColor: '#3081EE',
    paddingVertical: 10, 
    paddingHorizontal: 12, 
    marginLeft: 10, 
    marginRight: 10, 
  }, 
})
