package project4;
/**
 * MalformedPhoneNumberException Exception
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: MalformedPhoneNumberException.java
 */

public class MalformedPhoneNumberException extends Exception{
		
	public MalformedPhoneNumberException() {
		//  The message variable from the super class 'Exception'
		super("MalformedPhoneNumberException\n"
				+ "Invalid Phone Number: Enter in (xxx)xxx-xxxx format");
			
	}
}

