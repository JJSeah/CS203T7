import React, { useContext, useEffect } from 'react';
import { StyleSheet, Text, View, Button, TextInput, ScrollView, TouchableOpacity, KeyboardAvoidingView, secureTextEntry, Platform, TouchableWithoutFeedback , SafeAreaView, Keyboard} from 'react-native'

import AddCardViewController from '../../../viewController/AddCardViewController';

import { UserContext } from '../../../model/User';

import CustomLongButton from '../../../components/CustomLongButton';
import { styles } from "../../../components/Design"; 
import CustomTextField from '../../../components/CustomTextField'

import { Formik } from 'formik'
import * as Yup from 'yup'
import FontLoader from '../../../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';
import valid from 'card-validator';

SplashScreen.preventAutoHideAsync();

const AddCardSchema = Yup.object().shape({

  cardHolderName: Yup.string()
    .required('Please enter your name.'),

  cardNumber: Yup.string()
  .matches(/^\d{16}$/, 'Card number must be exactly 16 digits')
//   .test('test-number', 'Card number is invalid',
//   value => valid.number(value).isValid)
    .required('card number is required'),

    cardExpirationDate: Yup.string()
    .matches(/^\d{2}\/\d{2}$/, 'Invalid date format (MM/YY)')
    .test('valid-expiration', 'Expiration date is not valid', 
    value => valid.expirationDate(value).isValid)
    .required('Expiration date is required'),
});

export default AddCardScreen = ( {navigation} ) => {

    const {
      setIsReady,
      confirmAddCardButtonPressed,
    } = AddCardViewController( {navigation} );
    const { userData } = useContext(UserContext);

    useEffect(() => {
        const loadFonts = async() => {
          await FontLoader();
          setIsReady(true);
          await SplashScreen.hideAsync();
        }; 
    
        loadFonts();
      }, []);

      return (
        <Formik initialValues ={{
            cardHolderName:'', 
            cardNumber:'',
            cardExpirationDate:'', 
        }}
        validationSchema={AddCardSchema}
        validateOnMount={true}
        onSubmit={values => console.log(values)}
        > 
         {({values, errors, touched, handleChange, setFieldTouched, isValid,}) => (
          
      
          <SafeAreaView style={[styles.container, {flex:1}]}>
            <KeyboardAvoidingView style={{flex:1}}
              behavior={Platform.OS === 'ios'?'padding':'height'}
              keyboardVerticalOffset={Platform.OS === 'ios'?64:0}>
              
              <ScrollView style={{flexGrow:1}}>
                <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
                  <View>
                  <View style = {registerStyle.body}>
              <CustomTextField
              placeholder = 'Card Holder Name'
              value={values.cardHolderName}
              onChangeText={handleChange('cardHolderName')}
              onBlur={() => setFieldTouched('cardHolderName')}/>
                
      
              {errors.cardHolderName && touched.cardHolderName &&  (
                <Text style={registerStyle.textFailed}>{errors.cardHolderName}</Text>
              )}
      
              <CustomTextField
                placeholder = 'Card Number'
                values ={values.cardNumber}
                onChangeText={handleChange('cardNumber')}
                onBlur={() => setFieldTouched('cardNumber')}/>
      
              {errors.cardNumber && touched.cardNumber && (
                <Text style={registerStyle.textFailed}>{errors.cardNumber}</Text>
              )}
               
      
              <CustomTextField
                placeholder = 'Expiration Date'
                values ={values.cardExpirationDate}
                onChangeText={handleChange('cardExpirationDate')}
                onBlur={() => setFieldTouched('cardExpirationDate')}/>
      
              {errors.cardExpirationDate && touched.cardExpirationDate && (
                <Text style={registerStyle.textFailed}>{errors.cardExpirationDate}</Text>
              )}
            </View> 
      
            <View style={{margin: 40, marginBottom: 35}}>
              <CustomLongButton
              title="Add Card"
              onPress={() => { confirmAddCardButtonPressed(values.cardHolderName, values.cardNumber, values.cardExpirationDate, userData.id)}}
              disabled={!isValid}
              />
            </View>
          
                    </View>
                  </TouchableWithoutFeedback> 
                </ScrollView>
              </KeyboardAvoidingView>  
           </SafeAreaView>
          )}
          </Formik>
        )
      }

      const registerStyle = StyleSheet.create({
        body:{
          // backgroundColor: '#fff', 
          fontSize: 30, 
          padding: 10,
          marginVertical : 10,
        }, 
        text: {
          color: 'white', 
          fontFamily: 'Product-Sans-Regular', 
          fontSize: 15, 
          textAlign: 'center',
        },
        textFailed:{
          color: 'red', 
          fontSize: 13.5, 
          fontWeight: 'bold', 
          marginLeft: 20,
        }, 
      })
