package ca.dgg.e_contract;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by zing on 10/5/2015.
 */

@ReportsCrashes(formKey = "", // will not be used
mailTo = "umayaeswaran@gmail.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.err_msg)
public class DGG extends MultiDexApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		ACRA.init(this);
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
