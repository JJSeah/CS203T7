import React from 'react';
import { StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { LinearGradient } from 'expo-linear-gradient'
import { styles } from './Design';


export default CustomCarButton = ( { title, onPress, disabled = false} ) => {
  return (
    <View style={localStyles.container}>

      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
        style={localStyles.outerCircleContainer}
        >
            <View style = {localStyles.innerCircleContainer}>
            <Text style={localStyles.buttonText}>{title}</Text>
            </View>

  
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
  outerCircleContainer: {
    width: 100,
    height: 100,
    borderRadius: 50,
    borderWidth: 2,
    borderColor:'white',
    backgroundColor: 'grey',
    justifyContent: 'center',
    alignItems: 'center',
    position:'relative',
  },
  innerCircleContainer: {
    width: 75,
    height: 75,
    borderRadius: 50,
    borderWidth: 2,
    borderColor:'red',
    backgroundColor: 'green',
    justifyContent: 'center',
    alignItems: 'center',
    position:'relative',
  },
  buttonText: {
    fontFamily: "Product-Sans-Regular",
    color: 'white',
    textAlign: 'center',
  },
}
)