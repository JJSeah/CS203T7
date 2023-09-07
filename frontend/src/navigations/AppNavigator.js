import React, { useCallback, useContext } from "react"
import { NavigationContainer } from '@react-navigation/native';
import OnboardingNavigator from './OnboardingNavigator';
import TabNavigator from "./TabNavigator";
import { AuthContext } from "../context/AuthContext";
import { ActivityIndicator, View } from "react-native";

const AppNavigator = () => {

    const { isLoading, userToken }  = useContext(AuthContext);

    if (isLoading) {
        return (
            <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                <ActivityIndicator size={'large'}/>
            </View>
        );
    }

    return ( 
        <NavigationContainer>
            { userToken === null ? <OnboardingNavigator/> : <TabNavigator/>}
        </NavigationContainer>
    );
}

export default AppNavigator;
