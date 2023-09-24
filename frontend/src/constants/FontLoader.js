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


