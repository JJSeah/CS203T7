import React from "react";
import { TextInput, View, StyleSheet } from "react-native";
import { buttonColor } from "../shared/Colors";

export default TextField = ( { placeholder, onChangeText } ) => {
    return (
        <View>
            <TextInput
                placeholder={placeholder}
                onChangeText={onChangeText}
                style={styles}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    fontSize: 15,
    backgroundColor: buttonColor,
    padding: 20,
    margin: 15,
    borderRadius: 10
})