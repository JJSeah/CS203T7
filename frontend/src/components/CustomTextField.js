import React from 'react';
import { StyleSheet, TextInput, View} from 'react-native';

export default CustomTextField = ( { placeholder, value, onChangeText } ) => {

  return (
    <View>

        <TextInput
            placeholder={placeholder} 
            value={value}
            onChangeText={onChangeText}
        />

    </View>
  );
};
