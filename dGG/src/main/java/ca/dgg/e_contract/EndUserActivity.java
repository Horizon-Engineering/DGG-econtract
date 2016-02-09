package ca.dgg.e_contract;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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

public class EndUserActivity extends FragmentActivity {

	ImageView imgHeaderBack;
	TextView txtAppHeader;
	LinearLayout layEndUserItems;
	UiUtils uiUtils;
	String UserType= "EndUser";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_end_user_form);
		FuncUtils.changeStatusBarColor(context);
		uiUtils = UiUtils.getInstance(context);
		imgHeaderBack = (ImageView) findViewById(R.id.imgHeaderBack);
		imgHeaderBack.setVisibility(View.VISIBLE);
		txtAppHeader = (TextView) findViewById(R.id.txtAppHeader);
		txtAppHeader.setText("Data Point Selection");
		layEndUserItems = (LinearLayout) findViewById(R.id.layEndUserItems);
		findViewById(R.id.btnSubmit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//if (uiUtils.isValidToProceed()) {
					// FuncUtils.showToast(context, "Success!!!");
					ArrayList<String> lst = uiUtils.getFilledData(UserType);
					DataUtils dataUtils = DataUtils.getInstance(context);
					dataUtils.getSPref(context).edit()
							.putString(DataUtils.PREF_END_USER_DATA, dataUtils.convertToString(lst)).commit();
//					CSVUtils.writeCSV(context, lst, CSVUtils.FILE_END_USER);
					Intent in = new Intent(EndUserActivity.this, SuperDataUserActivity.class);
					startActivity(in);
					// finish();
					overridePendingTransition(R.anim.slide_forward, 0);
				//} else {
//					FuncUtils.showToast(context, "Failed");
				//}
			}
		});
		findViewById(R.id.imgHeaderBack).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		setupFormView();
	}

	String[] fieldTypes = {};
	String[] fieldTags = {};
	String[] fieldHint = { };

	 Context context = this;

	private void setupFormView() {
		try {
			layEndUserItems.removeAllViews();
			String dataJSON = DataUtils.getInstance(context).readFromfile(context, "EndUserDataFields.txt");
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
				FieldPOJO fN = result.get(0);
				FieldPOJO lN = result.get(1);
				FieldPOJO pwd = result.get(2);
				FieldPOJO postal = result.get(3);
				FieldPOJO email = result.get(4);
				FieldPOJO phone = result.get(5);
				FieldPOJO permission_cry = result.get(6);
				FieldPOJO gender = result.get(7);
				FieldPOJO age_group = result.get(8);
				FieldPOJO marital_status = result.get(9);
				FieldPOJO education = result.get(10);
				FieldPOJO employment = result.get(11);
				FieldPOJO union_member = result.get(12);
				FieldPOJO union_name = result.get(13);
				FieldPOJO union_local = result.get(14);
				FieldPOJO political_party = result.get(15);
				FieldPOJO military_service = result.get(16);
				FieldPOJO immigration_status = result.get(17);
				FieldPOJO town_of_birth = result.get(18);
				FieldPOJO current_town_of_residence = result.get(19);
				FieldPOJO current_residence = result.get(20);
				FieldPOJO type_of_residence = result.get(21);
				FieldPOJO number_of_members_in_household = result.get(22);
				FieldPOJO number_of_dependants = result.get(23);
				FieldPOJO length_of_time_at_this_residence = result.get(24);
				FieldPOJO first_language = result.get(25);
				FieldPOJO additional_language = result.get(26);
				FieldPOJO self_identified_ethnic_background = result.get(27);
				FieldPOJO self_identified_religion = result.get(28);
				uiUtils.setFormContentItem2inSingleRow(layEndUserItems, fN.getHint(), uiUtils.TYPE_EDITTEXT,
						fN.getTag(), fN.getHint(), fN.getListValues(), "", 0, InputType.TYPE_TEXT_FLAG_CAP_WORDS,
						fN.isMandatory(), lN.getHint(), uiUtils.TYPE_EDITTEXT, lN.getTag(), lN.getHint(),
						lN.getListValues(), "", 0, InputType.TYPE_TEXT_FLAG_CAP_WORDS, lN.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, pwd.getHint(), uiUtils.TYPE_EDITTEXT, pwd.getTag(),
						pwd.getHint(), pwd.getListValues(), "", 0, InputType.TYPE_TEXT_VARIATION_PASSWORD,
						pwd.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, postal.getHint(), uiUtils.TYPE_EDITTEXT, postal.getTag(),
						postal.getHint(), postal.getListValues(), "", 0, InputType.TYPE_NUMBER_FLAG_SIGNED,
						postal.isMandatory());
				uiUtils.setFormContentItem2inSingleRow(layEndUserItems, email.getHint(), uiUtils.TYPE_EDITTEXT,
						email.getTag(), email.getHint(), email.getListValues(), "", 0,
						InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, email.isMandatory(), phone.getHint(),
						uiUtils.TYPE_EDITTEXT, phone.getTag(), phone.getHint(), phone.getListValues(), "", 0,
						InputType.TYPE_CLASS_PHONE, phone.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, permission_cry.getHint(), uiUtils.TYPE_SPINNER,
						permission_cry.getTag(), permission_cry.getHint(), permission_cry.getListValues(), "", 0, -1,
						permission_cry.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, gender.getHint(), uiUtils.TYPE_SPINNER, gender.getTag(),
						gender.getHint(), gender.getListValues(), "", 0, -1, gender.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, age_group.getHint(), uiUtils.TYPE_SPINNER,
						age_group.getTag(), age_group.getHint(), age_group.getListValues(), "", 0, -1,
						age_group.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, marital_status.getHint(), uiUtils.TYPE_SPINNER,
						marital_status.getTag(), marital_status.getHint(), marital_status.getListValues(), "", 0, -1,
						marital_status.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, education.getHint(), uiUtils.TYPE_SPINNER,
						education.getTag(), education.getHint(), education.getListValues(), "", 0, -1,
						education.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, employment.getHint(), uiUtils.TYPE_SPINNER,
						employment.getTag(), employment.getHint(), employment.getListValues(), "", 0, -1,
						employment.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, union_member.getHint(), uiUtils.TYPE_SPINNER,
						union_member.getTag(), union_member.getHint(), union_member.getListValues(), "", 0, -1,
						union_member.isMandatory());
				uiUtils.setFormContentItem2inSingleRow(layEndUserItems, union_name.getHint(), uiUtils.TYPE_EDITTEXT,
						union_name.getTag(), union_name.getHint(), union_name.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, union_name.isMandatory(), union_local.getHint(),
						uiUtils.TYPE_EDITTEXT, union_local.getTag(), union_local.getHint(), union_local.getListValues(),
						"", 0, InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, union_local.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, political_party.getHint(), uiUtils.TYPE_EDITTEXT,
						political_party.getTag(), political_party.getHint(), political_party.getListValues(), "", 0, -1,
						political_party.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, military_service.getHint(), uiUtils.TYPE_SPINNER,
						military_service.getTag(), military_service.getHint(), military_service.getListValues(), "", 0,
						-1, military_service.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, immigration_status.getHint(), uiUtils.TYPE_SPINNER,
						immigration_status.getTag(), immigration_status.getHint(), immigration_status.getListValues(),
						"", 0, -1, immigration_status.isMandatory());
				uiUtils.setFormContentItem2inSingleRow(layEndUserItems, town_of_birth.getHint(), uiUtils.TYPE_EDITTEXT,
						town_of_birth.getTag(), town_of_birth.getHint(), town_of_birth.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, town_of_birth.isMandatory(),
						current_town_of_residence.getHint(), uiUtils.TYPE_EDITTEXT, current_town_of_residence.getTag(),
						current_town_of_residence.getHint(), current_town_of_residence.getListValues(), "", 0,
						InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, current_town_of_residence.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, current_residence.getHint(), uiUtils.TYPE_SPINNER,
						current_residence.getTag(), current_residence.getHint(), current_residence.getListValues(), "",
						0, -1, current_residence.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, type_of_residence.getHint(), uiUtils.TYPE_SPINNER,
						type_of_residence.getTag(), type_of_residence.getHint(), type_of_residence.getListValues(), "",
						0, -1, type_of_residence.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, number_of_members_in_household.getHint(),
						uiUtils.TYPE_EDITTEXT, number_of_members_in_household.getTag(),
						number_of_members_in_household.getHint(), number_of_members_in_household.getListValues(), "", 0,
						InputType.TYPE_NUMBER_FLAG_SIGNED, number_of_members_in_household.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, number_of_dependants.getHint(), uiUtils.TYPE_EDITTEXT,
						number_of_dependants.getTag(), number_of_dependants.getHint(),
						number_of_dependants.getListValues(), "", 0, InputType.TYPE_NUMBER_FLAG_SIGNED,
						number_of_dependants.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, length_of_time_at_this_residence.getHint(),
						uiUtils.TYPE_EDITTEXT, length_of_time_at_this_residence.getTag(),
						length_of_time_at_this_residence.getHint(), length_of_time_at_this_residence.getListValues(),
						"", 0, InputType.TYPE_NUMBER_FLAG_SIGNED, length_of_time_at_this_residence.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, first_language.getHint(), uiUtils.TYPE_SPINNER,
						first_language.getTag(), first_language.getHint(), first_language.getListValues(), "", 0, -1,
						first_language.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, additional_language.getHint(), uiUtils.TYPE_SPINNER,
						additional_language.getTag(), additional_language.getHint(),
						additional_language.getListValues(), "", 0, -1, additional_language.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, self_identified_ethnic_background.getHint(),
						uiUtils.TYPE_SPINNER, self_identified_ethnic_background.getTag(),
						self_identified_ethnic_background.getHint(), self_identified_ethnic_background.getListValues(),
						"", 0, -1, self_identified_ethnic_background.isMandatory());
				uiUtils.setFormContentItem(layEndUserItems, self_identified_religion.getHint(), uiUtils.TYPE_SPINNER,
						self_identified_religion.getTag(), self_identified_religion.getHint(),
						self_identified_religion.getListValues(), "", 0, -1, self_identified_religion.isMandatory());
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
