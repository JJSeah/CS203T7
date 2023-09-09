// import React from 'react'
// import { View, Image, TextInput, StyleSheet, TouchableOpacity }  from 'react-native'
// // import { borderColor, placeholderTextColor } from "../shared/Colors";
// import RegisterScreenViewController from '../viewController/RegisterScreenViewController';
// import RegisterScreen from '../view/RegisterScreen';
// const hidePasswordImagePath = "../../assets/images/hidePassword.png"
// const showPasswordImagePath = "../../assets/images/showPassword.png" 

// export default PasswordField = ( {placeholder, onChangeText} ) => {

//     return (
//         <View>
//             <TextInput 
//             style = {styles.input}
//             placeholder= {placeholder}
//             // placeholderTextColor={placeholderTextColor} 
//             secureTextEntry = {seePassword}
//             onChangeText={onChangeText} 
//             />
//             <TouchableOpacity 
//              onPress={() => setSeePassword(!seePassword)} style={styles.icon}>
//                 <View>
//                     <Image source = {seePassword ? "showPasswordImagePath"
//                                     : "hidePasswordImagePath" } size={20} color={'#333'}        
//                     />
//                 </View> 
//             </TouchableOpacity>
//         </View>

//     ) 
// }

// const styles = StyleSheet.create({
//     input: {
//         fontSize: 15,
//         borderWidth: 3,
//         borderRadius: 10,
//         // borderColor: borderColor,
//         padding: 20,
//         margin: 15,
//         borderRadius: 10,
//         fontWeight: 'bold',
//         color: 'red',
//         flexDirection: 'row', 
//     }, 
//     icon: { 
//         position: 'absolute',
//         right: 20, 
//         top: 8,
//     }

// })