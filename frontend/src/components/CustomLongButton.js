import React from 'react';
import { StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { LinearGradient } from 'expo-linear-gradient'
import { styles } from './Design';

export default CustomLongButton = ( { title, onPress, disabled = false} ) => {
  return (
    <View style={localStyles.container}>

      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
        style={localStyles.toucableOpacity}
        >

            <LinearGradient 
              colors={['white','white', 'white']}
              start={{x: 0.2, y: 0.2, z: 0.3}}
              end={{x: 0.9, y: 1, z: 1}}
              style={localStyles.gradient}
              >
    
            <Text style={styles.buttonText}>{title}</Text>      
  
            </LinearGradient>
      </TouchableOpacity>
    
    
    </View>
  );
}


const localStyles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  toucableOpacity: {
    width: '70%',
    height: 50
  },
  gradient: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 80
  }
}
)