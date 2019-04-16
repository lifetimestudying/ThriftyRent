/* COSC 1295 | Advance Programming
 * 2019 Semester 1
 * Assignment 1
 * Student Name: Xinchen Wang
 * Student #: s3743318
 * Due Date: 14/04/2019, 23:59
*/

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Vehicle {

	private int _Year;
	private String _Make;
	private String _Model;
	private double rentalFee;
	private double lateFee;

	ArrayList<RentRecord> myRecord = new ArrayList<RentRecord>(10);
	ArrayList<DateTime> maintainDate = new ArrayList<DateTime>();
	
	Scanner input = new Scanner(System.in);
	
	//constructor
	public Vehicle(int year, String make, String model) {
		this._Year = year;
		this._Make = make;
		this._Model = model;
	}

	public abstract String getVehicleID();
	public abstract int getVehicleSeats();
	public abstract String isVehicleStatus();
	public abstract boolean performMaintenance();
	public abstract boolean completeMaintenance(DateTime completionDate);
	public abstract void setVehicleStatus(String status);
	public abstract String getDetail();
	public abstract DateTime getLastMaintainDate();
	
	public int getVehicleYear() {
		return this._Year;
	}
	
	public String getVehicleMake() {
		return this._Make;
	}

	public String getVehicleModel() {
		return this._Model;
	}
	
	public int getLastRecord() {
		int lastRecord = 0;
		
		lastRecord = myRecord.size() - 1;
		
		return lastRecord;
	}
	
	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		DateTime estReturn = new DateTime(rentDate, numOfRentDay);
		DateTime actReturn = null;
		
		//check minimum & maximum rent days for car 
		if (this instanceof Car){	
			if (rentDate.getNameOfDay().equalsIgnoreCase("friday") || rentDate.getNameOfDay().equalsIgnoreCase("saturday") ) {
				if (numOfRentDay < 3 && numOfRentDay > 14) {
					System.out.println("Rental day on Friday and Saturday is minimum 3 days and maximum 14 days.");
				}
			} else {
				if (numOfRentDay < 2 && numOfRentDay > 14) {
					System.out.println("Rental day on Sunday to Thursday is minimum 2 days and maximum 14 days.");
				}
			}
		}
			
		//check minimum & maximum rent days for van
		if (this instanceof Van) {
			if (numOfRentDay < 1 && numOfRentDay > 12) {
				System.out.println("Maximum rental day for van is 12 days.");
			}
		}
		
		// check if vehicle is available
		if (isVehicleStatus().equalsIgnoreCase("Available")) {
			// replace first record with last record list when record achieves 10
			if (myRecord.size() == 10) {
				myRecord.remove(0); // remove the record on the first index from the list
				myRecord.add(new RentRecord (getVehicleID(), customerId, rentDate, estReturn, actReturn, rentalFee, lateFee)); // add to rental record list
				
				System.out.println("Vehicle rented successful.");
				setVehicleStatus("Rented");
			
			}  else if (myRecord.size() < 10) {
				myRecord.add(new RentRecord (getVehicleID(), customerId, rentDate, estReturn, actReturn, rentalFee, lateFee)); // add to rental record list
				
				System.out.println("Vehicle rented successful.");
				setVehicleStatus("Rented");
			}
			
		} else {
			System.out.printf("Vehicle %s not available", getVehicleID().toUpperCase());
		}
		
		return true;
	}
	
	public boolean returnVehicle(DateTime returnDate) {
		if (isVehicleStatus().equalsIgnoreCase("rented")) {	
			if (this instanceof Car) {
				if (getVehicleSeats() == 4) {
					rentalFee = 78 * DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getRentDate());
					
					// check if late fee apply
					if (DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getEstReturnDate()) <= 0) {
						lateFee = 0;
					} else {
						lateFee = 78 * 1.25 * DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getEstReturnDate()); // calculate late fee for each late day
					}
	
					// update record list
					myRecord.get(getLastRecord()).setActReturnDate(returnDate);
					myRecord.get(getLastRecord()).setRentalFee(rentalFee);
					myRecord.get(getLastRecord()).setLateFee(lateFee);
					
				} else if (getVehicleSeats() == 7) {
					rentalFee = 113 * DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getRentDate());
					
					// check if late fee apply
					if (DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getEstReturnDate()) <= 0) {
						lateFee = 0;
					} else {
						lateFee = 113 * 1.25 * DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getEstReturnDate()); // calculate late fee for each late day
					}

					// update record list
					myRecord.get(getLastRecord()).setActReturnDate(returnDate);
					myRecord.get(getLastRecord()).setRentalFee(rentalFee);
					myRecord.get(getLastRecord()).setLateFee(lateFee);
				}
			}
			
			if (this instanceof Van) {
				rentalFee = 235 * DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getRentDate());
				
				// check if late fee apply
				if (DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getEstReturnDate()) <= 0) {
					lateFee = 0;
				} else {
					lateFee = 299 * DateTime.diffDays(returnDate, myRecord.get(getLastRecord()).getEstReturnDate()); // calculate late fee for each late day
				}
				
				
				// update record list
				myRecord.get(getLastRecord()).setActReturnDate(returnDate);
				myRecord.get(getLastRecord()).setRentalFee(rentalFee);
				myRecord.get(getLastRecord()).setLateFee(lateFee);
			}
			
			setVehicleStatus("Available");
			
			System.out.println("Vehicle returned successful.");
			
		} else {
			System.out.println("Cannot return vehicle not been rented or under maintenance.");
		}
		
		return true;
	}
	
	public String toString() {
		return String.format("%s:%s:%s:%s:%s:%s", getVehicleID().toUpperCase(),
				getVehicleYear(), getVehicleMake(), getVehicleModel(), 
				getVehicleSeats(),isVehicleStatus());
	}



}
