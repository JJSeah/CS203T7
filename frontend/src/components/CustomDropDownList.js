import { View } from 'react-native'
import { SelectList } from 'react-native-dropdown-select-list'

export default CustomDropDownList = ( { placeholder, setSelected, searchPlaceholder, data } ) => {

    return (
        <View>
            <SelectList
                placeholder={placeholder}
                setSelected={setSelected}
                searchPlaceholder={searchPlaceholder}
                data={data}
            />
        </View>
    );


};