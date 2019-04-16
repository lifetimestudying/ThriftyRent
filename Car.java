/* COSC 1295 | Advance Programming
 * 2019 Semester 1
 * Assignment 1
 * Student Name: Xinchen Wang
 * Student #: s3743318
 * Due Date: 14/04/2019, 23:59
*/

import java.util.Scanner;

public class Car extends Vehicle {

	Scanner input = new Scanner(System.in);
	
	private String carID;
	private int carSeats;
	private String carStatus;
	private String carType;
	
	//Constructor
	public Car(String ID, int year, String make, String model, int seats, String status, String type) {
		super(year, make, model);
		this.carID = ID;
		this.carSeats = seats;
		
		if (status.equalsIgnoreCase("")) {
			this.carStatus = "Available";
		} else {
			this.carStatus = status;
		}
		
		this.carType = type;
	}
	
	@Override
	public String getVehicleID() {
		return "C_" + this.carID;
	}	
	
	@Override
	public int getVehicleSeats() {
		return this.carSeats;
	}
	
	@Override
	public String isVehicleStatus() {
		return this.carStatus;
	}
	
	@Override
	public boolean completeMaintenance(DateTime completionDate) {
		super.maintainDate.add(completionDate); // add to maintain list
		
		setVehicleStatus("Available");
		
		return true;
	}
	
	@Override
	public boolean performMaintenance() {
		setVehicleStatus("Under maintenance");

		return true;
	}
	
	@Override
	public DateTime getLastMaintainDate() {
		DateTime lstMaintain = null;

		lstMaintain = super.maintainDate.get(super.maintainDate.size() - 1);
		
		return lstMaintain;
	}
	
	@Override
	public void setVehicleStatus(String status) {
		this.carStatus = status;
	}
	
	@Override
	public String getDetail() {
		String detail_1 = "";
		String detail_2 = "";
		
		// print empty if no record in the list
		if (super.myRecord.size() == 0) {
			detail_1 = "VehicleID:\t\t" + getVehicleID().toUpperCase() +"\n" +
						"Year:\t\t\t" + getVehicleYear() +"\n" +
						"Make:\t\t\t" + getVehicleMake() + "\n" +
						"Model:\t\t\t" + getVehicleModel() + "\n" +
						"Number of Seats:\t" + getVehicleSeats() + "\n" +
						"Status:\t\t\t" + isVehicleStatus() + "\n" +
						"Rental Record: Empty\n";
		} else {
			detail_1 = "VehicleID:\t\t" + getVehicleID().toUpperCase() +"\n" +
						"Year:\t\t\t" + getVehicleYear() +"\n" +
						"Make:\t\t\t" + getVehicleMake() + "\n" +
						"Model:\t\t\t" + getVehicleModel() + "\n" +
						"Number of Seats:\t" + getVehicleSeats() + "\n" +
						"Status:\t\t\t" + isVehicleStatus() + "\n" +
						"Rental Record:\n";
		}
		
		for (int i = 0; i < super.myRecord.size(); i++) {
			detail_2 = myRecord.get(i).getDetail();
			
			// check if car been rented before and print last record detail
			if (super.myRecord.size() > 1) {
				detail_2 = myRecord.get(i).getDetail() + myRecord.get(myRecord.size() - 2).getDetail();
			}
		}
		
		return detail_1 + detail_2;
	}
}
