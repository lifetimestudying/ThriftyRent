/* COSC 1295 | Advance Programming
 * 2019 Semester 1
 * Assignment 1
 * Student Name: Xinchen Wang
 * Student #: s3743318
 * Due Date: 14/04/2019, 23:59
*/

public class RentRecord {

	private String customerID;
	private DateTime rentDate;
	private DateTime estReturnDate;
	private DateTime actuReturnDate;
	private int rentDays;
	private double rentalFee;
	private double lateFee;
	private String vehicleID;

	public RentRecord(String vID, String cID, DateTime rentDate, DateTime estReturnDate, DateTime actuReturnDate, double rentalFee, double lateFee) {
		this.vehicleID = vID;
		this.customerID = cID;
		this.rentDate = rentDate;
		this.estReturnDate = estReturnDate;
		this.actuReturnDate = actuReturnDate;
		this.lateFee = lateFee;
	}

	// getters
	public String getVehicleID() {
		return this.vehicleID;
	}

	public String getCustomerID() {
		return this.customerID;
	}

	public DateTime getRentDate() {
		return this.rentDate;
	}

	public String getRentalID() {
		return String.format("%s_%s_%s", getVehicleID().toUpperCase(), getCustomerID().toUpperCase(), getRentDate().getEightDigitDate());
	}
	public DateTime getEstReturnDate() {
		return this.estReturnDate;
	}

	public DateTime getActReturnDate() {
		return this.actuReturnDate;
	}

	public double getRentalFee() {
		return this.rentalFee;
	}

	public double getLateFee() {
		return this.lateFee;
	}

	public int getRentDays() {
		return this.rentDays;
	}

	public void setActReturnDate(DateTime date) {
		this.actuReturnDate = date;
	}

	public void setRentalFee(double rFee) {
		this.rentalFee = rFee;
	}

	public void setLateFee(double lFee) {
		this.lateFee = lFee;
	}

	public String toString() {
		String aReturn = "none";
		String rFee = "none";
		String lFee = "none";

		if (this.actuReturnDate == null) {
			return String.format("%s:%s:%s:%s:%sf%s", getRentalID(), getRentDate(), getEstReturnDate(), 
					aReturn, rFee, lFee);
		} else {
			return String.format("%s:%s:%s:%s:%.2f%.2f", getRentalID(), getRentDate(), getEstReturnDate(),
					getActReturnDate(), getRentalFee(), getLateFee());
		}
	}

	public String getDetail() {
		String detail = "";

		if (getActReturnDate() == null) {
			detail = "Record ID:\t\t" + getRentalID() + "\n" +
					"Rent Date:\t\t" + getRentDate() + "\n" +
					"Estimate Return Date:\t" + getEstReturnDate() + "\n";
		} else {
			detail = "---------------------------------------\n" +
					"Record ID:\t\t" + getRentalID() + "\n" +
					"Rent Date:\t\t" + getRentDate() + "\n" +
					"Estimate Return Date:\t" + getEstReturnDate() + "\n" +
					"Actural Return Date:\t" + getActReturnDate() + "\n" +
					"Rental Fee:\t\t" + getRentalFee() + "\n" +
					"Late Fee:\t\t" + getLateFee() + "\n";
		}

		return detail;
	}
}
