import React from 'react';
import { Button, Text, View, TouchableOpacity} from 'react-native';
import {LinearGradient} from 'expo-linear-gradient'
import { styles } from './Design';

export default CustomLongButton = ( { title, onPress, disabled = false} ) => {

  return (
    <View>
    
      <LinearGradient 
      colors={['#DFE9F3','#F5F7FA', '#C3CFE2' ]}
      start={{x: 0.2, y: 0.2, z: 0.3}}
      end={{x: 0.9, y: 1, z: 1}}
      style={styles.button}>

      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
        activeOpacity={0.3}
        >
   
      <Text style={styles.bodyText}>{title}</Text>      
  
      </TouchableOpacity>
    
     </LinearGradient>
    
    </View>
  );
}

