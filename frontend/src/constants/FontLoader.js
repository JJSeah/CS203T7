// import * as Font from 'expo-font';
// import { Text, StyleSheet, View } from 'react-native';

// const CustomText = ({ children, style}) => {
//     return <Text style = {[styles.titleText, style]}>{children}
//     </>
// };

// const styles = StyleSheet.create({
//     titleText: {
//         fontFamily: 'Inter-Black',
//         fontSize: 30,
//     }
// })

// import React from 'react';
// import { useFonts } from 'expo-font';
// import AppLoading from 'expo-app-loading';


// export const Font = () => {
//     let [fontsLoaded] = useFonts({
//         'Product-Sans-Regular': require('../../assets/fonts/Product-Sans-Regular.ttf')
//     });

//     if(!fontsLoaded){
//         return <AppLoading />
//     }
// }

import React from 'react';
import * as Font from 'expo-font';

export default FontLoader = async() => 
    await Font.loadAsync({
        'Product-Sans-Regular': require('../../assets/fonts/Product-Sans-Regular.ttf')
    });
    // const [isFontLoaded, setIsFontLoaded] = useState(false);

    // useEffect(() => {
    //     async function loadFont() {
    //         await Font.loadAsync({
    //             'Product-Sans-Regular': require('../../assets/fonts/Product-Sans-Regular.ttf')
    //         });
    //         setIsFontLoaded(true);
    //     }

    //     loadFont();
    // }, []);

    // if (!isFontLoaded) {
    //     return <AppLoading />;
    // }


