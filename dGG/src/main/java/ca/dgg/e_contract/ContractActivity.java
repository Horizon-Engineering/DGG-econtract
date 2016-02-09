package ca.dgg.e_contract;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import utils.others.CSVUtils;
import utils.others.DataUtils;
import utils.others.FuncUtils;
import utils.others.UiUtils;

public class ContractActivity extends FragmentActivity {

	ImageView imgHeaderBack;
	TextView txtAppHeader;
	LinearLayout layEndUserItems;
	String date1, date2, date3, date4, name1, name2, name3, name4, name5, amount1, amoount2;
	// UiUtils uiUtils;
	EditText etDate1Day, etDate1Month, etDate1Year, etDate2Day, etDate2Month, etDate2Year, etDate3Day, etDate3Month,
			etDate3Year, etDate4Day, etDate4Month, etDate4Year, etName1, etName2, etName3, etName4, etName5, etAmount1,
			etAmount2;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_contract);
		FuncUtils.changeStatusBarColor(context);
		initLibrary();
		// uiUtils = UiUtils.getInstance(context);
		imgHeaderBack = (ImageView) findViewById(R.id.imgHeaderBack);
		imgHeaderBack.setVisibility(View.VISIBLE);
		txtAppHeader = (TextView) findViewById(R.id.txtAppHeader);
		txtAppHeader.setText("DGG E-Contract");
		layEndUserItems = (LinearLayout) findViewById(R.id.layEndUserItems);
		findViewById(R.id.btnAgree).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//saveContract();
				doSuccessProcess(true);
			}
		});
		findViewById(R.id.imgHeaderBack).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		etDate1Day = (EditText) findViewById(R.id.etDate1);
		etDate1Month = (EditText) findViewById(R.id.etMonth1);
		etDate1Year = (EditText) findViewById(R.id.etYear1);

		etDate2Day = (EditText) findViewById(R.id.etDate2);
		etDate2Month = (EditText) findViewById(R.id.etMonth2);
		etDate2Year = (EditText) findViewById(R.id.etYear2);

		etDate3Day = (EditText) findViewById(R.id.etDate3);
		etDate3Month = (EditText) findViewById(R.id.etMonth3);
		etDate3Year = (EditText) findViewById(R.id.etYear3);

		etDate4Day = (EditText) findViewById(R.id.etDate4);
		etDate4Month = (EditText) findViewById(R.id.etMonth4);
		etDate4Year = (EditText) findViewById(R.id.etYear4);

		etName1 = (EditText) findViewById(R.id.etName1);
		etName2 = (EditText) findViewById(R.id.etName11);
		etName3 = (EditText) findViewById(R.id.etPrint1);
		etName4 = (EditText) findViewById(R.id.etName12);
		etName5 = (EditText) findViewById(R.id.etPrint2);

		etAmount1 = (EditText) findViewById(R.id.etAmount1);
		etAmount2 = (EditText) findViewById(R.id.etAmount2);

		etDate1Day.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate1Month.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate1Month.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate1Year.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate1Year.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 4)
						etName1.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate2Day.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate2Month.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate2Month.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate2Year.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate2Year.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 4)
						UiUtils.getInstance(context).hideKeyboard(etDate2Year);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		etDate3Day.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate3Month.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate3Month.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate3Year.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate3Year.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 4)
						etName2.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		etDate4Day.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate4Month.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate4Month.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 2)
						etDate4Year.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		etDate4Year.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				try {
					if (s.length() == 4)
						UiUtils.getInstance(context).hideKeyboard(etDate4Year);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		// assignDummyContent();
	}

	private void assignDummyContent() {
		// TODO Auto-generated method stub
		etDate1Day.setText("12");
		etDate1Month.setText("12");
		etDate1Year.setText("2012");
		etDate2Day.setText("20");
		etDate2Month.setText("12");
		etDate2Year.setText("2012");
		etDate3Day.setText("12");
		etDate3Month.setText("12");
		etDate3Year.setText("2012");
		etDate4Day.setText("12");
		etDate4Month.setText("12");
		etDate4Year.setText("2012");
		etName1.setText("Umayaeswaran");
		etName2.setText("Umayaeswaran");
		etName3.setText("Umayaeswaran");
		etName4.setText("Umayaeswaran");
		etName5.setText("Umayaeswaran");
		etAmount1.setText("250");
		etAmount2.setText("25");
	}

	Context context = this;

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_backward, 0);
	}

	// boolean isValid = true;

	// void setValid(boolean value) {
	// if (!value)
	// isValid = false;
	// }

	void saveContract() {

		try {
			ArrayList<Boolean> result = new ArrayList<>();
			String sd1date = UiUtils.getStringFromET(etDate1Day);
			String sd1month = UiUtils.getStringFromET(etDate1Month);
			String sd1year = UiUtils.getStringFromET(etDate1Year);
			String sd2date = UiUtils.getStringFromET(etDate2Day);
			String sd2month = UiUtils.getStringFromET(etDate2Month);
			String sd2year = UiUtils.getStringFromET(etDate2Year);
			String sd3date = UiUtils.getStringFromET(etDate3Day);
			String sd3month = UiUtils.getStringFromET(etDate3Month);
			String sd3year = UiUtils.getStringFromET(etDate3Year);
			String sd4date = UiUtils.getStringFromET(etDate4Day);
			String sd4month = UiUtils.getStringFromET(etDate4Month);
			String sd4year = UiUtils.getStringFromET(etDate4Year);
			String sname1 = UiUtils.getStringFromET(etName1);
			String sname2 = UiUtils.getStringFromET(etName2);
			String sname3 = UiUtils.getStringFromET(etName3);
			String sname4 = UiUtils.getStringFromET(etName4);
			String sname5 = UiUtils.getStringFromET(etName5);
			String samount1 = UiUtils.getStringFromET(etAmount1);
			String samount2 = UiUtils.getStringFromET(etAmount2);

			if (sd1date.length() != 0 && sd1month.length() != 0 && sd1year.length() != 0) {
				date1 = sd1date + "/" + sd1month + "/" + sd1year;
				result.add(true);
			} else {
				date1 = "";
				result.add(false);
				// setValid(false);
				if (sd1date.length() == 0) {
					etDate1Day.setError("Invalid!");
				}
				if (sd1month.length() == 0) {
					etDate1Month.setError("Invalid!");
				}
				if (sd1year.length() == 0) {
					etDate1Year.setError("Invalid!");
				}
			}

			if (sd2date.length() != 0 && sd2month.length() != 0 && sd2year.length() != 0) {
				date2 = sd2date + "/" + sd2month + "/" + sd2year;
				result.add(true);
			} else {
				date2 = "";
				result.add(false);
				if (sd2date.length() == 0) {
					etDate2Day.setError("Invalid!");
				}
				if (sd2month.length() == 0) {
					etDate2Month.setError("Invalid!");
				}
				if (sd2year.length() == 0) {
					etDate2Year.setError("Invalid!");
				}
			}

			if (sd3date.length() != 0 && sd3month.length() != 0 && sd3year.length() != 0) {
				date3 = sd3date + "/" + sd3month + "/" + sd3year;
				result.add(true);
			} else {
				date3 = "";
				result.add(false);
				if (sd3date.length() == 0) {
					etDate3Day.setError("Invalid!");
				}
				if (sd3month.length() == 0) {
					etDate3Month.setError("Invalid!");
				}
				if (sd3year.length() == 0) {
					etDate3Year.setError("Invalid!");
				}
			}

			if (sd4date.length() != 0 && sd4month.length() != 0 && sd4year.length() != 0) {
				date4 = sd4date + "/" + sd4month + "/" + sd4year;
				result.add(true);
			} else {
				date4 = "";
				result.add(false);
				if (sd4date.length() == 0) {
					etDate4Day.setError("Invalid!");
				}
				if (sd4month.length() == 0) {
					etDate4Month.setError("Invalid!");
				}
				if (sd4year.length() == 0) {
					etDate4Year.setError("Invalid!");
				}
			}

			if (sname1.length() != 0) {
				name1 = sname1;
				result.add(true);
			} else {
				name1 = "";
				result.add(false);
				etName1.setError("Invalid!");
			}

			if (sname2.length() != 0) {
				name2 = sname2;
				result.add(true);
			} else {
				name2 = "";
				result.add(false);
				etName2.setError("Invalid!");
			}

			if (sname3.length() != 0) {
				name3 = sname3;
				result.add(true);
			} else {
				name3 = "";
				result.add(false);
				etName3.setError("Invalid!");
			}

			if (sname4.length() != 0) {
				name4 = sname4;
				result.add(true);
			} else {
				name4 = "";
				result.add(false);
				etName4.setError("Invalid!");
			}

			if (sname5.length() != 0) {
				name5 = sname5;
				result.add(true);
			} else {
				name5 = "";
				result.add(false);
				etName5.setError("Invalid!");
			}

			if (samount1.length() != 0) {
				amount1 = samount1;
				result.add(true);
			} else {
				amount1 = "";
				result.add(false);
				etAmount1.setError("Invalid!");
			}

			if (samount2.length() != 0) {
				amoount2 = samount2;
				result.add(true);
			} else {
				amoount2 = "";
				result.add(false);
				etAmount2.setError("Invalid!");
			}

			if (!result.contains(false)) {
				String contract_template_1 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_1.txt");
				String contract_template_2 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_2.txt");
				String contract_template_3 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_3.txt");
				String contract_template_4 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_4.txt");
				String contract_template_5 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_5.txt");
				String contract_template_6 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_6.txt");
				String contract_template_7 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_7.txt");
				String contract_template_8 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_8.txt");
				String contract_template_9 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_9.txt");
				String contract_template_10 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_10.txt");
				String contract_template_11 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_11.txt");
				String contract_template_12 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_12.txt");
				String contract_template_13 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_13.txt");
				String contract_template_14 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_14.txt");
				String contract_template_15 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_15.txt");
				String contract_template_16 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_16.txt");
				String contract_template_17 = DataUtils.getInstance(context).readFromfile(context,
						"contract_template_17.txt");
				contract_template_1 = contract_template_1.replace("__DATE1__", date1);
				contract_template_2 = contract_template_2.replace("__DATE2__", date2);
				contract_template_12 = contract_template_12.replace("__DATE3__", date3);
				contract_template_17 = contract_template_17.replace("__DATE4__", date4);
				contract_template_1 = contract_template_1.replace("__NAME1__", name1);
				contract_template_13 = contract_template_13.replace("__NAME2__", name2);
				contract_template_14 = contract_template_14.replace("__NAME3__", name3);
				contract_template_15 = contract_template_15.replace("__NAME4__", name4);
				contract_template_16 = contract_template_16.replace("__NAME5__", name5);
				contract_template_12 = contract_template_12.replace("__AMOUNT1__", amount1);
				contract_template_12 = contract_template_12.replace("__AMOUNT2__", amoount2);
				String am = amount1 + "." + amoount2;
				if (Double.valueOf(am) > 0) {
					DataUtils.getInstance(context).getSPref(context).edit().putString(DataUtils.PREF_AMOUNT, am)
							.commit();
					Document doc = new Document();

					try {
						File newFile = getOutputPdfFile(name1);
						newFile.createNewFile();
						FileOutputStream pdfFile = new FileOutputStream(newFile);
						// pdfFile.write(mPDFWriter.asString().getBytes("ISO-8859-1"));
						// pdfFile.close();

						PdfWriter.getInstance(doc, pdfFile);

						Font titleFont = new Font(Font.BOLD);
						Font paraFont = new Font(Font.TIMES_ROMAN);
						// open the document
						doc.open();
						// Font titleFont = new Font(Font.COURIER,15, 12,
						// Font paraFont = new Font(Font.COURIER,12,
						// Color.BLACK);
						Paragraph title = new Paragraph("DGG E-CONTRACT");
						title.setAlignment(Paragraph.ALIGN_CENTER);
						title.setFont(titleFont);

						// add paragraph to document
						doc.add(title);
						doc.add(Chunk.NEWLINE);
						doc.add(Chunk.NEWLINE);
						doc.add(Chunk.NEWLINE);

						Paragraph p1 = new Paragraph(contract_template_1);
						p1.setAlignment(Paragraph.ALIGN_LEFT);
						p1.setFont(paraFont);
						doc.add(p1);

						doc.add(Chunk.NEWLINE);

						Paragraph p2 = new Paragraph(contract_template_2);
						p2.setAlignment(Paragraph.ALIGN_LEFT);
						p2.setFont(paraFont);
						doc.add(p2);
						doc.add(Chunk.NEWLINE);

						Paragraph p3 = new Paragraph(contract_template_3);
						p3.setAlignment(Paragraph.ALIGN_LEFT);
						p3.setFont(paraFont);
						doc.add(p3);
						doc.add(Chunk.NEWLINE);

						Paragraph p4 = new Paragraph(contract_template_4);
						p4.setAlignment(Paragraph.ALIGN_LEFT);
						p4.setFont(paraFont);
						doc.add(p4);
						doc.add(Chunk.NEWLINE);

						Paragraph p5 = new Paragraph(contract_template_5);
						p5.setAlignment(Paragraph.ALIGN_LEFT);
						p5.setFont(paraFont);
						doc.add(p5);
						doc.add(Chunk.NEWLINE);

						Paragraph p6 = new Paragraph(contract_template_6);
						p6.setAlignment(Paragraph.ALIGN_LEFT);
						p6.setFont(paraFont);
						doc.add(p6);
						doc.add(Chunk.NEWLINE);

						Paragraph p7 = new Paragraph(contract_template_7);
						p7.setAlignment(Paragraph.ALIGN_LEFT);
						p7.setFont(paraFont);
						doc.add(p7);
						doc.add(Chunk.NEWLINE);

						Paragraph p8 = new Paragraph(contract_template_8);
						p8.setAlignment(Paragraph.ALIGN_LEFT);
						p8.setFont(paraFont);
						doc.add(p8);
						doc.add(Chunk.NEWLINE);

						Paragraph p9 = new Paragraph(contract_template_9);
						p9.setAlignment(Paragraph.ALIGN_LEFT);
						p9.setFont(paraFont);
						doc.add(p9);

						Paragraph p10 = new Paragraph(contract_template_10);
						p10.setAlignment(Paragraph.ALIGN_LEFT);
						p10.setFont(paraFont);
						doc.add(p10);
						doc.add(Chunk.NEWLINE);

						Paragraph p11 = new Paragraph(contract_template_11);
						p11.setAlignment(Paragraph.ALIGN_LEFT);
						p11.setFont(paraFont);
						doc.add(p11);

						Paragraph p12 = new Paragraph(contract_template_12);
						p12.setAlignment(Paragraph.ALIGN_LEFT);
						p12.setFont(paraFont);
						doc.add(p12);
						doc.add(Chunk.NEWLINE);
						doc.add(Chunk.NEWLINE);

						Paragraph p13 = new Paragraph(contract_template_13);
						p13.setAlignment(Paragraph.ALIGN_LEFT);
						p13.setFont(paraFont);
						doc.add(p13);
						doc.add(Chunk.NEWLINE);

						Paragraph p14 = new Paragraph(contract_template_14);
						p14.setAlignment(Paragraph.ALIGN_LEFT);
						p14.setFont(paraFont);
						doc.add(p14);
						doc.add(Chunk.NEWLINE);
						doc.add(Chunk.NEWLINE);

						Paragraph p15 = new Paragraph(contract_template_15);
						p15.setAlignment(Paragraph.ALIGN_LEFT);
						p15.setFont(paraFont);
						doc.add(p15);
						doc.add(Chunk.NEWLINE);

						Paragraph p16 = new Paragraph(contract_template_16);
						p16.setAlignment(Paragraph.ALIGN_LEFT);
						p16.setFont(paraFont);
						doc.add(p16);
						doc.add(Chunk.NEWLINE);

						Paragraph p17 = new Paragraph(contract_template_17);
						p17.setAlignment(Paragraph.ALIGN_LEFT);
						p17.setFont(paraFont);
						doc.add(p17);
						doc.add(Chunk.NEWLINE);

						doc.close();
					} catch (Exception de) {
						de.printStackTrace();
					}
					FuncUtils.showToast(context, "Contract Saved Successfully!!");
					showPaymentDialog();
				} else {
					FuncUtils.showToast(context, "Invalid Amount!");
				}

				// PDFWriter mPDFWriter = new PDFWriter(PaperSize.FOLIO_WIDTH,
				// PaperSize.FOLIO_HEIGHT);
				// mPDFWriter.setFont(StandardFonts.SUBTYPE,
				// StandardFonts.HELVETICA_BOLD);
				// mPDFWriter.addText(300, 850, 15, "DGG E-Contract");
				// mPDFWriter.setFont(StandardFonts.SUBTYPE,
				// StandardFonts.HELVETICA_OBLIQUE);
				// int fBottomMargin = 450;
				// mPDFWriter.addText(100, fBottomMargin - 30, 12,
				// contractTemplate);
				// File newFile = getOutputPdfFile(name1);
				// try {
				// newFile.createNewFile();
				// FileOutputStream pdfFile = new FileOutputStream(newFile);
				// pdfFile.write(mPDFWriter.asString().getBytes("ISO-8859-1"));
				// pdfFile.close();
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
			} else {
				FuncUtils.showToast(context, "Please Enter all the details!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private File getOutputPdfFile(String name) {
		File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "DGG Contracts");
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				return null;
			}
		}
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator + name + " contract_"
				+ FuncUtils.getDateForPdfName() + ".pdf");
		DataUtils.getInstance(context).getSPref(context).edit()
				.putString(DataUtils.PREF_CONTRACT_FILE_PATH, mediaFile.getAbsolutePath()).commit();
		return mediaFile;
	}

