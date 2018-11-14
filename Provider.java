package project4;
/**
 * The Provider class holds all the options for
 * Phone plans in ArrayLists.  Because of the File layout
 * I didn't see a reason for a Parameterized Constructor
 * since it couldn't be used.  The original file format would have 
 * worked with it though.
 * @author Ian Cobia
 * @version 1.0
 * 
 * COP2253 Project #4
 * File Name: Provider.java
 */
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Provider {
	private ArrayList<TalkPackage> packages;
	private ArrayList<CellPhone> phones;
	private ArrayList<DataOption> dOption;
	private String transactionFile = "src/project4/transaction.dat";
	
	/**
	 * Default Constructor
	 */
	public Provider() {
		packages = new ArrayList<TalkPackage>();
		phones = new ArrayList<CellPhone>();
		dOption = new ArrayList<DataOption>();
	}
	
	/**
	 * Add the TalkPackages to an ArrayList
	 * @param minutes Integer: (Key) Minutes of the plan
	 * @param cost Double: (Value) Cost of the plan
	 */
	public void setPackages(Integer minutes, Double cost) {
		TreeMap<Integer, Double> insertPackageMap = new TreeMap<Integer, Double>();
		// Map the Key and Value
		insertPackageMap.put(minutes, cost);
		// Create a new Talk Package object using Key and Value
		TalkPackage insertPackage = new TalkPackage(insertPackageMap);
		// Add TalkPackage to ArrayList
		packages.add(insertPackage);
	}
	
	/**
	 * Add an individual TalkPackage to the Provider Object
	 * @param tPackage TalkPackage: The TalkPackage to add
	 */
	public void setPackage(TalkPackage tPackage) {
		packages.add(tPackage);
	}
	
	/**
	 * Add the CellPhones to an ArrayList
	 * @param model Integer: (Key) Model of the phone
	 * @param cost Double: (Value) Cost of the plan
	 */
	public void setPhones(Integer model, Double cost) {
		TreeMap<Integer, Double> insertPhoneMap = new TreeMap<Integer, Double>();
		insertPhoneMap.put(model, cost);
		CellPhone insertPhone = new CellPhone(insertPhoneMap);
		phones.add(insertPhone);
	}
	
	/**
	 * Add an individual CellPhone to the Provider Object
	 * @param cPhone CellPhone: The CellPhone to add
	 */
	public void setPhone(CellPhone cPhone) {
		phones.add(cPhone);
	}
	
	/**
	 * Add the DataOptions to an ArrayList
	 * @param data String: (Key) Data Option of the phone
	 * @param cost Double: (Value) Cost of the plan
	 */
	public void setDOption(String data, Double cost) {
		TreeMap<String, Double> insertDataMap = new TreeMap<String, Double>();
		insertDataMap.put(data, cost);
		DataOption insertData = new DataOption(insertDataMap);
		dOption.add(insertData);
	}
	
	/**
	 * Add an individual DataOption to the Provider Object
	 * @param dataPlan DataOption: The DataOption to add
	 */
	public void setDataOption(DataOption dataPlan) {
		dOption.add(dataPlan);
	}
	
	/**
	 * Get an Individual TalkPackage from the ArrayList
	 * @param minutes Integer: Key of the requested TalkPackage
	 * @return TalkPackage: The requested Package
	 */
	public TalkPackage getPackage(Integer minutes) {
		// Roll through the Packages in the List
		for (int i = 0; i < packages.size(); i++) {
			// Check to see if the Key Matches a Value
			if (packages.get(i).getMinutes(minutes)) {
				// Return the Package if a match is found
				return packages.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Get an Individual CellPhone from the ArrayList
	 * @param model Integer: Key of the requested TalkPackage
	 * @return CellPhone: The requested Phone
	 */
	public CellPhone getPhone(Integer model) {
		// Roll through the Phones in the List
		for (int i = 0; i < phones.size(); i++) {
			// Check to see if the Key Matches a Value
			if (phones.get(i).getPhone(model)) {
				// Return the Phone if a match is found
				return phones.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Get an Individual DataOption from the ArrayList
	 * @param data String: Key of the requested DataOption
	 * @return DataOption: The requested DataOption
	 */
	public DataOption getDataPlan(String data) {
		// Roll through the Plans in the List
		for (int i = 0; i < dOption.size(); i++) {
			// Check to see if the Key Matches a Value
			if (dOption.get(i).getDataOption(data)) {
				// Return the Plan if a match is found
				return dOption.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Set the shipping cost based on the Customers zipcode
	 * @param zipcode int: Customers zipcode
	 * @return double: Cost of Shipping based on zipcode
	 */
	public double setShippingCost(int zipcode) {
		// Local zipcode is Free
		if (zipcode >= 32500 && zipcode<= 32599) {
			return 0.0;
			// Alaska and Hawaii are $10.00
		} else if ((zipcode >= 99500 && zipcode <= 99999) || (zipcode >= 96700 && zipcode <= 96899)) {
			return 10.00;
			// The rest of the zipcodes are $5.00
		} else {
			return 5.0;
		}
	}
	
	/**
	 * Write the Customers Plan information to a File
	 * @param customer Customer: The Customer whose plan we need to write
	 */
	public void writeToFile(Customer customer){
		if (!isInFile(customer.getCustomerID())) {
			FileWriter fw;
			try {
				fw = new FileWriter(transactionFile,true);
				fw.write(customer.custProviderToString());
				fw.close();
				System.out.println("Transaction written to File!\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Customer already has plan.");
		}
	}
	
	/**
	 * Retrieve a transaction based on the Customers ID
	 * @param transactionID int: Id of the Customer
	 * @return String: The Formatted Transaction information
	 */
	public String retrieveTransaction(int transactionID) {
		// Convert the Customers ID to a String
		String stringTransactionID = Integer.toString(transactionID);
		ArrayList<String> printString = new ArrayList<String>();
		// Get the path to the file
		Path path = Paths.get(transactionFile);
		List<String> lines;
		try {
			// Read the lines in the file
			lines = Files.readAllLines(path);
			// Add the lines to an ArrayList
			for(String line: lines) {
				printString.add(line + "\n");
			}
			// Roll through the ArrayList of lines from the file
			for (int i = 0; i < printString.size(); i++) {
				// If a Line contains the ID number of the Customer
				if (printString.get(i).contains(stringTransactionID)) {
					// Add the lines from the ID + 24 more lines
					ArrayList<String> requestedTransaction = new ArrayList<String>(printString.subList(i, i+24));
					return requestedTransaction.toString();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// If there is no ID that matches
		return String.format("%n#############################%n"
				+ "No Transaction History found!"
				+ "%n#############################%n");
	}
	
	private boolean isInFile(int transactionID) {
		String stringTransactionID = Integer.toString(transactionID);
		ArrayList<String> printString = new ArrayList<String>();
		// Get the path to the file
		Path path = Paths.get(transactionFile);
		List<String> lines;
		try {
			// Read the lines in the file
			lines = Files.readAllLines(path);
			// Add the lines to an ArrayList
			for(String line: lines) {
				printString.add(line + "\n");
			}
			// Roll through the ArrayList of lines from the file
			for (int i = 0; i < printString.size(); i++) {
				// If a Line contains the ID number of the Customer
				if (printString.get(i).contains(stringTransactionID)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Return a formatted String of the TalkPackages Available
	 * @return String: TalkPackages toString
	 */
	public String printPackages() {
		StringBuilder returnString = new StringBuilder();
		for (TalkPackage onePackage: packages) {
			returnString.append(onePackage.toString());
		}
		return returnString.toString();
	}
	
	/**
	 * Return a formatted String of the CellPhones Available
	 * @return String: CellPhones toString
	 */
	public String printPhones() {
		StringBuilder returnString = new StringBuilder();
		for (CellPhone onePhone: phones) {
			returnString.append(onePhone.toString());
		}
		return returnString.toString();
	}
	
	/**
	 * Return a formatted String of the DataOptions Available
	 * @return String: DataOptions toString
	 */
	public String printDataOptions() {
		StringBuilder returnString = new StringBuilder();
		for(DataOption oneOption: dOption) {
			returnString.append(oneOption.toString());
		}
		return returnString.toString();
	}

	/**
	 * Return a toString of the TalkPackages, DataOptions, and CellPhones
	 */
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		returnString.append(printPackages() + printPhones() + printDataOptions());
		return returnString.toString();
	}
}
