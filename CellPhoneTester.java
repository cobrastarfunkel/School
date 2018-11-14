package project4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CellPhoneTester {
	public static Address testAdd = new Address();
	public static Address testAdd2 = new Address(1023, "Avery St", "Pensacola", "FL", "55722");
	public static Address testAdd3 = new Address(1023, "Joe St", "Tallahasse", "FL", "32511");
	public static Customer testCust = new Customer("Billy Joel", testAdd, "(123)455-9001");
	public static Customer testCust2 = new Customer();
	public static Customer testCust3 = new Customer("John John", testAdd2, "(123)456-9201");
	public static Customer testCust4 = new Customer("Sally Mae", testAdd3, "(123)456-9031");
	public static Provider testProvider = new Provider();
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	public static int userInput = 0;
	public static String stringInput = "";
	public static boolean correctInput = false;
	
	public static void main(String[] args) {
		File file = new File("src/project4/phone.dat");
		
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader(file));
			Integer minutes = 0;
			Double minutesCost = 0.0;				
			Integer model = 0;
			Double phoneCost = 0.0;
			String dOption = "";
			Double dataCost = 0.0;
			
			String line;
			try {
				line = inputStream.readLine();
				while(line != null) {
					String[] lineArray = line.split(",");
					for(int i = 0; i < lineArray.length; i++) {
						if (lineArray[i].equalsIgnoreCase("unlimited")) {
							// Make 'unlimited' an int equal to 0 for easier processing
							lineArray[i] = String.valueOf(0);
						}
					}
					minutes = Integer.parseInt(lineArray[0]);
					minutesCost = Double.parseDouble(lineArray[1]);
					testProvider.setPackages(minutes, minutesCost);
					minutes = Integer.parseInt(lineArray[2]);
					minutesCost = Double.parseDouble(lineArray[3]);
					testProvider.setPackages(minutes, minutesCost);
					minutes = Integer.parseInt(lineArray[4]);
					minutesCost = Double.parseDouble(lineArray[5]);
					testProvider.setPackages(minutes, minutesCost);
					line = inputStream.readLine();
					lineArray = line.split(",");
					
					model = Integer.parseInt(lineArray[0]);
					phoneCost = Double.parseDouble(lineArray[1]);
					testProvider.setPhones(model, phoneCost);
					model = Integer.parseInt(lineArray[2]);
					phoneCost = Double.parseDouble(lineArray[3]);
					testProvider.setPhones(model, phoneCost);
					model = Integer.parseInt(lineArray[4]);
					phoneCost = Double.parseDouble(lineArray[5]);
					testProvider.setPhones(model, phoneCost);
					line = inputStream.readLine();
					lineArray = line.split(",");
					
					dOption = lineArray[0];
					dataCost = Double.parseDouble(lineArray[1]);
					testProvider.setDOption(dOption, dataCost);
					dOption = lineArray[2];
					dataCost = Double.parseDouble(lineArray[3]);
					testProvider.setDOption(dOption, dataCost);
					dOption = lineArray[4];
					dataCost = Double.parseDouble(lineArray[5]);
					testProvider.setDOption(dOption, dataCost);
					line = inputStream.readLine();
					
				}
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		customers.add(testCust);
		customers.add(testCust2);
		customers.add(testCust3);
		customers.add(testCust4);
		menu();
	}
	
	
	public static void menu() {
		do {
			System.out.print("\n###########################\n"
					+ "1: Set up a plan.\n"
					+ "2: Search Transaction File for User Info.\n"
					+ "99: Exit.\nPlease make a selection: ");
			try {
				userInput = sc.nextInt();
				if (userInput == 1) {
					selectUser();
				} else if (userInput == 2) {
					getTransactionHistory();
				} else if (userInput == 99) {
					System.out.println("GoodBye");
					System.exit(0);
				} else {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid Option!");
				sc.nextLine();
			}
		} while (userInput != 99);
	}
	
	public static void getTransactionHistory() {
		do {
			try {
				System.out.printf("Enter the ID Number of the Customer whose Transaction%n"
						+ "History you would like to view(99 to Exit): ");
				userInput = sc.nextInt();
				for (int i = 0; i < customers.size(); i++) {
					if (customers.get(i).getCustomerID() == userInput) {
						System.out.println("\n" + testProvider.retrieveTransaction(userInput));
					} else if (userInput == 99) {
						menu();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid id!");
				sc.nextLine();
				getTransactionHistory();
			}
		} while (userInput != 99);
	}
	
	public static void selectUser() {
		for (Customer customer: customers) {
			System.out.println(customer.toString());
		}
		do {
			System.out.print("Please enter your ID Number to Begin(99 to quit): ");
			try {
				userInput = sc.nextInt();
				for (int i = 0; i < customers.size(); i++) {
					if (customers.get(i).getCustomerID() == userInput) {
						setUpPlan(customers.get(i));
					} else if (userInput == 99) {
						menu();
					}
				}
				throw new InputMismatchException();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid id!");
				sc.nextLine();
				menu();
			}
		} while (userInput != 99);
	}
	
	public static void setUpPlan(Customer customer) {
		correctInput = false;
		do {
			System.out.println(testProvider.printPackages());
			System.out.print("Enter the data plan you'd like by Minutes(0 for unlimited): ");
			try {
				userInput = sc.nextInt();
				if (userInput == 1000 || userInput == 5000 || userInput == 0) {
					correctInput = true;
					customer.addPackage(testProvider.getPackage(userInput), userInput);
					
				} 
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input!");
				sc.nextLine();
			} 
		} while (!correctInput);
		correctInput = false;
		do {
			System.out.println(testProvider.printPhones());
			System.out.print("Enter the Phone Model by number: ");
			try {
				userInput = sc.nextInt();
				if (userInput == 100 || userInput == 200 || userInput == 300) {
					correctInput = true;
					customer.addPhone(testProvider.getPhone(userInput), userInput);
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input!");
				sc.nextLine();
			}
		} while (!correctInput);
		correctInput = false;
		do {
			sc.nextLine();
			System.out.println(testProvider.printDataOptions());
			System.out.print("Enter Data Plan by size (3GB etc.): ");
			try {
				stringInput = sc.nextLine();
				if (stringInput.equalsIgnoreCase("3GB") || stringInput.equalsIgnoreCase("6GB")
						|| stringInput.equalsIgnoreCase("Unlimited")) {
					correctInput = true;
					customer.addDataPlan(testProvider.getDataPlan(stringInput), stringInput);
				} else {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input!");
				sc.nextLine();
			}
		} while (!correctInput);
		testProvider.writeToFile(customer);
		menu();
	}
}
