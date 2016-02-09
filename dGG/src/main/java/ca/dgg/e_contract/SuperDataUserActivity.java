package ca.dgg.e_contract;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import utils.others.DataUtils;
import utils.others.FuncUtils;
import utils.others.UiUtils;
import utils.pojos.FieldPOJO;
import utils.pojos.StringWithID;
import utils.ripplebutton.widget.RippleButton;

public class SuperDataUserActivity extends FragmentActivity {

	ImageView imgHeaderBack;
	TextView txtAppHeader;
	LinearLayout laySuperDataUserItems;
	UiUtils uiUtils;
	RippleButton btnSign;
	String UserType= "SuperUser";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_super_data_user_form);
		FuncUtils.changeStatusBarColor(context);
		uiUtils = UiUtils.getInstance(context);
		imgHeaderBack = (ImageView) findViewById(R.id.imgHeaderBack);
		imgHeaderBack.setVisibility(View.VISIBLE);
		txtAppHeader = (TextView) findViewById(R.id.txtAppHeader);
		txtAppHeader.setText("Super User Data");
		laySuperDataUserItems = (LinearLayout) findViewById(R.id.laySuperDataUserItems);
		findViewById(R.id.btnSubmit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
			//	if (uiUtils.isValidToProceed()) {
				// FuncUtils.showToast(context, "Success!!!");
				// showPaymentDialog();
				ArrayList<String> lst = uiUtils.getFilledData(UserType);
				DataUtils dataUtils = DataUtils.getInstance(context);
				dataUtils.getSPref(context).edit()
						.putString(DataUtils.PREF_SUPER_USER_DATA, dataUtils.convertToString(lst)).commit();
				// CSVUtils.writeCSV(context, lst, CSVUtils.FILE_END_USER);
				Intent in = new Intent(SuperDataUserActivity.this, ContractActivity.class);
				startActivity(in);
				// finish();
				overridePendingTransition(R.anim.slide_forward, 0);
				// } else {
				// FuncUtils.showToast(context, "Failed");
				// }
			}
		});
		findViewById(R.id.imgHeaderBack).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		findViewById(R.id.btnTOS).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String tos = DataUtils.getInstance(context).readFromfile(context, "TOS.txt");
				FuncUtils.showNotifyDialog(context, Html.fromHtml(tos).toString());
			}
		});
		btnSign = (RippleButton) findViewById(R.id.btnSign);
		btnSign.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(context, CaptureSignature.class);
				startActivityForResult(in, 121);
				overridePendingTransition(R.anim.slide_forward, R.anim.slide_backward);
			}
		});
		setupFormView();
	}

	Context context = this;

	private void setupFormView() {
		try {
			laySuperDataUserItems.removeAllViews();
			String dataJSON = DataUtils.getInstance(context).readFromfile(context, "SuperDataUserFields.txt");
			JSONObject js = new JSONObject(dataJSON);
			JSONArray fArray = js.getJSONArray("fields");
			ArrayList<FieldPOJO> result = new ArrayList<>();
			for (int i = 0; i < fArray.length(); i++) {
				JSONObject tempField = fArray.getJSONObject(i);
				FieldPOJO fp = new FieldPOJO();
				fp.setfType(tempField.getString("type"));
				fp.setHint(tempField.getString("hint"));
				fp.setIpType(tempField.getString("input_type"));
				fp.setTag(tempField.getString("tag"));
				fp.setMandatory(tempField.getBoolean("is_mandatory"));
				JSONArray lstArray = tempField.getJSONArray("list_values");
				ArrayList<StringWithID> lstValues = new ArrayList<>();
				for (int j = 0; j < lstArray.length(); j++) {
					JSONObject tempLst = lstArray.getJSONObject(j);
					StringWithID temp = new StringWithID();
					temp.setItemID(tempLst.getString("id"));
					temp.setName(tempLst.getString("value"));
					lstValues.add(temp);
				}
				fp.setListValues(lstValues);
				result.add(fp);
			}
			if (result.size() > 0) {
				FieldPOJO name = result.get(0);
				FieldPOJO CCity = result.get(1);
				FieldPOJO CPro = result.get(2);
				FieldPOJO CCoutry = result.get(3);
				FieldPOJO date = result.get(4);
				FieldPOJO contractNumber = result.get(5);
				FieldPOJO bName = result.get(6);
				FieldPOJO bPhone = result.get(7);
				FieldPOJO bMailing = result.get(8);
				FieldPOJO bBilling = result.get(9);
				FieldPOJO bMail = result.get(10);
				FieldPOJO bPrefer = result.get(11);
				FieldPOJO oParty = result.get(12);
				FieldPOJO oUnion = result.get(13);
				FieldPOJO oCorporate = result.get(14);
				FieldPOJO cName = result.get(15);
				FieldPOJO cPhone = result.get(16);
				FieldPOJO cMailing = result.get(17);
				FieldPOJO cBilling = result.get(18);
				FieldPOJO cMail = result.get(19);
				FieldPOJO cWebsite = result.get(20);
				FieldPOJO agent = result.get(21);
				FieldPOJO clName = result.get(22);
				FieldPOJO clPhone = result.get(23);
				FieldPOJO clMailing = result.get(24);
				FieldPOJO clBilling = result.get(25);
				FieldPOJO clMail = result.get(26);
				FieldPOJO clPrefer = result.get(27);
				FieldPOJO alName = result.get(28);
				FieldPOJO alPhone = result.get(29);
				FieldPOJO alMailing = result.get(30);
				FieldPOJO alBilling = result.get(31);
				FieldPOJO alMail = result.get(32);
				FieldPOJO alPrefer = result.get(33);
				uiUtils.setFormContentItem(laySuperDataUserItems, name.getHint(), uiUtils.TYPE_EDITTEXT, name.getTag(),
						name.getHint(), name.getListValues(), "", 0, InputType.TYPE_TEXT_FLAG_CAP_WORDS,
						name.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormTitle(laySuperDataUserItems, "Contract Location", "");
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, CCity.getHint(), uiUtils.TYPE_EDITTEXT,
						CCity.getTag(), CCity.getHint(), CCity.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, CCity.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, CPro.getHint(), uiUtils.TYPE_EDITTEXT, CPro.getTag(),
						CPro.getHint(), CPro.getListValues(), "", 0, InputType.TYPE_TEXT_FLAG_CAP_WORDS,
						CPro.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, CCoutry.getHint(), uiUtils.TYPE_EDITTEXT,
						CCoutry.getTag(), CCoutry.getHint(), CCoutry.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, CCoutry.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, date.getHint(), uiUtils.TYPE_EDITTEXT, date.getTag(),
						date.getHint(), date.getListValues(), "", 0, InputType.TYPE_TEXT_FLAG_CAP_WORDS,
						date.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, contractNumber.getHint(), uiUtils.TYPE_EDITTEXT,
						contractNumber.getTag(), contractNumber.getHint(), contractNumber.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, contractNumber.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormTitle(laySuperDataUserItems, "Billing information", "");
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, bName.getHint(), uiUtils.TYPE_EDITTEXT,
						bName.getTag(), bName.getHint(), bName.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, bName.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, bPhone.getHint(), uiUtils.TYPE_EDITTEXT,
						bPhone.getTag(), bPhone.getHint(), bPhone.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, bPhone.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, bMailing.getHint(), uiUtils.TYPE_EDITTEXT,
						bMailing.getTag(), bMailing.getHint(), bMailing.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, bMailing.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, bBilling.getHint(), uiUtils.TYPE_EDITTEXT,
						bBilling.getTag(), bBilling.getHint(), bBilling.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, bBilling.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, bMail.getHint(), uiUtils.TYPE_EDITTEXT,
						bMail.getTag(), bMail.getHint(), bMail.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, bMail.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, bPrefer.getHint(), uiUtils.TYPE_SPINNER,
						bPrefer.getTag(), bPrefer.getHint(), bPrefer.getListValues(), "", 0, -1, bPrefer.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormTitle(laySuperDataUserItems, "Organization type", "");
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, oParty.getHint(), uiUtils.TYPE_SPINNER,
						oParty.getTag(), oParty.getHint(), oParty.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, oParty.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, oUnion.getHint(), uiUtils.TYPE_SPINNER,
						oUnion.getTag(), oUnion.getHint(), oUnion.getListValues(), "", 0, -1, oUnion.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, oCorporate.getHint(), uiUtils.TYPE_SPINNER,
						oCorporate.getTag(), oCorporate.getHint(), oCorporate.getListValues(), "", 0, -1,
						oCorporate.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormTitle(laySuperDataUserItems, "Client Information", "");
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, cName.getHint(), uiUtils.TYPE_EDITTEXT,
						cName.getTag(), cName.getHint(), cName.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, cName.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, cPhone.getHint(), uiUtils.TYPE_EDITTEXT,
						cPhone.getTag(), cPhone.getHint(), cPhone.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, cPhone.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, cMailing.getHint(), uiUtils.TYPE_EDITTEXT,
						cMailing.getTag(), cMailing.getHint(), cMailing.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, cMailing.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, cBilling.getHint(), uiUtils.TYPE_EDITTEXT,
						cBilling.getTag(), cBilling.getHint(), cBilling.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, cBilling.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, cMail.getHint(), uiUtils.TYPE_EDITTEXT,
						cMail.getTag(), cMail.getHint(), cMail.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, cMail.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, cWebsite.getHint(), uiUtils.TYPE_EDITTEXT,
						cWebsite.getTag(), cWebsite.getHint(), cWebsite.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, cWebsite.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, agent.getHint(), uiUtils.TYPE_EDITTEXT,
						agent.getTag(), agent.getHint(), agent.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, agent.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormTitle(laySuperDataUserItems, "Client contact information", "");
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, clName.getHint(), uiUtils.TYPE_EDITTEXT,
						clName.getTag(), clName.getHint(), clName.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, clName.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, clPhone.getHint(), uiUtils.TYPE_EDITTEXT,
						clPhone.getTag(), clPhone.getHint(), clPhone.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, clPhone.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, clMailing.getHint(), uiUtils.TYPE_EDITTEXT,
						clMailing.getTag(), clMailing.getHint(), clMailing.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, clMailing.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, clBilling.getHint(), uiUtils.TYPE_EDITTEXT,
						clBilling.getTag(), clBilling.getHint(), clBilling.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, clBilling.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, clMail.getHint(), uiUtils.TYPE_EDITTEXT,
						clMail.getTag(), clMail.getHint(), clMail.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, clMail.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, clPrefer.getHint(), uiUtils.TYPE_SPINNER,
						clPrefer.getTag(), clPrefer.getHint(), clPrefer.getListValues(), "", 0, -1,
						clPrefer.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormTitle(laySuperDataUserItems, "Alternate contact information", "");
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
				uiUtils.setFormContentItem(laySuperDataUserItems, alName.getHint(), uiUtils.TYPE_EDITTEXT,
						alName.getTag(), alName.getHint(), alName.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, alName.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, alPhone.getHint(), uiUtils.TYPE_EDITTEXT,
						alPhone.getTag(), alPhone.getHint(), alPhone.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, alPhone.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, alMailing.getHint(), uiUtils.TYPE_EDITTEXT,
						alMailing.getTag(), alMailing.getHint(), alMailing.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, alMailing.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, alBilling.getHint(), uiUtils.TYPE_EDITTEXT,
						alBilling.getTag(), alBilling.getHint(), alBilling.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, alBilling.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, alMail.getHint(), uiUtils.TYPE_EDITTEXT,
						alMail.getTag(), alMail.getHint(), alMail.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_WORDS, alMail.isMandatory());
				uiUtils.setFormContentItem(laySuperDataUserItems, alPrefer.getHint(), uiUtils.TYPE_SPINNER,
						alPrefer.getTag(), alPrefer.getHint(), alPrefer.getListValues(), "", 0, -1,
						alPrefer.isMandatory());
				uiUtils.setFormTitleSplitter(laySuperDataUserItems);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_backward, 0);
	}

}
