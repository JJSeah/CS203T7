import { Text, StyleSheet, TouchableOpacity } from "react-native";
import { buttonColor, placeholderTextColor, fontSize} from "../shared/Colors"

export default RectangularButton = ( { title, onPress } ) => {
    return ( 
        <TouchableOpacity
            onPress={onPress}
            style={styles.button}
        >
            <Text
                style={styles.text} 
            >
                {title}
            </Text>
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    button: {
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: buttonColor,
      padding: 20,
      margin: 15,
      borderRadius: 10
    },
    text: {
      fontSize: fontSize,
      fontWeight: 'bold',
      color: 'white',
    },
  });
