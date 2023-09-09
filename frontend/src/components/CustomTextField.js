import React from 'react';
import { StyleSheet, TextInput, View} from 'react-native';

export default CustomTextField = ( { placeholder, values, onChangeText } ) => {

  return (
    <View>

        <TextInput
            placeholder={placeholder} 
            values={values}
            onChangeText={onChangeText}
        />

    </View>
  );
};
