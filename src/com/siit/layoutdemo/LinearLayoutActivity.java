package com.siit.layoutdemo;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * ���Բ���
 * 
 * @author kjh08490
 * 
 */
public class LinearLayoutActivity extends PicassoSampleActivity {

	/**
	 * android:layout_weight���� 
	 * android:orientation���� 
	 * android:layout_gravity����
	 * android:gravity����
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_linerlayout);
	}
}
