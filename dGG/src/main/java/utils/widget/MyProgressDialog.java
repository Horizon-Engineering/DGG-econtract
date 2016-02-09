//package utils.widget;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//import ca.dgg.e_contract.R;
//
//public class MyProgressDialog extends ProgressDialog {
//
//	View rootView;
//
//	public MyProgressDialog(Context context) {
//		super(context, R.style.ThemeDialogCustom);
//		LayoutInflater mInflater = (LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		rootView = mInflater.inflate(R.layout.custom_progress_dialog, null);
//		rootView.setBackgroundColor(Color.TRANSPARENT);
//	}
//
//	@Override
//	public void show() {
//		super.show();
//		setContentView(R.layout.custom_progress_dialog);
//	}
//
//	@Override
//	public void setMessage(CharSequence message) {
//		TextView txtM = (TextView) rootView.findViewById(R.id.txtMessage);
//		txtM.setText(message);
//	};
//}