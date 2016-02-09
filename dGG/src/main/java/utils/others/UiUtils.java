package utils.others;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import ca.dgg.e_contract.R;
import utils.pojos.StringWithID;

public class UiUtils {

	Context context;

	public int TYPE_TEXTVIEW = 1;
	public int TYPE_EDITTEXT = 2;
	public int TYPE_SPINNER = 3;
	public int TYPE_SPINNER_WITH_EDITTEXT = 4;


	//Dummy key for csv file
	String[] endUserCSVKey = {
			"1:User Name:<Value>:1:mandatory:String",
			"2:Last Name:<Value>:1:mandatory:String",
			"3:Pass phrase:<Value>e:1:mandatory:String",
			"4:Postal Code:<Value>:1:mandatory:String",
			"5:Email:<Value>:1:mandatory:String",
			"6:Phone Number:<Value>:1:mandatory:String",
			"7:Permission to notify of each CRY:<Value>:1:mandatory:Boolean",
			"8:Gender:<Value>:1:mandatory:Boolean",
			"9:Age Group under-18 19-30 31-45 46-60 61+:<Value>:1:mandatory:Boolean",
			"10:Marital Status:<Value>:1:mandatory:String",
			"11:Education level:<Value>:1:mandatory:String",
			"12:Employment (part-time/full-time/self-employed/retired/student/unemployed):<Value>:1:mandatory:Date",
			"13:Are you member Of Union:<Value>:0:not mandatory:Boolean",
			"14:Union Name:<Value>:0:not mandatory:String",
			"15:Union Local:<Value>:0:not mandatory:String",
			"16:Political Party:<Value>:0:not mandatory:String",
			"17:Military personel:<Value>:0:not mandatory:String",
			"18:Immigration Status:<Value>:0:not mandatory:String",
			"19:Town Of Birth:<Value>:0:not mandatory:String",
			"20:Current Town Of Residence:<Value>:0:not mandatory:String",
			"21:Current Residence:<Value>:0:not mandatory:String",
			"22:Type Of Residence:<Value>:0:not mandatory:String",
			"23:Number of members in household:<Value>:0:not mandatory:Integer",
			"24:Number of dependant:<Value>:0:not mandatory:Integer",
			"25:Length of time at residence:<Value>:0:not mandatory:Integer",
			"26:First language:<Value>:0:not mandatory:String",
			"27:Additional Language:<Value>:0:not mandatory:String",
			"28:Self Identified ethnic background:<Value>:0:not mandatory:String",
			"29:Self-identified Religion:<Value>:0:not mandatory:String"

	};


	String[] superUserCSVKey = {
			"1:Organisation Name:<Value>:1:mandatory:String",
			"2:Contract City:<Value>:1:mandatory:String",
			"3:Contract Province:<Value>e:1:mandatory:String",
			"4:Contract Country:<Value>:1:mandatory:String",
			"5:Date:<Value>:1:mandatory:Date",
			"6:Contract Number:<Value>:1:mandatory:Integer",
			"7:Billing Mailing Address:<Value>:1:mandatory:String",
			"8:Billing Bill Address:<Value>:1:mandatory:String",
			"9:Billing Contact Preference:<Value>:1:mandatory:Integer",
			"10:Party:<Value>:1:mandatory:String",
			"11:Union:<Value>:1:mandatory:String",
			"12:Corporate:<Value>:1:mandatory:String",
			"13:Client Name:<Value>:1:mandatory:String",
			"14:Client Phone:<Value>:1:mandatory:Integer",
			"15:Client Mailing Address:<Value>:1:mandatory:String",
			"16:Client Billing Address:<Value>:1:mandatory:String",
			"17:Client Email:<Value>:1:mandatory:String",
			"18:Client Website:<Value>:1:mandatory:String",
			"19:Purchaser/ Client Title:<Value>:1:mandatory:String",
			"20:Client contact Name:<Value>:1:mandatory:String",
			"21:Client contact Phone:<Value>:1:mandatory:Integer",
			"22:Client contact Mailing Address:<Value>:1:mandatory:String",
			"23:Client contact Billing Address:<Value>:1:mandatory:String",
			"24:Client contact Email:<Value>:1:mandatory:String",
			"25:Client contact Preference:<Value>:1:mandatory:String",
			"26:Client alternate Name:<Value>:1:mandatory:String",
			"27:Client alternate Phone:<Value>:1:mandatory:Integer",
			"28:Client alternate Mailing Address:<Value>:1:mandatory:String",
			"28:Client alternate Billing Address:<Value>:1:mandatory:String",
			"29:Client alternate Email:<Value>:1:mandatory:String",
			"30:Client alternate Preference:<Value>:1:mandatory:String",
			"31:Signature:<Value>:1:mandatory:String",

	};


