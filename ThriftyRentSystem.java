/* COSC 1295 | Advance Programming
 * 2019 Semester 1
 * Assignment 1
 * Student Name: Xinchen Wang
 * Student #: s3743318
 * Due Date: 14/04/2019, 23:59
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;

public class ThriftyRentSystem {
	
	private boolean Quit = false;
	private String addID; //define string for adding vehicle ID
	private Vehicle vehicle;
	private RentRecord rentRecord;

	//set car list
	ArrayList<Car> myCar = new ArrayList<Car>(50);
	
	//set van list
	ArrayList<Van> myVan = new ArrayList<Van>(50);
	
	//set rental record list
//	ArrayList<RentRecord> myRecord = new ArrayList<RentRecord>();
	
	Scanner input = new Scanner(System.in);

	ThriftyRentSystem() {
		
	}

	// main menu
	public void mainMenu() {
		System.out.println("**** ThriftyRent SYSTEM MAIN MENU ****\n");

		System.out.println("Add Vehicle:\t\t\t1");
		System.out.println("Rent Vehicle:\t\t\t2");
		System.out.println("Return Vehicle:\t\t\t3");
		System.out.println("Vehicle Maintenance:\t\t4");
		System.out.println("Complete Maintenance:\t\t5");
		System.out.println("Displa All Vehicles:\t\t6");
		System.out.println("Exit Program:\t\t\t7");
		System.out.println("Enter Your Choice:");
		int userChoice = input.nextInt();
		input.nextLine();
		
		switch (userChoice) {
			case 1:
				addVehicleMenu();
				break;
			case 2:
				rentVehicleMenu();
				break;
			case 3:
				returnVehicleMenu();
				break;
			case 4:
				performMaintenanceMenu();
				break;
			case 5:
				completeMaintenanceMenu();
				break;
			case 6:
				display();
				mainMenu();
				break;
			case 7:
				input.close();
				System.out.println("Bye Bye!");
				break;
	
			default:
				System.out.println("Please enter a valid number");
				mainMenu();
				break;
		}
	}

	//add vehicle menu
	public void addVehicleMenu() {
		String addType;

		while(!Quit) {
			System.out.println("*** ThriftyRent ADD VEHICLE MENU***\n");

			System.out.println("Enter vehicle Type: ");
			addType = input.nextLine();

			//check if user entered valid vehicle type
			while (!addType.equalsIgnoreCase("car") && !addType.equalsIgnoreCase("van")) {
				System.out.println("Only car or van can be entered");
				addType = input.nextLine();
			}
			
			System.out.println("Enter car ID: ");
			addID = input.nextLine();
//			checkID(addID); // perform ID check to prevent enter existing ID
			
			System.out.println("Enter vehicle year: ");
			int addYear = input.nextInt();
			input.nextLine();
			
			System.out.println("Enter vehicle brand: ");
			String addMake = input.nextLine();
			
			System.out.println("Enter vehicle Model: ");
			String addModel = input.nextLine();
			
			//add objects to car list
			if (addType.equalsIgnoreCase("car")) {
				addCar(addID, addYear, addMake, addModel, addType);	
			}

			//add objects to van list
			if (addType.equalsIgnoreCase("van")) {				
				addVan(addID, addYear, addMake, addModel, addType);
			}

			mainMenu();
		}
	}

	//rent vehicle menu
	public void rentVehicleMenu() {
		while (!Quit) {
			System.out.println("*** ThriftyRent RENT VEHICLE MENU***\n");
			
			System.out.println("Enter Vehicle ID:");
			String addVehicleID = input.nextLine();
			
			System.out.println("Enter Customer ID:");
			String addCustomerID = input.nextLine();
			
			System.out.println("Enter rent date (dd/mm/yyyy):");
			String addRentDate = input.nextLine();
			
			System.out.println("How many days:");
			int addDays = input.nextInt();
			input.nextLine();
			
			String[] values = addRentDate.split("/");
			int day = Integer.parseInt(values[0]);
			int month = Integer.parseInt(values[1]);
			int year = Integer.parseInt(values[2]);
			
			DateTime addDate = new DateTime(day, month, year);
			
			addRentalRecord(addVehicleID, addCustomerID, addDate, addDays);
			
			mainMenu();
		}
	}
	
	//return vehicle menu
	public void returnVehicleMenu() {
		while (!Quit) {
			System.out.println("*** ThriftyRent RETURN VEHICLE MENU***\n");
			
			System.out.println("Enter Vehicle ID:");
			String addReturnID = input.nextLine();
			
			System.out.println("Enter return date (dd/mm/yyyy):");
			String addReturnDate = input.nextLine();
			
			String[] values = addReturnDate.split("/");
			int day = Integer.parseInt(values[0]);
			int month = Integer.parseInt(values[1]);
			int year = Integer.parseInt(values[2]);
			
			DateTime returnDate = new DateTime(day, month, year);
			
			returnVehicle(addReturnID, returnDate);
			
			mainMenu();
		}
	}
	
	//perform maintenance menu
	public void performMaintenanceMenu() {
		while (!Quit) {
			System.out.println("Enter Vehicle ID:");
			String addMaintainID = input.nextLine();
			
			// update value status in myCar list
			for (int i = 0; i < myCar.size(); i++) {	
				if (myCar.get(i).getVehicleID().equalsIgnoreCase(addMaintainID)) {
					if (myCar.get(i).isVehicleStatus().equalsIgnoreCase("available")) {	
						myCar.get(i).performMaintenance();
						System.out.printf("Vehicle %s is now under maintenance.\n", myCar.get(i).getVehicleID().toUpperCase());
					} else {
						System.out.println("Unable to perform maintenance.\n");
					}
				}
			}
			
			// update value status in myVan list
			for (int i = 0; i < myVan.size(); i++) {	
				if (myVan.get(i).getVehicleID().equalsIgnoreCase(addMaintainID)) {
					if (myVan.get(i).isVehicleStatus().equalsIgnoreCase("available")) {	
						myVan.get(i).performMaintenance();
						System.out.printf("Vehicle %s is now under maintenance\n", myVan.get(i).getVehicleID().toUpperCase());
					} else {
						System.out.println("Unable to perform maintenance.\n");
					}
				}
			}
			
			mainMenu();
		}
	}
	
	//complete maintenance menu
	public void completeMaintenanceMenu() {
		String[] values;
		while (!Quit) {
			System.out.println("Enter Vehicle ID:");
			String addCompleteID = input.nextLine();
			
			System.out.println("Maintenance completion date (dd/mm/yyyy):");
			String addMaintainID = input.nextLine();
			
			// change date from string to integer
			values = addMaintainID.split("/");
			int day = Integer.parseInt(values[0]);
			int month = Integer.parseInt(values[1]);
			int year = Integer.parseInt(values[2]);
			
			DateTime completeDate = new DateTime(day, month, year);
			
			// complete maintain in car list
			for (int i = 0; i < myCar.size(); i++) {	
				if (myCar.get(i).getVehicleID().equalsIgnoreCase(addCompleteID)) {
					if (myCar.get(i).isVehicleStatus().equalsIgnoreCase("under maintenance")) {
						myCar.get(i).completeMaintenance(completeDate);		
					} else {
						System.out.println("Cannot complete maintain if vehicle not under maintenance.\n");
					}
				}
			}
			
			// complete maintain in van list
			for (int i = 0; i < myVan.size(); i++) {	
				if (myVan.get(i).getVehicleID().equalsIgnoreCase(addCompleteID)) {
					if (myVan.get(i).isVehicleStatus().equalsIgnoreCase("under maintenance")) {
						myVan.get(i).completeMaintenance(completeDate);		
					} else {
						System.out.println("Cannot complete maintain if vehicle not under maintenance.\n");
					} 
				}		
			}
			
			mainMenu();
		}
	}
		
	// check ID method
	public void checkID(String Id) {
		for (int i = 0; i < myCar.size(); i++) {
			while (!myCar.get(i).getVehicleID().equalsIgnoreCase("c_" + Id)) {
				System.out.println("ID existed. Enter another ID: ");
				addID = input.nextLine();
			}
		}

		for (int i = 0; i < myVan.size(); i++) {
			while (myVan.get(i).getVehicleID().equalsIgnoreCase("v_" + Id)) {
				System.out.println("ID existed. Enter another ID: ");
				addID = input.nextLine();
			}
		}
		addID = input.nextLine();
	}
	
	// add car method
	public void addCar(String ID, int carYear, String make, String model, String type) {
		System.out.println("Enter car Seats: ");
		int addSeats = input.nextInt();
		input.nextLine();
		
		while (addSeats != 4 && addSeats != 7) {
			System.out.println("Car seats must be either 4 or 7");
			addSeats = input.nextInt();
			input.nextLine();
		}
		
		//add to car list
		Vehicle car = new Car(ID, carYear, make, model, addSeats, "", type);
		
		if (myCar.size() <= 50) {
			myCar.add((Car) car);
			System.out.printf("Car %s added successful\n", ID);
		} else {
			System.out.println("Excced limits!\n");
		}	
	}
	
	// add van method
	public void addVan(String ID, int vanYear, String make, String model, String type) {
		System.out.println("Enter last maintenance date (dd/mm/yyyy):");
		String addDate = input.next();
		
		// change date from string to integer
		String[] values = addDate.split("/");
		int day = Integer.parseInt(values[0]);
		int month = Integer.parseInt(values[1]);
		int year = Integer.parseInt(values[2]);
		
		//add to van list
		Vehicle van = new Van(ID, vanYear, make, model, 0, "", type);
		
		DateTime completeDate = new DateTime(day, month, year);
		
		//add maintenance date to van
		for (int i = 0; i < myVan.size(); i++) {	
			myVan.get(i).completeMaintenance(completeDate);		
		}
		
		if (myVan.size() <= 50) {
			myVan.add((Van) van);
			System.out.printf("Van %s added successfully\n", ID);
		} else {
			System.out.println("Excced limits!\n");
		}
	}
	
	// add rental record
	public void addRentalRecord(String vID, String cID, DateTime date, int days) {
		for (int i = 0; i < myCar.size(); i++) {
			if (myCar.get(i).getVehicleID().equalsIgnoreCase(vID)) {
				myCar.get(i).rent(cID, date, days);
			}
		}
		
		for (int i = 0; i < myVan.size(); i++) {
			if (myVan.get(i).getVehicleID().equalsIgnoreCase(vID)) {
				myVan.get(i).rent(cID, date, days);
			}
		}
	}
	
	//return Vehicle
	public void returnVehicle(String vID, DateTime returnDate) {
		for (int i = 0; i < myCar.size(); i++) {
			if (myCar.get(i).getVehicleID().equalsIgnoreCase(vID)) {
				myCar.get(i).returnVehicle(returnDate);
			}
		}
		
		for (int i = 0; i < myVan.size(); i++) {
			if (myVan.get(i).getVehicleID().equalsIgnoreCase(vID)) {
				myVan.get(i).returnVehicle(returnDate);
			}
		}
	}
	
	//display all vehicle details
	public void display() {
		System.out.println("Maximum 50 cars and 50 vans can be stored, currently has " 
							+ myCar.size() + " Cars and " + myVan.size() + " Vans.");
		System.out.println("All Vehicles:\n");

		for(Vehicle car : myCar) {
			System.out.println(car.getDetail());
		}
			
		for(Vehicle van : myVan) {
			System.out.println(van.getDetail());
		}
	}
}
