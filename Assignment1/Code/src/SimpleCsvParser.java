import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class SimpleCsvParser {
	/*
	 * Counter for valid rows
	 */
	private static int countedRows = 0;
	/*
	 * counter for skipped rows
	 */
	private static int skippedRows = 0;
	private static boolean header = true;

	/*
	 * Read input csv file and collect data to log and write to output csv file
	 */
	public void readCSV(String csvPath, SimpleLogging logg) {
		logg.init();
		logg.logMsg("Date is in yyyy/mm/dd format");
		SimpleExceptions exceptionsInstance = SimpleExceptions.getExceptionsInstance();
		exceptionsInstance.createExcpLogger();
		exceptionsInstance.initExcpLogger();
		Reader in;
		BufferedWriter writer;
		String[] paths = csvPath.split("\\\\");
		String yyyymmdd = paths[paths.length-4]+"/"+paths[paths.length-3]+"/"+paths[paths.length-2];
		try {
			in = new FileReader(csvPath);
			writer = new BufferedWriter(new FileWriter("CustomerData.csv", true));
			CSVPrinter csvWriter = CSVFormat.EXCEL.print(writer);
			if (header) {
				writer.write("FirstName" + "," + "LastName" + "," + "StreetNo" + "," + "Street" + "," + "City" + ","
						+ "Province+" + "," + "Country" + "," + "PostalCode" + "," + "PhoneNumber" + "," + "Email");
				writer.newLine();
				header = false;
			}
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			String firstName = null;
			String lastName = null;
			String streetNo = null;
			String street = null;
			String city = null;
			String province = null;
			String country = null;
			String postalCode = null;
			String phoneNumber = null;
			String email = null;
			int i = 1;
			for (CSVRecord record : records) {
				if (i == 1) {
					i = 0;
					continue;
				}
				firstName = record.size() >= 1 ? record.get(0) : null;
				lastName = record.size() > 1 ? record.get(1) : null;
				streetNo = record.size() > 2 ? record.get(2) : null;
				street = record.size() > 3 ? record.get(3) : null;
				city = record.size() > 4 ? record.get(4) : null;
				province = record.size() > 5 ? record.get(5) : null;
				country = record.size() > 6 ? record.get(6) : null;
				postalCode = record.size() > 7 ? record.get(7) : null;
				phoneNumber = record.size() > 8 ? record.get(8) : null;
				email = record.size() > 9 ? record.get(9) : null;

				if (firstName == null || firstName.isEmpty()) {
					logg.logMsg(
							"First Name is missing in record" + " " + record.getRecordNumber() +" "+ "on date"+" " + yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (lastName == null || lastName.isEmpty()) {
					logg.logMsg(
							"Last Name is missing in record" + " " + record.getRecordNumber() +" "+ "on date " +" "+ yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (streetNo == null || streetNo.isEmpty()) {
					logg.logMsg(
							"streetNo is missing in record" + " " + record.getRecordNumber() +" "+ "on date "+" " + yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (street == null || street.isEmpty()) {
					logg.logMsg("street is missing in record" + " " + record.getRecordNumber() + " "+ "on date" + " "+yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (city == null || city.isEmpty()) {
					logg.logMsg("city is missing in record" + " " + record.getRecordNumber() + " "+"on date" + " "+yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (province == null || province.isEmpty()) {
					logg.logMsg(
							"province is missing in record" + " " + record.getRecordNumber() +" "+ "on date" +" " +yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (country == null || country.isEmpty()) {
					logg.logMsg("country is missing in record" + " " + record.getRecordNumber() +" "+ "on date" + " "+yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (postalCode == null || postalCode.isEmpty()) {
					logg.logMsg(
							"postalCode is missing in record" + " " + record.getRecordNumber() +" "+ "on date" +" " +yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (phoneNumber == null || phoneNumber.isEmpty()) {
					logg.logMsg(
							"phoneNumber is missing in record" + " " + record.getRecordNumber() + " "+"on date " + " "+yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (email == null || email.isEmpty()) {
					logg.logMsg("email is missing in record" + " " + record.getRecordNumber() +" "+ "on date " +" " +yyyymmdd);
					skippedRows += 1;
					continue;
				}
				if (firstName != null && lastName != null && street != null && streetNo != null && city != null
						&& province != null && country != null && postalCode != null && phoneNumber != null
						&& email != null && !firstName.isEmpty() && !lastName.isEmpty() && !street.isEmpty()
						&& !streetNo.isEmpty() && !city.isEmpty() && !province.isEmpty() && !country.isEmpty()
						&& !postalCode.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
					logg.logFineMsg(
							"This entry is fine in record" + " " + record.getRecordNumber() +" "+ "on date" + " "+yyyymmdd);
					csvWriter.printRecord(record);
					countedRows++;

				}
			}
			in.close();
			writer.close();
			logg.clearResource();
			exceptionsInstance.clearResource();
		} catch (IOException e) {
			exceptionsInstance.logException(Level.SEVERE, e.getMessage());
		}
	}

	/*
	 * Getter method for valid rows counter
	 */
	public static int getCountedRows() {
		return countedRows;
	}

	/*
	 * Getter method for skipped rows counter
	 */
	public static int getSkippedRows() {
		return skippedRows;
	}
}
