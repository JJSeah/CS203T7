import React from 'react';
import { Button, Text, View } from 'react-native';

export default CustomLongButton = ( { title, onPress, disabled = false} ) => {
  
  return (
    <View>

      <Button
        title={title}
        onPress={onPress}
        disabled={disabled}
      />

    </View>
  );
}
