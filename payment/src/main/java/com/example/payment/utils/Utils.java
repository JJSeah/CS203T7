
package com.example.payment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;


public final class Utils {
	
	/***
	 * 
	 * @param _object
	 * @return
	 */



public static String toJsonString(Object _object) {
		if(_object == null) {
			return "";
		}
		try {
			return new ObjectMapper()
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.findAndRegisterModules()
					.writeValueAsString(_object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static class Strings {

		/**
		 * Returns True if the String is NULL or Empty
		 * 
		 * @param input
		 * @return
		 */
	    public static boolean isBlank(String input) {
	        return input == null || input.trim().isEmpty();
	    }
	    
	    public static Stream<String> blankStrings() {
	        return Stream.of(null, "", "  ");
	    }
	}

	public static class Numbers {
		
		/**
		 * Returns True if the Number is an Odd Number
		 * @param number
		 * @return
		 */
	    public static boolean isOdd(int number) {
	        return number % 2 != 0;
	    }
	}

	public static void main(String[] args) throws Exception {
	}
}
