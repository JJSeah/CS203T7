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
    margin: 50, 
    marginBottom: 0,
    fontFamily: 'Product-Sans-Regular'
  },
  subHeader: {
    color: 'white',
    fontSize: 16,
    textAlign: 'left',
    margin: 20,
    fontFamily: 'Product-Sans-Regular',
  },
  bodyText: {
    color: 'black',
    fontSize: 16,
    textAlign: 'left',
    margin: 20,
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
  }
});
