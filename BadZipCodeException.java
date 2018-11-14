package project4;
/**
 * BadZipCodeException Exception
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: BadZipCodeException.java
 */

public class BadZipCodeException extends Exception{
		
	public BadZipCodeException() {
		//  The message variable from the super class 'Exception'
		super("BadZipCodeException\n"
				+ "Invalid Zipcode: Must be 5 digits!");
			
	}
}
