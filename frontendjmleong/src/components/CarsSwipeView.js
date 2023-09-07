import { Text, View } from "react-native";


export default CarsSwipeView = ({cars} ) => {

    return (
        <View>
            {cars.map(car => {
                <Text>car.nickname</Text>
            })}
        </View>
    ) 
}