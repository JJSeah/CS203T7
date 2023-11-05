import React from 'react';
import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import FontAwesome from 'react-native-vector-icons/FontAwesome';

export default SettingsButton = ( { title, onPress } ) => {

  return (
    <View>
        <TouchableOpacity onPress={onPress}>
          <View style={styles.item}>
            <Text style={styles.itemText}>{title}</Text>
            <FontAwesome name="angle-right" size={20} color="#ccc" />
          </View>
        </TouchableOpacity>
    </View>
  );

}

const styles = StyleSheet.create({
  item: {
    flexDirection: 'row', // Aligning text and icon horizontally
    alignItems: 'center', 
    paddingHorizontal: 12,
    paddingVertical: 12,
    justifyContent: 'space-between', // Right arrow to the right
  },
  itemText: {
    fontFamily: "Product-Sans-Regular",
    fontSize: 18,
    flex: 1,
    marginRight: 16, // icon and text spacing
  },
});

