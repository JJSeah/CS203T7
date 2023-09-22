import React from 'react';
import { Button, Text, View, StyleSheet, TouchableHighlight, TouchableOpacity } from 'react-native';

export default HyperlinkButton = ( { title, onPress, disabled = false} ) => {
  
  return (
    <View>

      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
      >

        <View>
            <Text 
            style={{color:'white'}}
            >
                {title}
            </Text>
        </View>

      </TouchableOpacity>

    </View>
  );
}

// const styles = StyleSheet.create ({
//   container: {
//     borderRadius: 50, 
//     borderColor: 'white',
//     paddingVertical: 10, 
//     paddingHorizontal: 12, 
//     marginLeft: 10, 
//     marginRight: 10, 
//     alignItems: 'center'
//   }, 
//   text: {
//     color: 'white',
//   }
// })
