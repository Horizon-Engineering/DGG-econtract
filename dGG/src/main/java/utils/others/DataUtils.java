package utils.others;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DataUtils {

	Context context;

	public DataUtils(Context ctx) {
		context = ctx;
	}

	static DataUtils currentInstance;

	public static DataUtils getInstance(Context cnContext) {
		// TODO Auto-generated constructor stub
		if (currentInstance == null) {
			currentInstance = new DataUtils(cnContext);
		}
		return currentInstance;
	}

	SharedPreferences pref;

	public SharedPreferences getSPref(Context context) {
		if (pref == null) {
			pref = PreferenceManager.getDefaultSharedPreferences(context);
		}
		return pref;
	}

	public static final String PREF_CONTRACT_FILE_PATH = "contract_file_path";
	public static final String PREF_AMOUNT = "contract_amount";

	public String readFromfile(Context context, String fileName) {
		StringBuilder returnString = new StringBuilder();
		InputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader input = null;
		try {
			fIn = context.getResources().getAssets().open(fileName, Context.MODE_WORLD_READABLE);
			isr = new InputStreamReader(fIn);
			input = new BufferedReader(isr);
			String line = "";
			while ((line = input.readLine()) != null) {
				returnString.append(line);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
				if (input != null)
					input.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return returnString.toString();
	}

	public static final String PREF_END_USER_DATA = "end_user_data";
	public static final String PREF_SUPER_USER_DATA = "super_user_data";

	public String convertToString(ArrayList<String> list) {

		StringBuilder sb = new StringBuilder();
		String delim = "";
		for (String s : list) {
			sb.append(delim);
			sb.append(s);
			delim = ",";
		}
		return sb.toString();
	}

	public ArrayList<String> convertToArray(String key) {
		String string = getSPref(context).getString(key, "");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(string.split(",")));
		return list;
	}

}
