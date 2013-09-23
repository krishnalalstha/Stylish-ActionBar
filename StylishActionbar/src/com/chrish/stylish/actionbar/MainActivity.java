package com.chrish.stylish.actionbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.chrish.imageswithcer.R;

public class MainActivity extends SherlockActivity {
	int imageIds[] = { R.drawable.ic_nepali_language,
			R.drawable.ic_english_language };
	String languages[] = { "english", "nepali" };
	ActionBar mActionBar;
	private ImageButton btnCatSetting;
	private TextView txtActionBarTitle;
	private ImageSwitcher imageSwitcher;
	private int currentIndex = -1;
	int messageCount=imageIds.length;
	 View customView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initActionBar();
	}

	private void initActionBar() {
		mActionBar = getSupportActionBar();
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setCustomView(getCustomView());
		mActionBar.setIcon(R.drawable.ic_home);

		

	}

	public View getCustomView() {

		LayoutInflater linflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		customView = linflater.inflate(
				R.layout.custom_home_action_bar_view, null);

		txtActionBarTitle = (TextView) customView
				.findViewById(R.id.txtActionBarTitle);

		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.RIGHT;
		customView.setLayoutParams(lp);
		btnCatSetting = (ImageButton) customView
				.findViewById(R.id.btnCatSetting);
		imageSwitcher = (ImageSwitcher) customView
				.findViewById(R.id.imageswitcher);
		imageSwitcher.setFactory(new ViewFactory() {

			public View makeView() {
				ImageView imageView = new ImageView(getApplicationContext());
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

				return imageView;
			}
		});

		imageSwitcher.setImageResource(R.drawable.ic_english_language);
		Animation in = AnimationUtils.loadAnimation(this, R.anim.push_up_in);
		Animation out = AnimationUtils.loadAnimation(this, R.anim.push_up_out);
		imageSwitcher.setInAnimation(in);
		imageSwitcher.setOutAnimation(out);
		imageSwitcher.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				  currentIndex++;
                  // If index reaches maximum reset it
                   if(currentIndex==messageCount)
                       currentIndex=0;
                   imageSwitcher.setImageResource(imageIds[currentIndex]);
				
			}
		});
		return customView;
	}

}