	private UiUtils(Context context) {
		this.context = context;
		lstFieldETs = new ArrayList<>();
		lstFieldMandatory = new ArrayList<>();
		lstFieldSpinners = new ArrayList<>();
	}

	static UiUtils currentInstance;

	public static UiUtils getInstance(Context context) {
//		if (currentInstance == null) {
			currentInstance = new UiUtils(context);
		// }
		 return currentInstance;
	}

	public void setFormTitleSplitter(LinearLayout rootView) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_splitter, null);
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormTitle(LinearLayout rootView, String title, String tag) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_form_sectional_title, null);
			TextView tv = (TextView) convertedView.findViewById(R.id.txtSectionTitle);
			tv.setTag(tag);
			tv.setText(title);
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormContentItem2inSingleRow(LinearLayout rootView, String name1, int valueType1, String valueTag1,
			String hint1, ArrayList<StringWithID> listValues1, String defValue1, int defValueIndex1, int inputType1,
			boolean isMust1, String name2, int valueType2, String valueTag2, String hint2,
			ArrayList<StringWithID> listValues2, String defValue2, int defValueIndex2, int inputType2,
			boolean isMust2) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_name_value_pair, null);
			LinearLayout leftLay = (LinearLayout) convertedView.findViewById(R.id.layLeft);
			LinearLayout rightLay = (LinearLayout) convertedView.findViewById(R.id.layRight);
			setFormContentItem(leftLay, name1, valueType1, valueTag1, hint1, listValues1, defValue1, defValueIndex1,
					inputType1, isMust1);
			setFormContentItem(rightLay, name2, valueType2, valueTag2, hint2, listValues2, defValue2, defValueIndex2,
					inputType2, isMust2);
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormContentItem(LinearLayout rootView, String name, int valueType, String valueTag, String hint,
			ArrayList<StringWithID> listValues, String defValue, int defValueIndex, int inputType, boolean isMust) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_name_value_pair, null);
			LinearLayout leftLay = (LinearLayout) convertedView.findViewById(R.id.layLeft);
			LinearLayout rightLay = (LinearLayout) convertedView.findViewById(R.id.layRight);
			setFormLeftName(leftLay, name);
			if (valueType == TYPE_EDITTEXT) {
				setFormRightValueET(rightLay, valueTag, hint, defValue, inputType, isMust);
			} else if (valueType == TYPE_TEXTVIEW) {
				setFormRightValueTV(rightLay, valueTag, defValue);
			} else if (valueType == TYPE_SPINNER) {
				setFormRightValueSpinner(rightLay, listValues, name, defValueIndex, isMust);
			} else if (valueType == TYPE_SPINNER_WITH_EDITTEXT) {
				setFormRightValueSpinnerWithET(rightLay, listValues, valueTag, defValueIndex, hint, defValue);
			}
			rootView.addView(convertedView);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ArrayList<EditText> lstFieldETs = new ArrayList<>();
	ArrayList<Boolean> lstFieldMandatory = new ArrayList<>();
	ArrayList<Spinner> lstFieldSpinners = new ArrayList<>();

	public void setFormLeftName(LinearLayout rootView, String title) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_form_left_tv, null);
			TextView tv = (TextView) convertedView.findViewById(R.id.txtLeftTitle);
			tv.setText(title);
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormRightValueET(LinearLayout rootView, String tag, String hint, String defValue, int inputValue,
			boolean isMust) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_form_right_et, null);
			EditText etRightValue = (EditText) convertedView.findViewById(R.id.etRightValue);
			etRightValue.setText(defValue);
			etRightValue.setHint(hint);
			etRightValue.setTag(hint);
			etRightValue.setInputType(inputValue);
			if (inputValue == -10) {
				etRightValue.setHeight(100);
			}
			lstFieldETs.add(etRightValue);
			lstFieldMandatory.add(isMust);
			lstFieldSpinners.add(null);
			etRightValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						hideKeyboard(v);
					}
				}
			});
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormRightValueTV(LinearLayout rootView, String tag, String defValue) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_form_left_tv, null);
			TextView tv = (TextView) convertedView.findViewById(R.id.txtLeftTitle);
			tv.setText(defValue);
			tv.setTag(tag);
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormRightValueSpinner(LinearLayout rootView, final ArrayList<StringWithID> lstValues, String tag,
			int defIndex, boolean isMust) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_form_right_spinner, null);
			final Spinner spinnerRightValue = (Spinner) convertedView.findViewById(R.id.spinnerRightValue);
			spinnerRightValue.setAdapter(new ArrayAdapter<>(context, R.layout.spnr_item, R.id.txtSpnrItem, lstValues));
			spinnerRightValue.setSelection(defIndex, false);
			spinnerRightValue.setTag(tag);
			final EditText etRightValue = (EditText) convertedView.findViewById(R.id.etRightValue);
			etRightValue.setTag(tag);
			lstFieldETs.add(etRightValue);
			lstFieldMandatory.add(isMust);
			lstFieldSpinners.add(spinnerRightValue);
			spinnerRightValue.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					if (arg2 != 0 && lstValues.get(arg2).toString().toLowerCase().equalsIgnoreCase("other")) {
						showOtherDialog(context, spinnerRightValue, lstValues, etRightValue);
					} else if (arg2 != 0) {
						etRightValue.setText(lstValues.get(arg2).toString());
					} else {
						etRightValue.setText("");
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showOtherDialog(final Context context, final Spinner spinner, final ArrayList<StringWithID> lst,
			final EditText et) {
		try {
			final Dialog dialog = new Dialog(context);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.other_info_dialog);
			dialog.setCancelable(false);
			// Window window = dialog.getWindow();
			// WindowManager.LayoutParams wlp = window.getAttributes();
			// wlp.gravity = Gravity.CENTER;
			// wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
			// window.setAttributes(wlp);
			// dialog.getWindow().setLayout(LayoutParams.FILL_PARENT,
			// LayoutParams.MATCH_PARENT);
			// dialog.getWindow().getAttributes().windowAnimations =
			// R.style.dialog_animation_1;
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
			// rubberIndicator = (RubberIndicator)
			// dialog.findViewById(R.id.rubber);
			final EditText etInfo = (EditText) dialog.findViewById(R.id.etOtherInfo);
			etInfo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						hideKeyboard(v);
					}
				}
			});
			dialog.findViewById(R.id.btnSave).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (etInfo.getText().toString() != null && etInfo.getText().toString().trim().length() != 0) {
						StringWithID e = new StringWithID();
						e.setItemID(etInfo.getText().toString());
						e.setName(etInfo.getText().toString().trim());
						lst.add(1, e);
						spinner.setAdapter(new ArrayAdapter<>(context, R.layout.spnr_item, R.id.txtSpnrItem, lst));
						spinner.setSelection(1, false);
						et.setText(etInfo.getText().toString().trim());
						dialog.dismiss();
					} else {
						etInfo.setError("Invalid!");
					}
				}
			});
			dialog.findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					spinner.setAdapter(new ArrayAdapter<>(context, R.layout.spnr_item, R.id.txtSpnrItem, lst));
					spinner.setSelection(0, false);
					et.setText("");
					dialog.dismiss();
				}
			});
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormRightValueSpinnerWithET(LinearLayout rootView, ArrayList<StringWithID> lstValues, String tag,
			int defIndex, String hint, String defValue) {
		View convertedView = null;
		try {
			convertedView = ((Activity) context).getLayoutInflater().inflate(R.layout.view_form_right_spinner, null);
			Spinner spinnerRightValue = (Spinner) convertedView.findViewById(R.id.spinnerRightValue);
			spinnerRightValue.setAdapter(new ArrayAdapter<>(context, R.layout.spnr_item, R.id.txtSpnrItem, lstValues));
			spinnerRightValue.setSelection(defIndex, false);
			spinnerRightValue.setTag(tag);
			rootView.addView(convertedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getStringFromET(EditText et) {
		String result = "";
		try {
			if (et != null && et.getText().toString() != null && et.getText().toString().trim().length() > 0)
				result = et.getText().toString().trim();
		} catch (Exception e) {

		}
		return result;
	}

	public boolean isValidToProceed() {
		ArrayList<Boolean> result = new ArrayList<>();
		try {
			for (int i = 0; i < lstFieldETs.size(); i++) {
				String res = getStringFromET(lstFieldETs.get(i));
				if (lstFieldMandatory.get(i) && res.length() != 0)
					result.add(true);
				else if (lstFieldMandatory.get(i) && res.length() == 0) {
					if (lstFieldSpinners.get(i) == null)
						lstFieldETs.get(i).setError("Invalid Info!");
					else {
						LinearLayout lin = (LinearLayout) lstFieldSpinners.get(i).getSelectedView();
						TextView errorText = (TextView) lin.findViewById(R.id.txtSpnrItem);
						errorText.setError("Invalid Info!");
						errorText.setTextColor(Color.RED);
					}
					result.add(false);
				} else if (!lstFieldMandatory.get(i))
					result.add(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result.contains(false)) {
			String msg = "Please check the entered info!";
			FuncUtils.showToast(context, msg);
			return false;
		} else if (result.size() > 0)
			return true;
		return false;
	}

	public ArrayList<String> getFilledData(String userType) {
		ArrayList<String> result = new ArrayList<>();
		try {
			for (int i = 0; i < lstFieldETs.size(); i++) {
				String res = getStringFromET(lstFieldETs.get(i));
				if (userType.equals("EndUser")) {
					result.add(endUserCSVKey[i].replace("<Value>", res));
				} else {
					result.add(superUserCSVKey[i].replace("<Value>", res));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void hideKeyboard(View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

}
