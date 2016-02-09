package utils.others;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import ca.dgg.e_contract.R;
import ca.dgg.e_contract.SplashActivity;

@SuppressLint("NewApi")
public class FuncUtils {

	public static boolean sendEmail(String to, String from, String subject, String message, String[] attachements)
			throws Exception {
		Mail mail = new Mail(from, "Hesolutions123");
		if (subject != null && subject.length() > 0) {
			mail.setSubject(subject);
		} else {
			mail.setSubject("Subject");
		}

		if (message != null && message.length() > 0) {
			mail.setBody(message);
		} else {
			mail.setBody("Message");
		}

		mail.setFrom(from);
		mail.setTo(new String[] { to });

		if (attachements != null) {
			for (String attachement : attachements) {
				if (attachement.trim().length() != 0)
					mail.addAttachment(attachement);
			}

		}
		try {
			return mail.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void changeStatusBarColor(
			Context context) {/*
								 * if (Build.VERSION.SDK_INT >=
								 * Build.VERSION_CODES.KITKAT) { Window w =
								 * ((Activity) context).getWindow();
								 * w.setFlags(WindowManager.LayoutParams.
								 * FLAG_TRANSLUCENT_STATUS,
								 * WindowManager.LayoutParams.
								 * FLAG_TRANSLUCENT_STATUS); final View
								 * decorView = w.getDecorView(); View view = new
								 * View(context); int actionBarHeight =
								 * getActionBarHeight(context); int
								 * statusBarHeight =
								 * getStatusBarHeight(context);
								 * view.setLayoutParams(new
								 * FrameLayout.LayoutParams(ViewGroup.
								 * LayoutParams.MATCH_PARENT, statusBarHeight));
								 * view.setBackgroundColor(context.getResources(
								 * ).getColor(R.color.app_theme_lighter));
								 * ((ViewGroup) decorView).addView(view); }
								 */
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			((Activity) context).getWindow().setStatusBarColor(context.getResources().getColor(R.color.colorPrimary));
		}
	}


	public static void showNotifyDialog(Context context, String msg) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("DGG Notice!");
		alertDialogBuilder.setMessage(msg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	

	public static void showNotifyDialogAndExit(final Context context, String msg) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setTitle("DGG Notice!");
		alertDialogBuilder.setMessage(msg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
				Intent in = new Intent(context, SplashActivity.class);
				in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				((Activity) context).startActivity(in);
				((Activity) context).finish();
				((Activity) context).overridePendingTransition(R.anim.slide_backward, 0);
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	final public static String PREFERENCE_VENDOR_DATA = "vendor_data";

	public static int[] color_list = { Color.parseColor("#012247"), Color.parseColor("#7DBD18"),
			Color.parseColor("#FFCD00"), Color.parseColor("#084B01"), Color.parseColor("#023B7A"),
			Color.parseColor("#FD6205"), Color.parseColor("#8C0253"), Color.parseColor("#04A093"),
			Color.parseColor("#CE963F") };

	public static boolean isValidEmail(CharSequence target) {
		if (TextUtils.isEmpty(target)) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
		}
	}

	public static String isValidET(Context context, EditText et, int errID) {
		try {
			String res = et.getText().toString();
			if (res != null && res.trim().length() != 0)
				return res.trim();
			else {
				if (errID != -1) {
					String errMsg = context.getResources().getString(errID);
					et.setError(errMsg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public interface FuncUtilsCommunicator {
		public void onYesClicked(int action, String json, String url);
	}

	public static void showYesOrNoDialog(Context context, String title, String message,
			final FuncUtilsCommunicator funcUtilsCommunicator, final int action, final String json, final String url) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

		// set title
		alertDialogBuilder.setTitle(title);

		// set dialog message
		alertDialogBuilder.setMessage(message).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
				funcUtilsCommunicator.onYesClicked(action, json, url);
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		// show it
		alertDialog.show();
	}

	public boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	Context context;
	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00 };

	public FuncUtils(Context ctx) {
		context = ctx;
	}

	static FuncUtils currentInstance;

	public static FuncUtils getInstance(Context cnContext) {
		// TODO Auto-generated constructor stub
		if (currentInstance == null) {
			currentInstance = new FuncUtils(cnContext);
		}
		return currentInstance;
	}

	/* Checking whether Internet avails or not */
	public static boolean isNetworkAvailable(final Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		boolean isConnectionAvailable = (activeNetworkInfo != null);
		// try {
		// if (isConnectionAvailable) {
		// if (LandingActivity.ripNoConnection != null)
		// LandingActivity.ripNoConnection.setVisibility(View.GONE);
		// } else {
		// if (LandingActivity.ripNoConnection != null) {
		// LandingActivity.ripNoConnection.setVisibility(View.VISIBLE);
		// LandingActivity.imgNoConnection.setOnClickListener(new
		// OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// LandingActivity.ripNoConnection.setOnRippleCompleteListener(new
		// OnRippleCompleteListener() {
		//
		// @Override
		// public void onComplete(RippleView rippleView) {
		// showToast(context, "Internet Connection not found!");
		// }
		// });
		// }
		// });
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return isConnectionAvailable;
	}

	// public static void shareMyText(Context ctx, String contentToBeShared,
	// boolean shouldTrackForDGGChips) {
	// shareMyTextWithCustomChooser(ctx, contentToBeShared,
	// shouldTrackForDGGChips);
	// }

	public static void shareBitmap(Context context, Bitmap bitmap, String fileName, String content) {
		try {
			File file = new File(context.getFilesDir(), fileName + ".png");
			FileOutputStream fOut = new FileOutputStream(file);
			bitmap.compress(CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
			file.setReadable(true, false);
			final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
			intent.putExtra(Intent.EXTRA_TEXT, content);
			intent.setType("image/png");
			((Activity) context).startActivityForResult(intent, 404);
			((Activity) context).overridePendingTransition(R.anim.slide_forward, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Bitmap createBitMap(String colorCode, String name) {
		try {
			Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888); // creates
																					// bmp
			bitMap = bitMap.copy(bitMap.getConfig(), true); // lets bmp to be
															// mutable
			Canvas canvas = new Canvas(bitMap); // draw a canvas in defined bmp

			Paint paint = new Paint();
			paint.setAntiAlias(true);
			// int randomColorIndex = new Random().nextInt(colors.length);
			// int randomColor = (colors[randomColorIndex]);
			paint.setColor(Color.parseColor(colorCode));
			paint.setStyle(Paint.Style.FILL);

			Paint paint1 = new Paint();
			paint1.setAntiAlias(true);
			paint1.setColor(Color.WHITE);
			paint1.setStyle(Paint.Style.FILL);
			paint1.setTextSize(60);
			paint1.setTextAlign(Align.CENTER);
			canvas.drawCircle(50, 50, 50, paint);
			int xPos = (canvas.getWidth() / 2);
			int yPos = (int) ((canvas.getHeight() / 2) - ((paint1.descent() + paint1.ascent()) / 2));
			String character = name.substring(0, 1).toUpperCase();
			canvas.drawText(character, xPos, yPos, paint1);
			return bitMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int taskTextView = 1;
	public static int taskEditText = 2;
	public static int taskButton = 3;

	public static void setRobotoRegular(Context context, View view, int task) {
		if (task == taskTextView) {
			TextView tv = (TextView) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Regular.ttf");
			tv.setTypeface(tf);
		} else if (task == taskEditText) {
			EditText tv = (EditText) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Regular.ttf");
			tv.setTypeface(tf);
		} else if (task == taskButton) {
			Button tv = (Button) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Regular.ttf");
			tv.setTypeface(tf);
		}
	}

	public static void setRobotoBold(Context context, View view, int task) {
		if (task == taskTextView) {
			TextView tv = (TextView) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Bold.ttf");
			tv.setTypeface(tf);
		} else if (task == taskEditText) {
			EditText tv = (EditText) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Bold.ttf");
			tv.setTypeface(tf);
		} else if (task == taskButton) {
			Button tv = (Button) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Bold.ttf");
			tv.setTypeface(tf);
		}
	}

	public static void setRobotoLight(Context context, View view, int task) {
		if (task == taskTextView) {
			TextView tv = (TextView) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Light.ttf");
			tv.setTypeface(tf);
		} else if (task == taskEditText) {
			EditText tv = (EditText) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Light.ttf");
			tv.setTypeface(tf);
		} else if (task == taskButton) {
			Button tv = (Button) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Light.ttf");
			tv.setTypeface(tf);
		}
	}

	public static void setRobotoMedium(Context context, View view, int task) {
		if (task == taskTextView) {
			TextView tv = (TextView) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Medium.ttf");
			tv.setTypeface(tf);
		} else if (task == taskEditText) {
			EditText tv = (EditText) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Medium.ttf");
			tv.setTypeface(tf);
		} else if (task == taskButton) {
			Button tv = (Button) view;
			Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Medium.ttf");
			tv.setTypeface(tf);
		}
	}

	public static boolean isValidEditText(EditText et) {
		if (et.getText() != null && et.getText().toString().trim().length() != 0)
			return true;
		return false;
	}

	public static boolean isValidTextView(TextView tv, String def) {
		if (tv.getText() != null && !tv.getText().toString().trim().equals(def))
			return true;
		return false;
	}

	public static String getMsFromDate(String input) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {

			Date date = formatter.parse(input);
			System.out.println(date);
			System.out.println(formatter.format(date));
			return date.getTime() + "";

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis() + "";
	}

	public static String getMsFromDateForProgression(String input) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		try {
			Date date = formatter.parse(input);
			System.out.println(date);
			System.out.println(formatter.format(date));
			return date.getTime() + "";

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis() + "";
	}

	static int[] colors = { Color.parseColor("#ef4036"), Color.parseColor("#fcb040"), Color.parseColor("#fff200"),
			Color.parseColor("#8dc63f"), Color.parseColor("#25aae1"), Color.parseColor("#db42e5"),
			Color.parseColor("#7c7c7c"), Color.parseColor("#70b5f6"), Color.parseColor("#9c5f2e"),
			Color.parseColor("#38c9ce") };

	public static Bitmap createBitMap(String name) {
		try {
			int idx = new Random().nextInt(colors.length);
			int random = (colors[idx]);
			Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888); // creates
																					// bmp
			bitMap = bitMap.copy(bitMap.getConfig(), true); // lets bmp to be
															// mutable
			Canvas canvas = new Canvas(bitMap); // draw a canvas in defined bmp

			Paint paint = new Paint();
			paint.setAntiAlias(true);
			// int randomColorIndex = new Random().nextInt(colors.length);
			// int randomColor = (colors[randomColorIndex]);
			paint.setColor(random);
			paint.setStyle(Paint.Style.FILL);

			Paint paint1 = new Paint();
			paint1.setAntiAlias(true);
			paint1.setColor(Color.WHITE);
			paint1.setStyle(Paint.Style.FILL);
			paint1.setTextSize(60);
			paint1.setTextAlign(Align.CENTER);
			canvas.drawCircle(50, 50, 50, paint);
			int xPos = (canvas.getWidth() / 2);
			int yPos = (int) ((canvas.getHeight() / 2) - ((paint1.descent() + paint1.ascent()) / 2));
			String character = name.substring(0, 1).toUpperCase();
			canvas.drawText(character, xPos, yPos, paint1);
			return bitMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Bitmap createPTBitMap() {
		try {
			int idx = new Random().nextInt(colors.length);
			int random = (colors[idx]);
			Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888); // creates
																					// bmp
			bitMap = bitMap.copy(bitMap.getConfig(), true); // lets bmp to be
															// mutable
			Canvas canvas = new Canvas(bitMap); // draw a canvas in defined bmp

			Paint paint = new Paint();
			paint.setAntiAlias(true);
			// int randomColorIndex = new Random().nextInt(colors.length);
			// int randomColor = (colors[randomColorIndex]);
			paint.setColor(random);
			paint.setStyle(Paint.Style.FILL);

			Paint paint1 = new Paint();
			paint1.setAntiAlias(true);
			paint1.setColor(Color.WHITE);
			paint1.setStyle(Paint.Style.FILL);
			paint1.setTextSize(45);
			paint1.setTextAlign(Align.CENTER);
			canvas.drawCircle(50, 50, 50, paint);
			int xPos = (canvas.getWidth() / 2);
			int yPos = (int) ((canvas.getHeight() / 2) - ((paint1.descent() + paint1.ascent()) / 2));
			String character = "PT";// .substring(0, 1).toUpperCase();
			canvas.drawText(character, xPos, yPos, paint1);
			return bitMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDateFromMS(Long ms) {
		Date date = new Date(ms);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		return formattedDate;
	}

	public static String getDateForPdfName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		return formattedDate;
	}

	public static String getDateFromMSForProgression(Long ms) {
		Date date = new Date(ms);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate);
		return formattedDate;
	}

	public static String encodeTobase64(Bitmap image) {
		Bitmap immagex = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

		Log.e("LOOK", imageEncoded);
		return imageEncoded;
	}

	public static byte[] encodeToByteArray(Bitmap image) {
		Bitmap immagex = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		return b;
	}

	public static Bitmap decodeBase64(String input) {
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

	public static void sendViewToBack(final View child) {
		final ViewGroup parent = (ViewGroup) child.getParent();
		if (null != parent) {
			parent.removeView(child);
			parent.addView(child, 0);
		}
	}

	public int readCardHeight(Context context) {
		// System.out.println("Screen Size :"+getMyScreenSize());
		switch (getMyScreenSize(context)) {
		case DisplayMetrics.DENSITY_LOW:
			return 150;
		case DisplayMetrics.DENSITY_MEDIUM:
			return 200;
		case DisplayMetrics.DENSITY_HIGH:
			return 310;
		case DisplayMetrics.DENSITY_XHIGH:
			return 390;
		case DisplayMetrics.DENSITY_XXHIGH:
			return 525;
		default:
			return 525;
		}

	}

	public int getMyScreenSize(Context cont) {
		return cont.getResources().getDisplayMetrics().densityDpi;
	}

	public static void setMyRelativeLayoutSize(Context context, View view) {
		// TODO Auto-generated method stub
		try {
			Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
			int width = display.getWidth();
			int height = (width * 70) / 100;
			RelativeLayout.LayoutParams lp = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height);
			view.setLayoutParams(lp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setMyViewPagerSize(Context context, View view) {
		// TODO Auto-generated method stub
		try {
			Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
			int width = display.getWidth();
			int height = (width * 70) / 100;
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
					height);
			view.setLayoutParams(lp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// public static void showNewStoriesToast(Context context) {
	// try {
	// LayoutInflater inflater = ((Activity) context).getLayoutInflater();
	// final View toastRoot = inflater.inflate(R.layout.new_stories_view, null);
	// final Toast toast = new Toast(context);
	// toast.setView(toastRoot);
	// toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
	// toast.setDuration(5000);
	// toast.show();
	// toastRoot.findViewById(R.id.imgCloseNewStories).setOnClickListener(new
	// OnClickListener() {
	//
	// @Override
	// public void onClick(View arg0) {
	// // TODO Auto-generated method stub
	// RippleView rippleView = (RippleView)
	// toastRoot.findViewById(R.id.ripCloseNewStories);
	// rippleView.setOnRippleCompleteListener(new OnRippleCompleteListener() {
	//
	// @Override
	// public void onComplete(RippleView rippleView) {
	// // TODO Auto-generated method stub
	// toast.cancel();
	// }
	// });
	// }
	// });
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public static boolean isValidURL(String url) {

		URL u = null;

		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}

		try {
			u.toURI();
		} catch (URISyntaxException e) {
			return false;
		}

		return true;
	}

	public static Animation getTourEnterAnimation() {
		Animation enterAnimation = new AlphaAnimation(0f, 1f);
		enterAnimation.setDuration(600);
		enterAnimation.setFillAfter(true);
		return enterAnimation;
	}

	public static Animation getTourExitAnimation() {
		Animation exitAnimation = new AlphaAnimation(1f, 0f);
		exitAnimation.setDuration(600);
		exitAnimation.setFillAfter(true);
		return exitAnimation;
	}

	// ArrayList<String> titles = new ArrayList<>();
	// ArrayList<String> descriptions = new ArrayList<>();
	// ArrayList<Integer> gravities = new ArrayList<>();
	// ArrayList<View> views = new ArrayList<>();

	ArrayList<Integer> lstRes = new ArrayList<>();
	// ArrayList<Fragment> lstTutorial = new ArrayList<>();

	// public void addTourContent(
	// int resource/* String t, String d, int g, View v */) {
	// // titles.add("");
	// // descriptions.add(d);
	// // gravities.add(g);
	// // views.add(v);
	// if (!lstRes.contains(resource)) {
	// lstRes.add(resource);
	// // lstTutorial.add(TutorialFragment.newInstance(resource,
	// // FuncUtils.this));
	// }
	//
	// }
	//
	// String prefValue;
	//
	// public void playTourGuide(Activity context, String prefValue) {
	// try {
	// this.prefValue = prefValue;
	// // landingActivity.changeFragment(lstTutorial.get(0), true);
	// showTutorial(lstRes.get(0));
	// // DataUtils.getSPref(context).edit().putBoolean(prefValue,
	// // true).commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// // public void clearTutorial() {
	// // try {
	// // tourGuide.cleanUp();
	// // } catch (Exception e) {
	// // e.printStackTrace();
	// // }
	// // }
	// //
	// // @Override
	// // public void onFragmentDisappear(int res) {
	// // // TODO Auto-generated method stub
	// // try {
	// // int index = lstRes.indexOf(res);
	// // if (index < lstRes.size() - 1) {
	// // landingActivity.changeFragment(lstTutorial.get(index + 1), true);
	// // } else {
	// // lstRes.clear();
	// // lstTutorial.clear();
	// // // DataUtils.getSPref(context).edit().putBoolean(prefValue,
	// // // true).commit();
	// // }
	// // } catch (Exception e) {
	// // e.printStackTrace();
	// // }
	// // }
	//
	// void showTutorial(final int resID) {
	// try {
	// final Dialog dialog = new Dialog(context,
	// android.R.style.Theme_Black_NoTitleBar);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.setContentView(R.layout.fragment_tutorial);
	// dialog.setCancelable(false);
	// Window window = dialog.getWindow();
	// WindowManager.LayoutParams wlp = window.getAttributes();
	// wlp.gravity = Gravity.CENTER;
	// wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
	// window.setAttributes(wlp);
	// dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,
	// LayoutParams.MATCH_PARENT);
	// dialog.getWindow().getAttributes().windowAnimations =
	// com.DGG.R.style.dialog_animation_1;
	// dialog.getWindow().setBackgroundDrawable(new
	// ColorDrawable(Color.parseColor("#00000000")));
	// ImageView img = (ImageView) dialog.findViewById(R.id.imgTutorial);
	// img.setImageBitmap(loadBitmapSafety(resID, 1, context));
	// img.setScaleType(ScaleType.FIT_XY);
	// img.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// dialog.dismiss();
	// closeFragment(resID);
	// }
	// });
	// dialog.show();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// void closeFragment(int res) {
	// try {
	// int index = lstRes.indexOf(res);
	// if (index < lstRes.size() - 1) {
	// showTutorial(lstRes.get(index + 1));
	// } else {
	// lstRes.clear();
	// // lstTutorial.clear();
	// DataUtils.getSPref(context).edit().putBoolean(prefValue, true).commit();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// int[] tipsViews = { R.id.layTip1, R.id.layTip2, R.id.layTip3,
	// R.id.layTip4, R.id.layTip5 };
	// String[] tipsBGColors = { "#6639B6", "#4CAF50", "#2095F2", "#FFC107",
	// "#9B26AF" };
	// int currentIndex = 0;
	// RubberIndicator rubberIndicator;
	// InkPageIndicator indicator;
	// Dialog dialog;
	// ViewPager viewPager;
	// ScrollView scrlTips;
	//
	// public void showEntertainTutorialDialog(Context context) {
	// try {
	// dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.setContentView(R.layout.fragment_tips);
	// dialog.setCancelable(false);
	// Window window = dialog.getWindow();
	// WindowManager.LayoutParams wlp = window.getAttributes();
	// wlp.gravity = Gravity.CENTER;
	// wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
	// window.setAttributes(wlp);
	// dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,
	// LayoutParams.MATCH_PARENT);
	// dialog.getWindow().getAttributes().windowAnimations =
	// com.glide.R.style.dialog_animation_1;
	// dialog.getWindow().setBackgroundDrawable(new
	// ColorDrawable(Color.parseColor("#00000000")));
	// rubberIndicator = (RubberIndicator) dialog.findViewById(R.id.rubber);
	// indicator = (InkPageIndicator)
	// dialog.findViewById(R.id.ink_pager_indicator);
	// rubberIndicator.setCount(5);
	// viewPager = (ViewPager) dialog.findViewById(R.id.myviewpager);
	// scrlTips = (ScrollView) dialog.findViewById(R.id.scrlTips);
	// MyPagerAdapter myPagerAdapter = new MyPagerAdapter(context);
	// viewPager.setAdapter(myPagerAdapter);
	// indicator.setViewPager(viewPager);
	// // rubberIndicator.setOnMoveListener(this);
	// dialog.findViewById(R.id.txt1).setEnabled(true);
	// dialog.findViewById(R.id.txt2).setEnabled(true);
	// dialog.findViewById(R.id.txt3).setEnabled(true);
	// dialog.findViewById(R.id.txt4).setEnabled(true);
	// dialog.findViewById(R.id.txt5).setEnabled(true);
	// updateView(context, dialog, currentIndex);
	// dialog.show();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private void updateView(Context context, Dialog dialog, int index) {
	// // TODO Auto-generated method stub
	// try {
	// scrlTips.setBackgroundColor(Color.parseColor(tipsBGColors[index]));
	// if (index == 0) {
	// dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
	// dialog.findViewById(tipsViews[index])
	// .startAnimation(AnimationUtils.loadAnimation(context,
	// R.anim.slide_forward));
	// dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
	// rubberIndicator.moveToRight();
	// currentIndex = 1;
	// viewPager.setCurrentItem(0);
	// } else if (index == 1) {
	// dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
	// dialog.findViewById(tipsViews[index])
	// .startAnimation(AnimationUtils.loadAnimation(context,
	// R.anim.slide_forward));
	// dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
	// currentIndex = 2;
	// rubberIndicator.moveToRight();
	// viewPager.setCurrentItem(1);
	// } else if (index == 2) {
	// dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
	// dialog.findViewById(tipsViews[index])
	// .startAnimation(AnimationUtils.loadAnimation(context,
	// R.anim.slide_forward));
	// dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
	// currentIndex = 3;
	// rubberIndicator.moveToRight();
	// viewPager.setCurrentItem(2);
	// } else if (index == 3) {
	// dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
	// dialog.findViewById(tipsViews[index])
	// .startAnimation(AnimationUtils.loadAnimation(context,
	// R.anim.slide_forward));
	// dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
	// currentIndex = 4;
	// rubberIndicator.moveToRight();
	// viewPager.setCurrentItem(3);
	// } else if (index == 4) {
	// dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
	// dialog.findViewById(tipsViews[index])
	// .startAnimation(AnimationUtils.loadAnimation(context,
	// R.anim.slide_forward));
	// dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
	// dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
	// currentIndex = 0;
	// viewPager.setCurrentItem(4);
	// rubberIndicator.setFocusPosition(0);
	// }
	// waitFor10Seconds(context, dialog, currentIndex);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// Handler tipsShowHandler;
	//
	// private void waitFor10Seconds(final Context context, final Dialog dialog,
	// final int index) {
	// // TODO Auto-generated method stub
	// new Handler().postDelayed(new Runnable() {
	// @Override
	// public void run() {
	// if (dialog != null && dialog.isShowing())
	// updateView(context, dialog, index);
	// }
	// }, 10000);
	// }
	//
	// public void dismissDialog() {
	// if (dialog != null && dialog.isShowing())
	// dialog.dismiss();
	// }
	//
	// private class MyPagerAdapter extends PagerAdapter {
	//
	// int NumberOfPages = 5;
	// Context context;
	//
	// public MyPagerAdapter(Context ct) {
	// // TODO Auto-generated constructor stub
	// context = ct;
	// }
	//
	// int[] res = { android.R.drawable.ic_dialog_alert,
	// android.R.drawable.ic_menu_camera,
	// android.R.drawable.ic_menu_compass,
	// android.R.drawable.ic_menu_directions,
	// android.R.drawable.ic_menu_gallery };
	// int[] backgroundcolor = { 0xFF101010, 0xFF202020, 0xFF303030, 0xFF404040,
	// 0xFF505050 };
	//
	// @Override
	// public int getCount() {
	// return NumberOfPages;
	// }
	//
	// @Override
	// public boolean isViewFromObject(View view, Object object) {
	// return view == object;
	// }
	//
	// @Override
	// public Object instantiateItem(ViewGroup container, int position) {
	//
	// View emptyView = new View(context);
	//
	// return emptyView;
	// }
	//
	// @Override
	// public void destroyItem(ViewGroup container, int position, Object object)
	// {
	// container.removeView((View) object);
	// }
	//
	// }

	// public static void shareFile(Context context, Bitmap bm1, String content)
	// {
	// try {
	// Bitmap bm = Bitmap.createScaledBitmap(bm1, 400, 300, false);
	// PDFWriter mPDFWriter = new PDFWriter(PaperSize.FOLIO_WIDTH,
	// PaperSize.FOLIO_HEIGHT);
	// mPDFWriter.setFont(StandardFonts.SUBTYPE, StandardFonts.HELVETICA_BOLD);
	// mPDFWriter.addText(200, 850, 15, "Glide - Digital Business Cards");
	//// mPDFWriter.addRawContent("Glide - Digital Business Cards");
	// mPDFWriter.addImage(100, 500, bm);
	// mPDFWriter.setFont(StandardFonts.SUBTYPE,
	// StandardFonts.HELVETICA_OBLIQUE);
	// String c1 = "--> You never need to use a paper business card again.";
	// String c2 = "--> Download Glide and create your digital business card
	// today just like mine.";
	// String c3 = "--> Don't forget to enter my tag " +
	// DataUtils.getUserTag(context)
	// + " as Referral Tag when you sign up.";
	// String c4 = "--> Download :
	// https://play.google.com/store/apps/details?id=com.glide";
	// int fBottomMargin = 450;
	// mPDFWriter.addText(100, fBottomMargin-30, 12, c1);
	// mPDFWriter.addText(100, fBottomMargin-60, 12, c2);
	// mPDFWriter.addText(100, fBottomMargin-90, 12, c3);
	// mPDFWriter.addText(100, fBottomMargin-120, 12, c4);
	// File newFile = getOutputPdfFile();
	// try {
	// newFile.createNewFile();
	// FileOutputStream pdfFile = new FileOutputStream(newFile);
	// pdfFile.write(mPDFWriter.asString().getBytes("ISO-8859-1"));
	// pdfFile.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
	// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(newFile));
	// intent.setType("application/pdf");
	// ((Activity) context).startActivityForResult(intent, 404);
	// ((Activity) context).overridePendingTransition(R.anim.slide_forward, 0);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// private static File getOutputPdfFile() {
	// File mediaStorageDir = new
	// File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
	// "Glide Show Off");
	// if (!mediaStorageDir.exists()) {
	// if (!mediaStorageDir.mkdirs()) {
	// return null;
	// }
	// }
	// File mediaFile = new File(
	// mediaStorageDir.getPath() + File.separator + "glide_card_" +
	// System.currentTimeMillis() + ".pdf");
	// return mediaFile;
	// }

}
