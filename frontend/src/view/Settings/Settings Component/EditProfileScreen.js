import React, {useContext, useEffect} from 'react'
import { StyleSheet, Text, View, Button, TextInput, ScrollView, TouchableOpacity, KeyboardAvoidingView, secureTextEntry, Platform, TouchableWithoutFeedback , SafeAreaView, Keyboard} from 'react-native'
import CustomTextField from '../../../components/CustomTextField'
import RegisterScreenViewController from '../../../viewController/RegisterScreenViewController'
import EditProfileScreenViewController from '../../../viewController/EditProfileScreenViewController'
import CustomLongButton from '../../../components/CustomLongButton'

import { Formik } from 'formik'
import * as Yup from 'yup'
import { UserContext } from '../../../model/User';
import { styles } from "../../../components/Design"; 
import * as Font from 'expo-font';
import FontLoader from '../../../constants/FontLoader';
import * as SplashScreen from 'expo-splash-screen';


SplashScreen.preventAutoHideAsync();

const EditSchema = Yup.object().shape({

  firstName: Yup.string()
    .required('Please enter your first name.'),

  lastName: Yup.string()
    .required('Please enter your last name.'),

  username: Yup.string()
    .required('Please enter your username.'),
});


export default EditProfileScreen = ( { navigation } ) => {

  const { userData } = useContext(UserContext);
  const {confirmEditProfileButtonPressed, isReady, setIsReady} = EditProfileScreenViewController({navigation})
  

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
      firstName:'', 
      lastName:'',
      username:'', 
  }}
  validationSchema={EditSchema}
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
        placeholder = 'First Name'
        value={values.firstName}
        onChangeText={handleChange('firstName')}
        onBlur={() => setFieldTouched('firstName')}/>
          

        {errors.firstName && touched.firstName &&  (
          <Text style={registerStyle.textFailed}>{errors.firstName}</Text>
        )}

        <CustomTextField
          placeholder = 'Last Name'
          values ={values.lastName}
          onChangeText={handleChange('lastName')}
          onBlur={() => setFieldTouched('lastName')}/>

        {errors.lastName && touched.lastName && (
          <Text style={registerStyle.textFailed}>{errors.lastName}</Text>
        )}
         

        <CustomTextField
          placeholder = 'Username'
          values ={values.username}
          onChangeText={handleChange('username')}
          onBlur={() => setFieldTouched('username')}/>  

        {errors.username && touched.username && (
          <Text style={registerStyle.textFailed}>{errors.username}</Text>
        )}
      </View> 

      <View style={{margin: 40, marginBottom: 35}}>
        <CustomLongButton
        title="Confirm"
        onPress={() => { confirmEditProfileButtonPressed(values.firstName, values.lastName, values.username, userData.id)}}
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






