package ca.dgg.e_contract;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import utils.others.CSVUtils;
import utils.others.FuncUtils;

public class LandingActivity extends FragmentActivity {

	ImageView imgHeaderBack;
	TextView txtAppHeader;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_landing_page);
		FuncUtils.changeStatusBarColor(context);
		CSVUtils.configCSVs(context);
		imgHeaderBack = (ImageView) findViewById(R.id.imgHeaderBack);
		imgHeaderBack.setVisibility(View.VISIBLE);
		txtAppHeader = (TextView) findViewById(R.id.txtAppHeader);
		txtAppHeader.setText("DGG E-Contract");
		findViewById(R.id.imgHeaderBack).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		findViewById(R.id.imgHeaderBack).setVisibility(View.INVISIBLE);
		findViewById(R.id.layContractForm).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(context, ContractActivity.class);
				startActivity(in);
				overridePendingTransition(R.anim.slide_forward, 0);
			}
		});
		findViewById(R.id.layEndUserForm).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(context, EndUserActivity.class);
				startActivity(in);
				overridePendingTransition(R.anim.slide_forward, 0);
			}
		});
		findViewById(R.id.laySuperUserForm).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(context, SuperDataUserActivity.class);
				startActivity(in);
				overridePendingTransition(R.anim.slide_forward, 0);
			}
		});
	}

	Context context = this;

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_backward, 0);
	}

}
