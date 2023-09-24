import React from 'react';
import { Button, Text, View, StyleSheet, TouchableHighlight, Pressable, Animated, TouchableOpacity} from 'react-native';
import {LinearGradient} from 'expo-linear-gradient'

export default CustomLongButton = ( { title, onPress, disabled = false} ) => {

  return (
    <View>
    
      <LinearGradient 
      colors={['#DFE9F3','#F5F7FA', '#C3CFE2' ]}
      start={{x: 0.2, y: 0.2, z: 0.3}}
      end={{x: 0.9, y: 1, z: 1}}
      style={styles.container}>

      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
        activeOpacity={0.3}
        >
   
      <Text style={styles.text}>{title}</Text>      
  
      </TouchableOpacity>
    
     </LinearGradient>
    
    </View>
  );
}

const styles = StyleSheet.create ({
  container: {
    borderRadius: 80, 
    padding: 15,
    marginHorizontal: 90, 
    marginVertical: 20, 
    alignItems: 'center',
    justifyContent: 'center',
  }, 
  text: {
    fontWeight: 'bold',
    fontFamily: 'Product-Sans-Regular'
  },
})
