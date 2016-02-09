package utils.others;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class DeviceUtils {

	public static String getMyDeviceID(Context context) {
		String res = getGeneralIMEI(context) + "_" + getAndroidID(context)
				+ "_" + getWifiMAC(context) + "_" + getBluetoothMAC(context)
				+ "_" + getDeviceName();
		return res;
	}

	static String getGeneralIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		if (imei != null && imei.length() > 0)
			return imei;

		return "IMEI";
	}

	static String getAndroidID(Context context) {
		String m_androidId = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);
		if (m_androidId != null && m_androidId.length() > 0)
			return m_androidId;

		return "ANDROIDID";
	}

	static String getWifiMAC(Context context) {
		WifiManager m_wm = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		String m_wlanMacAdd = m_wm.getConnectionInfo().getMacAddress();
		if (m_wlanMacAdd != null && m_wlanMacAdd.length() > 0)
			return m_wlanMacAdd;

		return "WIFIMAC";
	}

	static String getBluetoothMAC(Context context) {
		BluetoothAdapter m_BluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		String m_bluetoothAdd = m_BluetoothAdapter.getAddress();
		if (m_bluetoothAdd != null && m_bluetoothAdd.length() > 0)
			return m_bluetoothAdd;

		return "BLUETOOTHMAC";
	}

	static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		} else {
			return capitalize(manufacturer) + " " + model;
		}
	}

	static String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

}
