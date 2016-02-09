package utils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewRegular extends TextView {


    public TextViewRegular(Context context) {
      super(context);
		init();
    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
		init();
    }

    public TextViewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
		init();
    }

    private void init() {
		// TODO Auto-generated method stub
    	super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));
	}

	protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        
       
    }

}