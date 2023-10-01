import { View, Text} from 'react-native'
import { SelectList } from 'react-native-dropdown-select-list'
import { styles } from './Design';
import { FontAwesome } from '@expo/vector-icons';

export default CustomDropDownList = ( { placeholder, setSelected, searchPlaceholder, data } ) => {

    return (
        <View>
            <SelectList
                placeholder={placeholder}
                setSelected={setSelected}
                searchPlaceholder={searchPlaceholder}
                data={data}
                arrowicon={<FontAwesome name='search' size={12} color={'#B2D3C2'}/>}
                inputStyles={styles.bodyText}
                dropdownTextStyles={styles.bodyText}
            />
        </View>
    );

};