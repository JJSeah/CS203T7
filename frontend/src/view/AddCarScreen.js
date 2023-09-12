import React from 'react';
import { Text, View } from 'react-native';
import CustomLongButton from '../components/CustomLongButton'
import AddCarScreenViewController from '../viewController/AddCarScreenViewController';

export default AddCarScreen = ( { navigation } ) => {

  const { addCarButtonPressed, clearAllFieldsPressed } = AddCarScreenViewController( { navigation} )

  return (
    <View>


      <CustomLongButton
        title="Add car"
        onPress={addCarButtonPressed}
      />




    </View>
  );
}
