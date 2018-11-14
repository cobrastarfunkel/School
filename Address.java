package project4;
/**
 * The Address object for the Customer
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: Address.java
 */
import java.util.regex.Pattern;

public class Address {
	private int streetNumber;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	/**
	 * Default Constructor
	 */
	public Address() {
		setStreetNumber(100);
		setStreet("Laurel St");
		setCity("Pensacola");
		setState("FL");
		setZipcode("99512");
	}
	
	/**
	 * Param Constructor
	 * @param streetNumber int: Street Number
	 * @param street String: Street Name
	 * @param city String: City Name
	 * @param state String: State
	 * @param zipcode String: zipcode
	 */
	public Address(int streetNumber, String street, String city, String state, String zipcode) {
		setStreetNumber(streetNumber);
		setStreet(street);
		setCity(city);
		setState(state);
		setZipcode(zipcode);
	}
	
	/**
	 * Get the Street Number
	 * @return int streetNumber
	 */
	public int getStreetNumber() {
		return streetNumber;
	}
	
	/**
	 * Set the Street Number
	 * @param streetNumber int: The Street Number
	 */
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	
	/**
	 * Get the Street Name
	 * @return String street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * Set the Street Name
	 * @param street String: The Street Name
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * Get the City Name
	 * @return String city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Set the City Name
	 * @param city String: The City Name
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Get the State Name
	 * @return String state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Set the State Name
	 * @param state String: The State Name
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Get the zipcode
	 * @return String zipcode
	 */
	public int getZipcode() {
		return Integer.valueOf(zipcode);
	}
	
	/**
	 * Match the zipcode againsta regex pattern to
	 * ensure the correct format then set the zipcode
	 * @param zipcode String: The zipcode
	 */
	public void setZipcode(String zipcode) {
		// Regex Pattern: [0-9]{5} means there must be 
		// 5 numbers in a row for the zipcode to match
		String zipRegex = "[0-9]{5}";
		Pattern zipPattern = Pattern.compile(zipRegex);
		try {
			// If the Pattern 'phonePattern' does not match the regex pattern
			if (!zipPattern.matcher(zipcode).matches()) {
				throw new BadZipCodeException();
			} else {
			this.zipcode = zipcode;
			}
		} catch (BadZipCodeException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	/**
	 * toString for Address information 
	 */
	public String toString() {
		return String.format("Address: %d %s %n	 %s, %s %s", getStreetNumber(), getStreet(), getCity(),
				getState(), getZipcode());
	}
}
