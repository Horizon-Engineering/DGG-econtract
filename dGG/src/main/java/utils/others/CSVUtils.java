package utils.others;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.pojos.FieldPOJO;

public class CSVUtils {

	public static String FILE_END_USER = "End User Data.csv";
	public static String FILE_SUPER_USER = "Super User Data.csv";
	public static String FILE_INTERAC_E_TRANSFER = "Interac e-transfer.csv";
	private static final String COMMA_DELIMITER = ",";

	/*static String[] endUserColumns = { "Firstsssss Name", "Last Name", "Password", "Postal Code", "Email", "Phone",
			"Permission to notify of each CRY", "Gender", "Age Group", "Marital Status", "Education", "Employment",
			"Union Member", "Union Name", "Union Local", "Political Party / affiliation name", "Military service",
			"Immigration Status", "Town of Birth", "Current town of residence", "Current Residence",
			"Type of Residence", "Number of members in household", "Number of Dependants",
			"Length of time at this Residence", "First Language", "Number of Additional Languages",
			"Self-identified ethnic background", "Self-identified religion " };*/

	private static final String FILE_HEADER = "data_point_repository_id, name, description, mandatory,comment, value_type";

	//modified the array according to the csv file
	//End user data is separated by :, if you split by ":" you will get each column data
	private static String[] endUserData = {
			"1:TEST....Postal Code:Postal Code:1:mandatory:String",
			"2:Pass phrase:Pass phrase:1:mandatory:String",
			"3:User name:User name:1:mandatory:String",
			"4:Phone number:Phone number:1:mandatory:String",
			"5:User identifier:User identifier:1:mandatory:String",
			"6:Email:Email:1:mandatory:String",
			"7:Permission to notify of each CRY:Permission to notify of each CRY:1:mandatory:Boolean",
			"8:Entitled to vote Y/N:Entitled to vote Y/N:1:mandatory:Boolean",
			"9:Voted before Federal,Provincial,Municipal:Voted before Federal,Provincial Municipal:1:mandatory:Boolean",
			"10:Age Group under-18 19-30 31-45 46-60 61+:Age Group under-18 19-30 31-45 46-60 61+:1:mandatory:String",
			"11:Gender:Gender:1:mandatory:String",
			"12:Creation Date:Creation Date:1:mandatory:Date",
			"13:How did you find us:How did you find us:0:not mandatory:String",
			"14:Marital Status:Marital Status:0:not mandatory:String",
			"15:Education level:Education level:0:not mandatory:String",
			"16:Home Town:Home Town:0:not mandatory:String",
			"17:Renter/owner:Renter/owner:0:not mandatory:String",
			"18:Residence Type(Single family,semi-detached,multi-unit):Residence Type(Single family,semi-detached,multi-unit):0:not mandatory:String",
			"19:Employment (part-time,full-time,self-employed,retired,student,unemployed):Employment (part-time,full-time,self-employed,retired,student,unemployed):0:not mandatory:String",
			"20:Union Local:Union Local:0:not mandatory:String",
			"21:Union employee Y/N:Union employee Y/N:0:not mandatory:Boolean",
			"22:Military personel:Military personel:0:not mandatory:Boolean",
			"23:Veteran Y/N:Veteran Y/N:0:not mandatory:Boolean",
			"24:Number of dependant:Number of dependant:0:not mandatory:Integer",
			"25:Number of children at residence:Number of children at residence:0:not mandatory:Integer",
			"26:Number of children:Number of children:0:not mandatory:Integer",
			"27:Number of years at this address:Number of years at this address:0:not mandatory:Integer",
			"28:Number of people in residence:Number of people in residence:0:not mandatory:Integer",
			"29:Number of Additional languages:Number of Additional languages:0:not mandatory:Integer",
			"30:First language:First language:0:not mandatory:String"
	};

	static String[] superUserColumns = { "Organisation Name", "Contract City", "Contract Province", "Contract Country",
			"Date", "Contract Number", "Billing Name", "Billing Phone", "Billing Mailing Address",
			"Billing Bill Address", "Billing Email", "Billing Cotact Preference", "Party", "Union", "Corporate",
			"Client Name", "Client Phone", "Client Mailing Address", "Client Billing Address", "Client Email",
			"Client Website", "Purchaser/ Client Title", "Client contact Name", "Client contact Phone",
			"Client contact Mailing Address", "Client contact Billing Address", "Client contact Email",
			"Client contact Preference", "Client alternate Name", "Client alternate Phone",
			"Client alternate Mailing Address", "Client alternate Billing Address", "Client alternate Email",
			"Client alternate Preference", "Signature" };

