package utils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewMedium extends TextView {


    public TextViewMedium(Context context) {
      super(context);
		init();
    }

    public TextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
		init();
    }

    public TextViewMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
		init();
    }

    private void init() {
		// TODO Auto-generated method stub
    	super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf"));
	}

	protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        
       
    }

}