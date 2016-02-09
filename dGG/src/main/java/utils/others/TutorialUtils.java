package utils.others;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import ca.dgg.e_contract.EndUserActivity;
import ca.dgg.e_contract.LandingActivity;
import ca.dgg.e_contract.R;
import utils.ripplebutton.widget.RippleButton;
import utils.widget.InkPageIndicator;

public class TutorialUtils {

	int[] tipsViews = { R.id.layTip1, R.id.layTip2, R.id.layTip3, R.id.layTip4, R.id.layTip5, R.id.layTip6,
			R.id.layTip7, R.id.layTip8, R.id.layTip9 };
	// String[] tipsBGColors = { "#6639B6", "#4CAF50", "#2095F2", "#FFC107",
	// "#9B26AF" };
	String[] tipsBGColors = { "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF", "#FFFFFF",
			"#FFFFFF" };
	int currentIndex = 0;
	// RubberIndicator rubberIndicator;
	InkPageIndicator indicator;
	Dialog dialog;
	ViewPager viewPager;
	// ScrollView scrlTips;
	// TextView btnGotIt;
	ImageView imgGetStarted;

	public void showEntertainTutorialDialog(final Context context) {
		try {
			dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.fragment_ppt);
			dialog.setCancelable(false);
			dialog.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					((Activity)context).finish();
					((Activity)context).overridePendingTransition(R.anim.slide_backward, 0);
				}
			});
			Window window = dialog.getWindow();
			WindowManager.LayoutParams wlp = window.getAttributes();
			wlp.gravity = Gravity.CENTER;
			wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
			window.setAttributes(wlp);
			dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT);
			dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation_1;
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

			// rubberIndicator = (RubberIndicator)
			// dialog.findViewById(R.id.rubber);
			indicator = (InkPageIndicator) dialog.findViewById(R.id.ink_pager_indicator);
			imgGetStarted = (ImageView) dialog.findViewById(R.id.imgGetStarted);
			imgGetStarted.setVisibility(View.VISIBLE);
			// imgGetStarted.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View arg0) {
			// // TODO Auto-generated method stub
			// Intent in = new Intent(context, EndUserActivity.class);
			// ((Activity) context).startActivity(in);
			// ((Activity) context).finish();
			// ((Activity)
			// context).overridePendingTransition(R.anim.slide_forward, 0);
			// }
			// });
			// rubberIndicator.setCount(5);
			viewPager = (ViewPager) dialog.findViewById(R.id.myviewpager);
			// scrlTips = (ScrollView) dialog.findViewById(R.id.scrlTips);
			MyPagerAdapter myPagerAdapter = new MyPagerAdapter(context);
			viewPager.setAdapter(myPagerAdapter);
			viewPager.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {
					// TODO Auto-generated method stub
					updateView(context, dialog, count - arg0);
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub

				}
			});
			indicator.setViewPager(viewPager);
			// rubberIndicator.setOnMoveListener(this);

			dialog.findViewById(R.id.layTip1).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {

				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 1);

				}
			});
			dialog.findViewById(R.id.layTip2).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {
					updateViewPager(context, dialog, 0);
				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 2);

				}
			});
			dialog.findViewById(R.id.layTip3).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {
					updateViewPager(context, dialog, 1);
				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 3);

				}
			});
			dialog.findViewById(R.id.layTip4).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {
					updateViewPager(context, dialog, 2);
				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 4);

				}
			});
			dialog.findViewById(R.id.layTip5).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {
					updateViewPager(context, dialog, 3);
				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 5);

				}
			});
			dialog.findViewById(R.id.layTip6).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {
					updateViewPager(context, dialog, 4);
				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 6);

				}
			});
			dialog.findViewById(R.id.layTip7).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {
					updateViewPager(context, dialog, 5);
				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 7);

				}
			});
			dialog.findViewById(R.id.layTip8).setOnTouchListener(new OnSwipeTouchListener(context) {
				@Override
				public void onSwipeRight() {
					updateViewPager(context, dialog, 6);
				}

				@Override
				public void onSwipeLeft() {
					updateViewPager(context, dialog, 8);

				}
			});
			// dialog.findViewById(R.id.layTip9).setOnTouchListener(new
			// OnSwipeTouchListener(context) {
			// @Override
			// public void onSwipeRight() {
			// updateViewPager(context, dialog, 7);
			// }
			//
			// @Override
			// public void onSwipeLeft() {
			// updateViewPager(context, dialog, 0);
			//
			// }
			// });
			dialog.findViewById(R.id.layTip9).setOnTouchListener(new OnSwipeAddtionalListener(context) {

				@Override
				public void onClick() {
					super.onClick();
					Intent in = new Intent(context, LandingActivity.class);
					((Activity) context).startActivity(in);
					// ((Activity) context).finish();
					((Activity) context).overridePendingTransition(R.anim.slide_forward, 0);
				}

				@Override
				public void onDoubleClick() {
					super.onDoubleClick();
					// your on onDoubleClick here
				}

				@Override
				public void onLongClick() {
					super.onLongClick();
					// your on onLongClick here
				}

				@Override
				public void onSwipeUp() {
					super.onSwipeUp();
					// your swipe up here
				}

				@Override
				public void onSwipeDown() {
					super.onSwipeDown();
					// your swipe down here.
				}

				@Override
				public void onSwipeLeft() {
					super.onSwipeLeft();
					updateViewPager(context, dialog, 8);
				}

				@Override
				public void onSwipeRight() {
					super.onSwipeRight();
					updateViewPager(context, dialog, 7);
				}
			});
			updateViewPager(context, dialog, currentIndex);
			dialog.show();
		} catch (

		Exception e)

		{
			e.printStackTrace();
		}
	}

	int count = 8;

	private void updateViewPager(Context context, Dialog dialog, int index) {
		// TODO Auto-generated method stub
		try {
			// if (count - index) {
			// viewPager.setCurrentItem(0);
			// } else if (index == 1) {
			// viewPager.setCurrentItem(1);
			// } else if (index == 2) {
			// viewPager.setCurrentItem(2);
			// } else if (index == 3) {
			// viewPager.setCurrentItem(3);
			// } else if (index == 4) {
			// viewPager.setCurrentItem(4);
			// } else if (index == 5) {
			// viewPager.setCurrentItem(5);
			// } else if (index == 6) {
			// viewPager.setCurrentItem(6);
			// } else if (index == 7) {
			// viewPager.setCurrentItem(7);
			// } else if (index == 8) {
			viewPager.setCurrentItem(count - index);
			// }
			// waitFor10Seconds(context, dialog, currentIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateView(Context context, Dialog dialog, int index) {
		// TODO Auto-generated method stub
		try {
			// scrlTips.setBackgroundColor(Color.parseColor(tipsBGColors[index]));
			// btnGotIt.setVisibility(View.INVISIBLE);
			if (index == 0) {
				if (currentIndex <= 1) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
				}
				// rubberIndicator.moveToRight();
				currentIndex = 1;

				// viewPager.setCurrentItem(0);
			} else if (index == 1) {
				if (currentIndex <= 2) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				}
				currentIndex = 2;
				// rubberIndicator.moveToRight();
				// viewPager.setCurrentItem(1);
			} else if (index == 2) {
				if (currentIndex <= 3) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				}
				currentIndex = 3;
				// rubberIndicator.moveToRight();
				// viewPager.setCurrentItem(2);
			} else if (index == 3) {
				if (currentIndex <= 3) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				}
				currentIndex = 4;
				// rubberIndicator.moveToRight();
				// viewPager.setCurrentItem(3);
			} else if (index == 4) {
				if (currentIndex <= 4) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				}
				currentIndex = 5;
				// viewPager.setCurrentItem(4);
				// rubberIndicator.setFocusPosition(0);
				// btnGotIt.setVisibility(View.VISIBLE);
			} else if (index == 5) {
				if (currentIndex <= 5) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				}
				currentIndex = 6;
				// viewPager.setCurrentItem(4);
				// rubberIndicator.setFocusPosition(0);
				// btnGotIt.setVisibility(View.VISIBLE);
			} else if (index == 6) {
				if (currentIndex <= 6) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				}
				currentIndex = 7;
				// viewPager.setCurrentItem(4);
				// rubberIndicator.setFocusPosition(0);
				// btnGotIt.setVisibility(View.VISIBLE);
			} else if (index == 7) {
				if (currentIndex <= 7 && currentIndex != 0) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[8]).setVisibility(View.GONE);
				}
				currentIndex = 8;
				// viewPager.setCurrentItem(4);
				// rubberIndicator.setFocusPosition(0);
				// btnGotIt.setVisibility(View.VISIBLE);
			} else if (index == 8) {
				if (currentIndex <= 8) {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_backward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
				} else {
					dialog.findViewById(tipsViews[index]).setVisibility(View.VISIBLE);
					dialog.findViewById(tipsViews[index])
							.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_forward));
					dialog.findViewById(tipsViews[4]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[3]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[2]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[1]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[0]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[5]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[6]).setVisibility(View.GONE);
					dialog.findViewById(tipsViews[7]).setVisibility(View.GONE);
				}
				currentIndex = 0;
				// viewPager.setCurrentItem(4);
				// rubberIndicator.setFocusPosition(0);
				// btnGotIt.setVisibility(View.VISIBLE);
			}
			// waitFor10Seconds(context, dialog, currentIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Handler tipsShowHandler;

	// private void waitFor10Seconds(final Context context, final Dialog dialog,
	// final int index) {
	// // TODO Auto-generated method stub
	// new Handler().postDelayed(new Runnable() {
	// @Override
	// public void run() {
	// if (dialog != null && dialog.isShowing())
	// updateViewPager(context, dialog, index);
	// }
	// }, 10000);
	// }

	public void dismissDialog() {
		if (dialog != null && dialog.isShowing())
			dialog.dismiss();
	}

	private class MyPagerAdapter extends PagerAdapter {

		int NumberOfPages = 9;
		Context context;

		public MyPagerAdapter(Context ct) {
			// TODO Auto-generated constructor stub
			context = ct;
		}

		// int[] res = { android.R.drawable.ic_dialog_alert,
		// android.R.drawable.ic_menu_camera,
		// android.R.drawable.ic_menu_compass,
		// android.R.drawable.ic_menu_directions,
		// android.R.drawable.ic_menu_gallery };
		// int[] backgroundcolor = { 0xFF101010, 0xFF202020, 0xFF303030,
		// 0xFF404040, 0xFF505050 };

		@Override
		public int getCount() {
			return NumberOfPages;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			View emptyView = new View(context);

			return emptyView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

}
