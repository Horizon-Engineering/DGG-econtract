/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utils.widget.vpi_lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ca.dgg.e_contract.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
@SuppressLint({ "NewApi", "ResourceAsColor" })
public class TabPageIndicator_Custom extends HorizontalScrollView implements PageIndicator {
	/** Title text used when no title is provided by the adapter. */
	private static final CharSequence EMPTY_TITLE = "";

	/**
	 * Interface for a callback when the selected tab has been reselected.
	 */
	public interface OnTabReselectedListener {
		/**
		 * Callback when the selected tab has been reselected.
		 * 
		 * @param position
		 *            Position of the current center item.
		 */
		void onTabReselected(int position);
	}

	private Runnable mTabSelector;

	@SuppressLint("ResourceAsColor")
	@SuppressWarnings("unused")
	// private final OnClickListener mTabClickListener = new OnClickListener() {
	// public void onClick(final View view) {
	// final RippleView rippleView = (RippleView) mTabLayout
	// .findViewById(R.id.ripTabItem);
	// rippleView.setRippleColor(R.color.rippelColorOrange);
	// rippleView
	// .setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
	//
	// @Override
	// public void onComplete(RippleView rippleView) {
	// TabView tabView = (TabView) view;
	// final int oldSelected = mViewPager.getCurrentItem();
	// final int newSelected = tabView.getIndex();
	// mViewPager.setCurrentItem(newSelected);
	// if (oldSelected == newSelected
	// && mTabReselectedListener != null) {
	// mTabReselectedListener
	// .onTabReselected(newSelected);
	// }
	// }
	//
	// });
	//
	// }
	// };
	private final IcsLinearLayout mTabLayout;

	private ViewPager mViewPager;
	private ViewPager.OnPageChangeListener mListener;

	private int mMaxTabWidth;
	private int mSelectedTabIndex;

	private OnTabReselectedListener mTabReselectedListener;

	public TabPageIndicator_Custom(Context context) {
		this(context, null);
	}

	public TabPageIndicator_Custom(Context context, AttributeSet attrs) {
		super(context, attrs);
		setHorizontalScrollBarEnabled(false);

		mTabLayout = new IcsLinearLayout(context, R.attr.vpiTabPageIndicatorStyle);
		addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
	}

	public void setOnTabReselectedListener(OnTabReselectedListener listener) {
		mTabReselectedListener = listener;
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
		setFillViewport(lockedExpanded);

		final int childCount = mTabLayout.getChildCount();
		if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
			if (childCount > 2) {
				mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
			} else {
				mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
			}
		} else {
			mMaxTabWidth = -1;
		}

		final int oldWidth = getMeasuredWidth();
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int newWidth = getMeasuredWidth();

