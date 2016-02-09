package utils.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import ca.dgg.e_contract.R;

public class EditTextMedium extends EditText {

	// The image we are going to use for the Clear button
	private Drawable imgCloseButton = getResources().getDrawable(R.drawable.clear_et);

	public EditTextMedium(Context context) {
		super(context);
		init();
	}

	public EditTextMedium(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public EditTextMedium(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	void init() {
		super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf"));
		super.setBackgroundResource(R.drawable.et_white_bg);
		super.setTextColor(Color.BLACK);
		int paddingLeft = (int) getResources().getDimension(R.dimen.et_padding_left);
		int paddingRight = (int) getResources().getDimension(R.dimen.et_padding_right);
		int paddingTop = (int) getResources().getDimension(R.dimen.et_padding_top);
		int paddingBottom = (int) getResources().getDimension(R.dimen.et_padding_bottom);
		super.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
		// Set bounds of the Clear button so it will look ok
		imgCloseButton.setBounds(0, 0, imgCloseButton.getIntrinsicWidth(), imgCloseButton.getIntrinsicHeight());

		// There may be initial text in the field, so we may need to display the
		// button
		handleClearButton();

		// if the Close image is displayed and the user remove his finger from
		// the button, clear it. Otherwise do nothing
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				EditTextMedium et = EditTextMedium.this;

				if (et.getCompoundDrawables()[2] == null)
					return false;
				if (et.getCompoundDrawables()[2] != null && et.getCompoundDrawables()[2] != imgCloseButton)
					return false;
				if (event.getAction() != MotionEvent.ACTION_UP)
					return false;

				if (event.getX() > et.getWidth() - et.getPaddingRight() - imgCloseButton.getIntrinsicWidth()) {
					et.setText("");
					EditTextMedium.this.handleClearButton();
				}
				return false;
			}
		});

		// if text changes, take care of the button
		this.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

				EditTextMedium.this.handleClearButton();
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
		});
	}

	// intercept Typeface change and set it with our custom font
//	public void setTypeface(Typeface tf, int style) {
//		if (style == Typeface.BOLD) {
//			super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Vegur-B 0.602.otf"));
//		} else {
//			super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Vegur-R 0.602.otf"));
//		}
//	}

	void handleClearButton() {
		if (this.getText().toString().equals("")) {
			// add the clear button
			this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null,
					this.getCompoundDrawables()[3]);
		} else {
			// remove clear button
			this.setError(null);
			this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null,
					this.getCompoundDrawables()[3]);
			this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imgCloseButton,
					this.getCompoundDrawables()[3]);
		}
	}
}