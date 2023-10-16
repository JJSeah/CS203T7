import React, { useContext } from 'react';
import { Text, View } from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";

import { UserContext } from '../../../model/User';
import AddCardViewController from '../../../viewController/AddCardViewController';


export default PaymentMethodsScreen = ( {navigation} ) => {

    const {
      name, setName,
      number, setNumber,
      expiry, setExpiry,
      addCardButtonPressed,
    } = AddCardViewController( {navigation} );

    const { userData } = useContext(UserContext);

  return (
    <SafeAreaView>
      <Text>This is PaymentScreen</Text>
    </SafeAreaView>
  );
}