		if (lockedExpanded && oldWidth != newWidth) {
			// Recenter the tab display if we're at a new (scrollable) size.
			setCurrentItem(mSelectedTabIndex);
		}
	}

	private void animateToTab(final int position) {
		final View tabView = mTabLayout.getChildAt(position);

		if (mTabSelector != null) {
			removeCallbacks(mTabSelector);
		}
		mTabSelector = new Runnable() {
			public void run() {
				final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
				smoothScrollTo(scrollPos, 0);
				mTabSelector = null;
			}
		};
		post(mTabSelector);
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (mTabSelector != null) {
			// Re-post the selector we saved
			post(mTabSelector);
		}
	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (mTabSelector != null) {
			removeCallbacks(mTabSelector);
		}
	}

	View parentView;

	@SuppressLint("InflateParams")
	private void addTab(int index, CharSequence text, int iconResId, String url) {
		// TODO
		final TabView tabView = new TabView(getContext());
		tabView.mIndex = index;
		LinearLayout linearLayout = new LinearLayout(getContext());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setTag(index);
		linearLayout.setFocusable(true);
		linearLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout tabView = (LinearLayout) v;
				final int oldSelected = mViewPager.getCurrentItem();
				final int newSelected = Integer.valueOf(tabView.getTag().toString());
				mViewPager.setCurrentItem(newSelected);
				if (oldSelected == newSelected && mTabReselectedListener != null) {
					mTabReselectedListener.onTabReselected(newSelected);
				}
			}
		});

		parentView = LayoutInflater.from(getContext()).inflate(R.layout.vpi_tap_custom, null);
		ImageView img = (ImageView) parentView.findViewById(R.id.tabImage);
		// img.setImageResource(iconResId);
		/*
		 * ProgressBar pbBar = (ProgressBar)
		 * view.findViewById(R.id.progressBar_tab_custom);
		 */
		// LinearLayout layout = (LinearLayout)
		// view.findViewById(R.id.vpi_custom_layout);
		TextView txtTitle = (TextView) parentView.findViewById(R.id.vpi_custom_txt_Title);
		TextView txtBottom = (TextView) parentView.findViewById(R.id.vpi_custom_txt_btm);
		// TODO
		// LinearLayout.LayoutParams layoutParams = new
		// LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		// layout.setLayoutParams(layoutParams);

		txtTitle.setText(text);
		txtTitle.setTextColor(getResources().getColor(R.color.vpi_non_focus));
		txtBottom.setBackgroundColor(getResources().getColor(R.color.vpi_non_focus));

		/*
		 * AQuery aq = new AQuery(getContext());
		 * aq.id(img).progress(pbBar).image(url, false, true, 0,
		 * R.drawable.img_default_category, new BitmapAjaxCallback() {
		 * 
		 * @SuppressLint("NewApi") @SuppressWarnings("deprecation")
		 * 
		 * @Override protected void callback(String url, ImageView iv, Bitmap
		 * bm, AjaxStatus status) { int sdk = android.os.Build.VERSION.SDK_INT;
		 * if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		 * iv.setBackgroundDrawable(new
		 * BitmapDrawable(getContext().getResources(), bm)); } else {
		 * iv.setBackground(new BitmapDrawable(getContext().getResources(),
		 * bm)); } } });
		 */

		linearLayout.addView(parentView);
		linearLayout.addView(tabView);

		mTabLayout.addView(linearLayout, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1));
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		if (mListener != null) {
			mListener.onPageScrollStateChanged(arg0);
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		if (mListener != null) {
			mListener.onPageScrolled(arg0, arg1, arg2);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		setCurrentItem(arg0);
		if (mListener != null) {
			mListener.onPageSelected(arg0);
		}
	}

	@Override
	public void setViewPager(ViewPager view) {
		if (mViewPager == view) {
			return;
		}
		if (mViewPager != null) {
			mViewPager.setOnPageChangeListener(null);
		}
		final PagerAdapter adapter = view.getAdapter();
		if (adapter == null) {
			throw new IllegalStateException("ViewPager does not have adapter instance.");
		}
		mViewPager = view;
		view.setOnPageChangeListener(this);
		notifyDataSetChanged();
	}

	public void notifyDataSetChanged() {
		mTabLayout.removeAllViews();
		PagerAdapter adapter = mViewPager.getAdapter();
		IconPagerAdapter iconAdapter = null;
		if (adapter instanceof IconPagerAdapter) {
			iconAdapter = (IconPagerAdapter) adapter;
		}
		final int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			CharSequence title = adapter.getPageTitle(i);
			if (title == null) {
				title = EMPTY_TITLE;
			}
			int iconResId = 0;
			if (iconAdapter != null) {
				iconResId = iconAdapter.getIconResId(i);
			}
			String iconUrl = "";
			if (iconAdapter != null) {
				iconUrl = iconAdapter.getIconUrl(i);
			}
			addTab(i, title, iconResId, iconUrl);
		}
		if (mSelectedTabIndex > count) {
			mSelectedTabIndex = count - 1;
		}
		setCurrentItem(mSelectedTabIndex);
		requestLayout();
	}

	@Override
	public void setViewPager(ViewPager view, int initialPosition) {
		setViewPager(view);
		setCurrentItem(initialPosition);
	}

	@Override
	public void setCurrentItem(final int item) {

		// final RippleView rippleView = (RippleView) mTabLayout
		// .findViewById(R.id.ripTabItem);
		// rippleView.setRippleColor(R.color.rippelColorOrange);
		// rippleView
		// .setOnRippleCompleteListener(new
		// RippleView.OnRippleCompleteListener() {
		//
		// @Override
		// public void onComplete(RippleView rippleView) {
		if (mViewPager == null) {
			throw new IllegalStateException("ViewPager has not been bound.");
		}
		mSelectedTabIndex = item;
		mViewPager.setCurrentItem(item);

		final int tabCount = mTabLayout.getChildCount();
		for (int i = 0; i < tabCount; i++) {
			final View child = mTabLayout.getChildAt(i);
			final boolean isSelected = (i == item);
			child.setSelected(isSelected);

			// TODO - Trigger (Arrow) Setting
			// Toast.makeText(getContext(),
			// "Selected ID :"+item,
			// Toast.LENGTH_SHORT).show();
			// ImageView img = (ImageView)
			// child.findViewById(R.id.img_trigger);
			TextView txtTitle = (TextView) child.findViewById(R.id.vpi_custom_txt_Title);
			TextView txtBottom = (TextView) child.findViewById(R.id.vpi_custom_txt_btm);

			if (isSelected) {
				animateToTab(item);
				txtBottom.setBackgroundColor(getResources().getColor(R.color.vpi_focus));
				txtBottom.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 8));
				txtTitle.setTextColor(getResources().getColor(R.color.vpi_focus));
				txtTitle.setTypeface(null, Typeface.BOLD);
				// img.setVisibility(View.VISIBLE);
			} else {
				txtBottom.setBackgroundColor(getResources().getColor(R.color.vpi_non_focus));
				txtBottom.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
				txtTitle.setTextColor(getResources().getColor(R.color.vpi_non_focus));
				txtTitle.setTypeface(null, Typeface.NORMAL);
				// img.setVisibility(View.INVISIBLE);
			}
		}
		// }
		//
		// });

	}

	@Override
	public void setOnPageChangeListener(OnPageChangeListener listener) {
		mListener = listener;
	}

	private class TabView extends TextView {
		private int mIndex;

		public TabView(Context context) {
			super(context, null, R.attr.vpiTabPageIndicatorStyle);
		}

		@Override
		public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);

			// Re-measure if we went beyond our maximum size.
			if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
				super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
			}
		}

		public int getIndex() {
			return mIndex;
		}
	}
}
