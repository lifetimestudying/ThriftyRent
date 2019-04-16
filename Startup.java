/* COSC 1295 | Advance Programming
 * 2019 Semester 1
 * Assignment 1
 * Student Name: Xinchen Wang
 * Student #: s3743318
 * Due Date: 14/04/2019, 23:59
*/

public class Startup {

	public static void main(String[] args) {
		
		ThriftyRentSystem menu = new ThriftyRentSystem();
		
		//Start of all sample data
		//sample date data for testing purpose
		String date1 = "01/01/2019";
		String date2 = "06/01/2019";
		String date3 = "02/02/2019";
		String date4 = "06/02/2019";
		String date5 = "15/04/2019";

		String[] values1 = date1.split("/");
		int day1 = Integer.parseInt(values1[0]);
		int month1 = Integer.parseInt(values1[1]);
		int year1 = Integer.parseInt(values1[2]);
		
		String[] values2 = date2.split("/");
		int day2 = Integer.parseInt(values2[0]);
		int month2 = Integer.parseInt(values2[1]);
		int year2 = Integer.parseInt(values2[2]);
		
		String[] values3 = date3.split("/");
		int day3 = Integer.parseInt(values3[0]);
		int month3 = Integer.parseInt(values3[1]);
		int year3 = Integer.parseInt(values3[2]);
		
		String[] values4 = date4.split("/");
		int day4 = Integer.parseInt(values4[0]);
		int month4 = Integer.parseInt(values4[1]);
		int year4 = Integer.parseInt(values4[2]);
		
		String[] values5 = date5.split("/");
		int day5 = Integer.parseInt(values5[0]);
		int month5 = Integer.parseInt(values5[1]);
		int year5 = Integer.parseInt(values5[2]);
		
		DateTime date_1 = new DateTime(day1, month1, year1);
		DateTime date_2 = new DateTime(day2, month2, year2);
		DateTime date_3 = new DateTime(day3, month3, year3);
		DateTime date_4 = new DateTime(day4, month4, year4);
		DateTime date_5 = new DateTime(day5, month5, year5);
		
		//sample vehicle data for testing purpose
		Vehicle car1 = new Car("zxv123", 2014, "Volkswagen", "Golf R", 4, "", "car");
		Vehicle van1 = new Van("asd776", 2010, "Toyota", "Hiace", 0, "", "van");
		Vehicle car2 = new Car("ghj884", 2017, "BMW", "M3", 4, "", "car");
		Vehicle car3 = new Car("lkj987", 2013, "Toyota", "Tarago", 7, "", "car");
		Vehicle van2 = new Van("eer345", 2009, "Toyota", "Hiace", 0, "", "van");
		Vehicle van3 = new Van("wer965", 2015, "Toyota", "Hiace", 0, "", "van");
		
		menu.myCar.add((Car)car1);
		menu.addRentalRecord("C_zxv123", "cus_005", date_1, 4);
		menu.returnVehicle("C_zxv123", date_2);
		
		menu.myCar.add((Car)car2);
		menu.addRentalRecord("ghj884", "cus_003", date_5, 6);
		
		menu.myVan.add((Van)van1);
		van1.completeMaintenance(date_3);
		menu.addRentalRecord("V_asd776", "cus_043", date_3, 5);
		menu.returnVehicle("V_asd776", date_4);
		
		menu.myCar.add((Car)car3);
		
		menu.myVan.add((Van)van2);
		van2.completeMaintenance(date_4);
		
		menu.myVan.add((Van)van3);
		van3.completeMaintenance(date_4);
		//end of all sample data
		
		menu.mainMenu(); // calling main menu
	}
}
