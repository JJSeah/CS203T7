import { View } from 'react-native'
import ScrollPicker from "react-native-wheel-scrollview-picker"

export default CustomWheelPicker = ( { onValueChange, dataSource } ) => {

    return (
        <View>
            <ScrollPicker
                dataSource={dataSource}
                onValueChange={onValueChange}
            />
        </View>
    );
}