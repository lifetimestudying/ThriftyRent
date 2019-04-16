/* COSC 1295 | Advance Programming
 * 2019 Semester 1
 * Assignment 1
 * Student Name: Xinchen Wang
 * Student #: s3743318
 * Due Date: 14/04/2019, 23:59
*/

import java.util.Scanner;

public class Van extends Vehicle{

	private String vanID;
	private String vanType;
	private int vanSeats;
	private String vanStatus;
	
	Scanner input = new Scanner(System.in);
	
	public Van(String ID, int year, String make, String model, int seats, String status, String type) {
		super(year, make, model);
		this.vanID = ID;
		this.vanSeats = seats;
		
		if (status.equalsIgnoreCase("")) {
			this.vanStatus = "Available";
		} else {
			this.vanStatus = status;
		}
		
		this.vanType = type;
	}

	@Override
	public String getVehicleID() {
		return "V_" + this.vanID;
	}

	@Override
	public int getVehicleSeats() {
		return this.vanSeats = 15;
	}
	
	@Override
	public String isVehicleStatus() {
		return this.vanStatus;
	}
	
	@Override
	public boolean performMaintenance() {
		setVehicleStatus("Under maintenance");

		return true;
	}
	
	@Override
	public boolean completeMaintenance(DateTime completionDate) {
		super.maintainDate.add(completionDate); // add to maintain list
		
		setVehicleStatus("Available");

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
		this.vanStatus = status;
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
						"Last maintenance date:\t" + getLastMaintainDate() + "\n" +
						"Rental Record: Empty\n";
		} else {
			detail_1 = "VehicleID:\t\t" + getVehicleID().toUpperCase() +"\n" +
						"Year:\t\t\t" + getVehicleYear() +"\n" +
						"Make:\t\t\t" + getVehicleMake() + "\n" +
						"Model:\t\t\t" + getVehicleModel() + "\n" +
						"Number of Seats:\t" + getVehicleSeats() + "\n" +
						"Status:\t\t\t" + isVehicleStatus() + "\n" +
						"Last maintenance date:\t" + getLastMaintainDate() + "\n" +
						"Rental Record:\n";
		}
		
		for (int i = 0; i < super.myRecord.size(); i++) {
			detail_2 = myRecord.get(i).getDetail();
			
			// check if van been rented before and print last record detail
			if (super.myRecord.size() > 1) {
				detail_2 = myRecord.get(i).getDetail() + myRecord.get(myRecord.size() - 2).getDetail();
			}
		}
		
		return detail_1 + detail_2;
	}
}
