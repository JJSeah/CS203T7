import React from "react";
import { StyleSheet, Text, View, TouchableOpacity, Image } from "react-native";

import * as imageAssets from "../../assets/images";

export default IconButton = ({ iconName, onPress, disabled = false }) => {
  const selectedIcon = imageAssets[iconName];
  return (
    <View style={localStyles.buttonContainer}>
      <TouchableOpacity
        onPress={onPress}
        disabled={disabled}
        style={localStyles.toucableOpacity}
      >
        
        <Image source={selectedIcon} style={localStyles.iconDisplay} />

      </TouchableOpacity>
    </View>
  );
};

const localStyles = StyleSheet.create({
  toucableOpacity: {
    width: "70%",
    height: 50,
  },
  iconDisplay: {
    width: 30,
    height: 45,
    resizeMode: "contain",
  },
  buttonContainer : {
    width: 30,
    height: 45,
    borderRadius: 10, // Adjust as needed to make it look like a button
    shadowColor: 'rgba(0, 0, 0, 0.2)',
    shadowOffset: { width: 2, height: 2 },
    shadowOpacity: 1,
    shadowRadius: 2,
  },
});