//	String LIVE_MAIL = "h.chawiche@digitalgovernancegroup.com";
//	String LIVE_MAIL = "vampiraate@gmail.com";
	String LIVE_MAIL = "rick.relf@gmail.com";
//	String LIVE_MAIL = "umesh@archerpenny.com";
	String DEBUG_MAIL = "umayaeswaran@gmail.com";

	String LIVE_CLIENT_ID = "AZjXuSDVa1GqTdlImEqzkp9mnE0lLjfCGcnNwz1uJtIQcYDPiwgrt_C-6gntwrXEQmBFCt1VDE7KPGlv";
	String SANBOX_CLIENT_ID = "AdGQT6gOcEE9owF6yiZLkX2TR5vGzFem-Sjj1Jbx-4cfRWOQfBvSDp1K_UV99KJLQ9GbN02GTY5s71zZ";

	String PAYPAL_MODE = PayPalConfiguration.ENVIRONMENT_PRODUCTION;
	String PAYPAL_CLIENT_ID = LIVE_CLIENT_ID;
	String MAIL_IN_USE = "dollar.rokka@gmail.com";

	private PayPalConfiguration config = new PayPalConfiguration().environment(PAYPAL_MODE).clientId(PAYPAL_CLIENT_ID);

	public void initLibrary() {
		Intent intent = new Intent(this, PayPalService.class);

		intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

		startService(intent);
	}

	@Override
	public void onDestroy() {
		stopService(new Intent(this, PayPalService.class));
		super.onDestroy();
	}

	public void PayPalButtonClick() {
		String am = DataUtils.getInstance(context).getSPref(context).getString(DataUtils.PREF_AMOUNT, "");
		if (Double.valueOf(am) > 0) {
			PayPalPayment payment = new PayPalPayment(new BigDecimal(am), "USD", "DGG Contract Payment",
					PayPalPayment.PAYMENT_INTENT_SALE);
			Intent intent = new Intent(this, PaymentActivity.class);
			// send the same configuration for restart resiliency
			intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
			intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
			startActivityForResult(intent, 0);
		} else {
			FuncUtils.showToast(context, "Invalid Amount!");
		}
	}

	private void showPaymentDialog() {
		// TODO Auto-generated method stub
		final String[] items = new String[] { "Credit/ Debit Cards", "Paypal", "Interac e-transfer" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, items);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setTitle("Pay Through...");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if (item == 0 || item == 1) {
					PayPalButtonClick();
				} else if (item == 2) {
					showMailPaymentDialog();
				}
			}
		});

		final AlertDialog dialog = builder.create();

		dialog.show();
	}

	public void hideKeyboard(View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	private void showMailPaymentDialog() {
		try {
			final Dialog dialog = new Dialog(context);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.pay_by_mail_dialog);
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
			final TextView txtCarYear = (TextView) dialog.findViewById(R.id.txtCarYear);

			final EditText etMail = (EditText) dialog.findViewById(R.id.etMail);
			final EditText etPin = (EditText) dialog.findViewById(R.id.etPIN);
			etMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						hideKeyboard(v);
					}
				}
			});
			etPin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (!hasFocus) {
						hideKeyboard(v);
					}
				}
			});
			dialog.findViewById(R.id.btnPay).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (etMail.getText().toString() != null && etMail.getText().toString().trim().length() != 0
							&& etPin.getText().toString() != null && etPin.getText().toString().trim().length() != 0
							&& FuncUtils.isValidEmail(etMail.getText().toString().trim())) {

						ArrayList<String> lst = new ArrayList<>();
						lst.add(etMail.getText().toString().trim());
						lst.add(etPin.getText().toString().trim());
						CSVUtils.writeCSV(context, lst, CSVUtils.FILE_INTERAC_E_TRANSFER);
						dialog.dismiss();
						doSuccessProcess(true);
					} else {
						String mail = "";
						try {
							mail = etMail.getText().toString();
							if (!FuncUtils.isValidEmail(mail))
								mail = "";
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (mail.length() != 0)
							etMail.setError("Invalid!");

						String pin = "";
						try {
							pin = etPin.getText().toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (pin.length() != 0)
							etPin.setError("Invalid!");
					}
				}
			});
			dialog.findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					dialog.dismiss();
				}
			});
			String am = DataUtils.getInstance(context).getSPref(context).getString(DataUtils.PREF_AMOUNT, "");
			if (Double.valueOf(am) > 0) {
				txtCarYear.setText("$" + am
						+ " can be paid through Interac e-transfer by simply entering your E-Mail and Password.");
				dialog.show();
			} else {
				FuncUtils.showToast(context, "Invalid Amount!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 121 && resultCode == RESULT_OK && null != data) {

			Bundle bundle = data.getExtras();
			String status = bundle.getString("status");
			if (status.equalsIgnoreCase("done")) {
				String b64 = bundle.getString("base64");
				FuncUtils.showToast(context, "Sign Captured!");
				// currentET.setText(b64);
				// btnSign.setText("Sign Captured");
				// Toast toast = Toast.makeText(this,
				// "Signature capture successful!", Toast.LENGTH_SHORT);
				// toast.setGravity(Gravity.TOP, 105, 50);
				// toast.show();
			}
		} else {
			switch (resultCode) {
			// The payment succeeded
			case Activity.RESULT_OK:
				// PaymentConfirmation confirm =
				// data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
				// if (confirm != null) {
				// try {
				// Log.i("paymentExample", confirm.toJSONObject().toString(4));
				//
				// } catch (JSONException e) {
				// Log.e("paymentExample", "an extremely unlikely failure
				// occurred: ", e);
				// }
				// }

				doSuccessProcess(false);
				break;

			// The payment was canceled
			case Activity.RESULT_CANCELED:
				FuncUtils.showNotifyDialog(context, "Sorry!. Payment got Cancelled. Try again later!");
				break;

			// The payment failed, get the error from the EXTRA_ERROR_ID and
			// EXTRA_ERROR_MESSAGE
			case PaymentActivity.RESULT_EXTRAS_INVALID:
				FuncUtils.showNotifyDialog(context, "Sorry!. Payment got failed. Try again later!.");
			}
		}

	}

	private void doSuccessProcess(boolean isPaidByMail) {
		DataUtils dataUtils = DataUtils.getInstance(context);

		ArrayList<String> lstE = dataUtils.convertToArray(DataUtils.PREF_END_USER_DATA);
		CSVUtils.writeCSV(context, lstE, CSVUtils.FILE_END_USER);

		ArrayList<String> lstS = dataUtils.convertToArray(DataUtils.PREF_SUPER_USER_DATA);
		CSVUtils.writeCSV(context, lstS, CSVUtils.FILE_SUPER_USER);
		// ArrayList<String> lstS =
		// dataUtils.convertToArray(DataUtils.PREF_SUPER_USER_DATA);
		// CSVUtils.writeCSV(context, lstS, CSVUtils.FILE_SUPER_USER);

		FuncUtils.showNotifyDialogAndExit(context,
				"Thankyou very much for your support. Looking forward for more support!");
		try {
			int SDK_INT = android.os.Build.VERSION.SDK_INT;

			if (SDK_INT > 8) {

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

				StrictMode.setThreadPolicy(policy);

			}
			String f1 = CSVUtils.getEndUserCSVFilePath(context);
			String f2 = CSVUtils.getSuperUserCSVFilePath(context);
			String f3 = CSVUtils.getContractPDFFilePath(context);
			boolean b = false;
			if (isPaidByMail) {
				String f4 = CSVUtils.getMailFileFilePath(context);
				b = FuncUtils.sendEmail(MAIL_IN_USE, "test.dgg.1@gmail.com", "Reg : DGG E-Contract Docs", "PFA.",
						new String[] { f1, f2, f3, f4 });
			} else {
				b = FuncUtils.sendEmail(MAIL_IN_USE, "test.dgg.1@gmail.com", "Reg : DGG E-Contract Doc", "PFA.",
						new String[] { f1, f2, f3 });
			}
			Log.d("TAG", String.valueOf(b));
			if (b) {
				CSVUtils.resetCSV(context, CSVUtils.FILE_END_USER);
				CSVUtils.resetCSV(context, CSVUtils.FILE_SUPER_USER);
				CSVUtils.resetCSV(context, CSVUtils.FILE_INTERAC_E_TRANSFER);
				// File contractFile = new
				// File(DataUtils.getInstance(context).getSPref(context)
				// .getString(DataUtils.PREF_CONTRACT_FILE_PATH, ""));
				CSVUtils.configCSVs(context);
			}
			// Log.d("TAG", String.valueOf(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
