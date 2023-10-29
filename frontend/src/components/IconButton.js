import React from "react";
import { StyleSheet, Text, View, TouchableOpacity, Image } from "react-native";

import * as imageAssets from "../../assets/images";

export default IconButton = ({ iconName, onPress, disabled = false }) => {
  const selectedIcon = imageAssets[iconName];
  return (
    <View style={localStyles.container}>
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
    width: 45,
    height: 45,
    resizeMode: "contain",
  },
});
