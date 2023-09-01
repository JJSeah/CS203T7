import React from 'react'
import { View, Image }  from 'react-native'
const hidePasswordImagePath = "../../assets/images/hidePassword.png"
const showPasswordImagePath = "../../assets/images/hidePassword.png"


export default HidePasswordView = ( { showPassword } ) => {
    return (
        <View style = >


            <TouchableOpacity onPress = {() => setSeePass}
           <Image source={require(showPassword ? showPasswordImagePath : hidePasswordImagePath )}>
           </Image>
        </View>
    )
}