import React from "react";
import { TextInput, View, StyleSheet } from "react-native";
import { borderColor, buttonColor, placeholderTextColor } from "../shared/Colors";

export default TextField = ( { placeholder, onChangeText } ) => {
    return (
        <View>
            <TextInput
                placeholder={placeholder}
                placeholderTextColor={placeholderTextColor}
                onChangeText={onChangeText}
                style={styles.text}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    text: {
        fontSize: 15,
        borderWidth: 3,
        borderRadius: 10,
        borderColor: borderColor,
        padding: 20,
        margin: 15,
        borderRadius: 10,
        fontWeight: 'bold',
        color: 'red'
    }
})