	static String[] emailPayColumns = { "E-Mail ID", "Password" };

	public static void configCSVs(Context context) {
//		final List<FieldPOJO> endUserArray = new ArrayList<>();
//		for (String values : endUserData) {
//			String[] split = values.split(":");
//			endUserArray.add(new FieldPOJO(Long.parseLong(split[0]), split[1], split[2], split[3], split[4], split[5]));
//		}

		try {
//			if (!isCSVExists(context, FILE_END_USER)) {
//				generateEndUserCsvFile(FILE_END_USER, endUserArray);
//			}
//			if (!isCSVExists(context, FILE_SUPER_USER)) {
//				generateCsvFile(FILE_SUPER_USER, Arrays.asList(superUserColumns));
//			}
			if (!isCSVExists(context, FILE_INTERAC_E_TRANSFER)) {
				generateCsvFile(FILE_INTERAC_E_TRANSFER, Arrays.asList(emailPayColumns));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static boolean isCSVExists(Context context, String fileName) {
		try {
			File file = new File(Environment.getExternalStorageDirectory(), fileName);
			if (file.exists())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getEndUserCSVFilePath(Context context) {
		try {
			File file = new File(Environment.getExternalStorageDirectory(), FILE_END_USER);
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSuperUserCSVFilePath(Context context) {
		try {
			File file = new File(Environment.getExternalStorageDirectory(), FILE_SUPER_USER);
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getMailFileFilePath(Context context) {
		try {
			File file = new File(Environment.getExternalStorageDirectory(), FILE_INTERAC_E_TRANSFER);
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getContractPDFFilePath(Context context) {
		try {
			return DataUtils.getInstance(context).getSPref(context).getString(DataUtils.PREF_CONTRACT_FILE_PATH, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// static long readZingLogSize(Context context, String fileName) {
	// try {
	// File file = new File(context.getFilesDir(), fileName);
	// long length = file.length();
	// length = length / 1024;
	// return length;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return 0;
	// }

	private static void generateCsvFile(String sFileName, List<String> lstColumns) {
		try {
			File root = Environment.getExternalStorageDirectory();
			File gpxfile = new File(root, sFileName);
			if (gpxfile.exists())
				gpxfile.delete();
			gpxfile.createNewFile();
			FileWriter writer = new FileWriter(gpxfile);
			for (int i = 0; i < lstColumns.size(); i++) {
				if (i == 0) {
					writer.append(lstColumns.get(i));
				} else {
					writer.append(',');
					writer.append(lstColumns.get(i));
				}
			}
			writer.append('\n');
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//modified the generate user csv file acording the to the reqirements
	private static void generateEndUserCsvFile(String sFileName, List<FieldPOJO> lstColumns) {

		try {
			File root = Environment.getExternalStorageDirectory();
			File gpxfile = new File(root, sFileName);
			if (gpxfile.exists())
				gpxfile.delete();
			gpxfile.createNewFile();

			FileWriter writer = new FileWriter(gpxfile);
			writer.append(FILE_HEADER.toString());
			for(FieldPOJO fieldPOJO : lstColumns){
				writer.append(fieldPOJO.csvString());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String fixCSVString(String data) {
		if(data.contains(",")) {
			return "\"" + data + "\"";
		}
		return data;
	}

	public static void writeCSV(Context context, ArrayList<String> lstValues, String fileName) {

		try {
			// String existingInfo = readCSV(context, fileName);
			File root = Environment.getExternalStorageDirectory();
			File gpxfile = new File(root, fileName);
			FileWriter writer = new FileWriter(gpxfile, true);
			writer.append(FILE_HEADER);
			for (int i = 0; i < lstValues.size(); i++) {
					writer.append('\n');
					writer.append(lstValues.get(i).replace(":", ","));
			}
			writer.append('\n');
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resetCSV(Context context, String fileName) {

		try {
			File root = Environment.getExternalStorageDirectory();
			File gpxfile = new File(root, fileName);
			if (gpxfile.exists())
				gpxfile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String readCSV(Context context, String fileName) {
		try {
			File file = new File(context.getFilesDir(), fileName);
			StringBuilder text = new StringBuilder();
			BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			String line;

			while ((line = br.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
			br.close();
			return text.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
