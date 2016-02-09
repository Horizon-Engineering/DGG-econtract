package ca.dgg.e_contract;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import utils.others.FuncUtils;
import utils.others.TutorialUtils;

@SuppressLint("ResourceAsColor")
public class SplashActivity extends AwesomeSplash {

	int CIRCLE_REVEAL = 1000;
	int STAY_DURATION = 1000;
	int STROKE_DURATION = 1000;
	int FILL_DURATION = 1000;
	int TITLE_STAY_DURATION = 1000;

	public static String printKeyHash(Activity context) {
		PackageInfo packageInfo;
		String key = null;
		try {
			// getting application package name, as defined in manifest
			String packageName = context.getApplicationContext().getPackageName();

			// Retriving package info
			packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);

			Log.e("Package Name=", context.getApplicationContext().getPackageName());

			for (Signature signature : packageInfo.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				key = new String(Base64.encode(md.digest(), 0));

				// String key = new String(Base64.encodeBytes(md.digest()));
				Log.e("Key Hash=", key);
			}
		} catch (NameNotFoundException e1) {
			Log.e("Name not found", e1.toString());
		} catch (NoSuchAlgorithmException e) {
			Log.e("No such an algorithm", e.toString());
		} catch (Exception e) {
			Log.e("Exception", e.toString());
		}

		return key;
	}

	@Override
	public void initSplash(ConfigSplash configSplash) {
		FuncUtils.changeStatusBarColor(context);

		/* you don't have to override every property */

		printKeyHash(SplashActivity.this);
		// Customize Circular Reveal
		configSplash.setBackgroundColor(R.color.app_theme); // any color you
															// want
															// form colors.xml
		configSplash.setAnimCircularRevealDuration(CIRCLE_REVEAL); // int ms
		configSplash.setRevealFlagX(Flags.REVEAL_RIGHT); // or Flags.REVEAL_LEFT
		configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); // or Flags.REVEAL_TOP

		// Choose LOGO OR PATH; if you don't provide String value for path it's
		// logo by default

		// Customize Logo
		configSplash.setLogoSplash(R.drawable.logo_splash); // or any other
															// drawable
		configSplash.setAnimLogoSplashDuration(STAY_DURATION); // int ms
		configSplash.setAnimLogoSplashTechnique(Techniques.FadeInUp); // choose
																		// one
																		// form
																		// Techniques
																		// (ref:
																		// https://github.com/daimajia/AndroidViewAnimations)

		// Customize Path
		configSplash.setPathSplash(""); // set path String
		// configSplash.setOriginalHeight(400); // in relation to your svg
		// (path)
		// // resource
		// configSplash.setOriginalWidth(400); // in relation to your svg (path)
		// resource
		configSplash.setAnimPathStrokeDrawingDuration(STROKE_DURATION);
		configSplash.setPathSplashStrokeSize(3); // I advise value be <5
		configSplash.setPathSplashStrokeColor(R.color.black); // any color you
																// want form
																// colors.xml
		configSplash.setAnimPathFillingDuration(FILL_DURATION);
		configSplash.setPathSplashFillColor(R.color.dark_grey); // path object
		// filling color

		// Customize Title
		configSplash.setTitleSplash("DGG E-Contract");
		configSplash.setTitleTextColor(R.color.white);
		configSplash.setTitleTextSize(18f); // float value
		configSplash.setAnimTitleDuration(TITLE_STAY_DURATION);
		configSplash.setAnimTitleTechnique(Techniques.FlipInX);
		configSplash.setTitleFont("fonts/Roboto_Bold.ttf"); // provide string to
															// your
		// font located in
		// assets/fonts/

	}

	Context context = this;

	TutorialUtils funcUtils;

	@Override
	public void animationsFinished() {
		funcUtils = new TutorialUtils();
		funcUtils.showEntertainTutorialDialog(context);
	}

//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		// super.onBackPressed();
//		try {
//			funcUtils.dismissDialog();
//			finish();
//			overridePendingTransition(R.anim.slide_backward, 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
