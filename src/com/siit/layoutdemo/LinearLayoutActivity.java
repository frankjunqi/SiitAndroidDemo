package com.siit.layoutdemo;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * 线性布局
 * 
 * @author kjh08490
 * 
 */
public class LinearLayoutActivity extends PicassoSampleActivity {

	/**
	 * android:layout_weight属性 
	 * android:orientation属性 
	 * android:layout_gravity属性
	 * android:gravity属性
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_linerlayout);
	}
}
