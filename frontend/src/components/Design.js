import React from 'react';
import { Text, View, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';


export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black',
  },
  header: {
    color: 'white',
    fontSize: 27,
    fontWeight: 'bold',
    textAlign: 'left',
    margin: 50, 
    marginBottom: 0,
  },
  subHeader: {
    color: 'white',
    fontSize: 20,
    textAlign: 'left',
    margin: 20,
  },
  bodyText: {
    color: 'white',
    fontSize: 16,
    textAlign: 'left',
    margin: 20,
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
    color: '#FAA',
    padding: 20,
    backgroundColor: '#FFA',
    margin: 20,
  },

  // add more as you go
});
