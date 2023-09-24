import React from 'react';
import { Button, Text, View, StyleSheet, TouchableHighlight, TouchableOpacity } from 'react-native';
import { styles } from './Design';

export default HyperlinkButton = ( { title, onPress, disabled = false} ) => {
  
  return (
    <View>

      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
      >

        <View>
            <Text 
            style={styles.hyperLinkText}
            >
              {title}
            </Text>
        </View>

      </TouchableOpacity>

    </View>
  );
}
