package project4;

/**
 * The Customer class.  Holds all the information about the Customer
 * and will hold their Plan information if they choose to buy a plan.
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: Customer.java
 */

import java.util.regex.Pattern;

public class Customer {
	private String name;
	private Address address;
	private String phoneNumber;
	private int customerID;
	private double planCost;
	private double monthlyCost;
	private static int nextID = 2000;
	
	// Holds the Cell Plan Objects
	private Provider provider;
	
	private boolean shippingApplied = false;
	private double cellPhoneCost = 0.0;
	
	/**
	 * Default Constructor
	 */
	public Customer() {
		name = "Jim Bob";
		address = new Address();
		phoneNumber = "(850)913-1234";
		planCost = 0.0;
		setCustomerID();
		provider = new Provider();
	}
	
	/**
	 * Param Constructor
	 * @param name String: Customers Name
	 * @param address Address: An Address Object for the Customers Address
	 * @param phoneNumber String: Customers Phone Number Format must be (xxx)xxx-xxxx
	 */
	public Customer(String name, Address address, String phoneNumber) {
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNumber);
		planCost = 0.0;
		setCustomerID();
		provider = new Provider();
	}
	
	/**
	 * Add a Talk Package to the Customers profile
	 * @param tPackage TalkPackage: The Talk Package based on minutes requested
	 * @param minutes int: The minutes (Key value) for the requested Talk Package
	 */
	public void addPackage(TalkPackage tPackage, int minutes) {
		provider.setPackage(tPackage);
		setPlanCost(tPackage.getCost(minutes));
	}
	
	/**
	 * Add a Cell Phone to the Customers profile
	 * @param cPhone CellPhone: The Cell Phone based on model number requested
	 * @param model int: The model# (Key value) for the requested Talk Package
	 */
	public void addPhone(CellPhone cPhone, int model) {
		provider.setPhone(cPhone);
		setPlanCost(cPhone.getCost(model));
		cellPhoneCost = (cPhone.getCost(model));
	}
	
	/**
	 * Add a Data Plan to the Customers profile
	 * @param dataPlan DataOption: The Data Plan based on data size requested
	 * @param plan String: The data size (Key value) for the requested Data Option
	 */
	public void addDataPlan(DataOption dataPlan, String plan) {
		provider.setDataOption(dataPlan);
		setPlanCost(dataPlan.getCost(plan));
	}
	
	/**
	 * The Monthly Cost of the Plan.  Takes the Total plan cost and subtracts the 
	 * price of the phone and the shipping coost (if there was any).
	 * @return monthlyCost double: Monthly cost of the plan
	 */
	public double getMonthlyCost() {
		monthlyCost = (getPlanCost() - provider.setShippingCost(address.getZipcode()) - cellPhoneCost);
		return monthlyCost;
	}
	
	/**
	 * Return the cost of the Plan at Startup.  Includes shipping
	 * and price of Phone
	 * @return planCost Double: Current Cost to start up plan.
	 */
	public double getPlanCost() {
		return planCost;
	}
	
	/**
	 * Set the PlanCost.  As features are requested this adds the cost to the
	 * overall cost of the plan.  It also adds the shipping the first time it is
	 * called.
	 * @param planCost double: Cost of the phone plan
	 */
	public void setPlanCost(double planCost) {
		this.planCost += planCost;
		if (!shippingApplied) { 
			this.planCost += provider.setShippingCost(address.getZipcode());
			shippingApplied = true;
		}
	}
	 /**
	  * Customers Name
	  * @return name String: Customers Name
	  */
	public String getName() {
		return name;
	}
	/**
	  * Customers Name
	  * @param name String: Set Customers Name
	  */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Customers Address
	 * @return Address:  Address Object for Customer
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * Customers Address
	 * @param address Address:  Set Address Object for Customer
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * Customers phone number
	 * @return phoneNumber String: The Customers Phone Number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Set the Customers phone number using a regex statement to enforce the
	 * format of the phone number
	 * @param phoneNumber String: Customer Phone Number
	 */
	public void setPhoneNumber(String phoneNumber) {
		/*
		 * Explanation of the regex pattern
		 * [(] == ( must be first in phone number
		 * [0-9]{3} == There must be 3 digits next
		 * [)] == Followed by a )
		 * [0-9]{3} == Three more digits after the )
		 * [-] == Next a -
		 * [0-9]{4} == Finally 4 more digits to end the String
		 */
		String phoneRegex = "[(][0-9]{3}[)][0-9]{3}[-][0-9]{4}";
		Pattern phonePattern = Pattern.compile(phoneRegex);
		try {
			// If the Pattern 'phonePattern' does not match the regex pattern
			if (!phonePattern.matcher(phoneNumber).matches()) {
				throw new MalformedPhoneNumberException();
			} else {
			this.phoneNumber = phoneNumber;
			}
		} catch (MalformedPhoneNumberException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	/**
	 * Customer ID number
	 * @return customerID int: Customers ID
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * Customer ID number.  Set the ID based on next available ID
	 * @param customerID int: Customers ID
	 */
	private void setCustomerID() {
		customerID = nextID;
		nextID++;
	}
	
	/**
	 * Returns a formatted String of the customers Phone plan
	 * information.
	 * @return String returnString: Formatted toString
	 */
	public String custProviderToString() {
		StringBuilder returnString = new StringBuilder();
		returnString.append(toString() + "\n###############################\n"
				+ "        Subscribed Plan\n" + provider.toString() + "###############################\n");
		return returnString.toString();
	}
	/**
	 * toString of the Customers Personal Information.  Does not include
	 * plan information.
	 */
	public String toString() {
		return String.format("%n******************************%n"
				+ "ID:      %d%nName:    %s%n%s%n"
				+ "Phone:   %s%n"
				+ "StartUp Cost:     %.2f%n"
				+ "Monthly Cost:     %.2f%n******************************%n", 
				getCustomerID(), getName(), address.toString(),
				getPhoneNumber(), getPlanCost(), getMonthlyCost());
	}
	
	
}
