import React from 'react';
import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';


export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#141414',
  },
  header: {
    color: 'white',
    fontSize: 27,
    fontWeight: 'bold',
    textAlign: 'left',
    margin: 35, 
    marginBottom: 10,
    fontFamily: 'Product-Sans-Regular'
  },
  headerText: {
    fontWeight: 'bold',
    fontSize: 40, 
    color: 'white', 
    fontFamily: 'Product-Sans-Regular',
  }, 
  subHeader: {
    color: 'white',
    fontSize: 16,
    textAlign: 'left',
    margin: 10,
    fontFamily: 'Product-Sans-Regular',
  },
  bodyText: {
    color: 'white',
    fontSize: 16,
    // textAlign: 'left',
    // margin: 20,
    fontFamily: 'Product-Sans-Regular'
  },
  boxContainer: {
    backgroundColor:'white',
    padding: 50,
    borderColor: 'pink',
    marginBottom: 20, 
  },
  SectionHeader: {
    borderWidth: 2,
    borderHeight: 3,
    fontSize: 20,
    color: 'white',
    padding: 20,
    backgroundColor: '#FFA',
    margin: 20,
  },
  // add more as you go
  button: {
      borderRadius: 80, 
      marginHorizontal: 90, 
      marginVertical: 20, 
      alignItems: 'center',
      justifyContent: 'center',
  }, 
  buttonText: {
    color: 'black',
    fontSize: 16,
    fontFamily: 'Product-Sans-Regular',
    padding: 5
  }, 
  hyperLinkText:{
    color:'#B2D3C2', 
    fontWeight: 900,
    fontFamily: 'Product-Sans-Regular'
  }
});
