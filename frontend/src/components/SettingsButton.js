import React from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';

export default SettingsButton = ( { title, onPress } ) => {

  return (
    <View>
        <TouchableOpacity onPress={onPress}>
          <View>
            <Text>{title}</Text>
          </View>
        </TouchableOpacity>
    </View>
  );

}


