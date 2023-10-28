import { useContext, useState } from "react";
import { CardRepository } from "../model/CardRepository";

export default MakePaymentViewController = ( { navigation }) => {

    const [ paymentCard, setPaymentCard ] = useState(null)


    const makePayment = async(apptId, paymentCard) => {
        axios.put(`${BASE_URL}/api/appointment/complete/${apptId}`,
         {
           headers: {
             'Authorization': `Bearer ${userToken}`
           }
         })
         .then(res => {
          console.log("finished charging")
          loadAllAppointments()
          navigation.navigate('HomeNavigator');
         })
         .catch (e => {
          console.log(`failed to finish charging: ${e}`)
          })

    }

    return {
        paymentCard, 
        setPaymentCard
    };
}
       