package com.siit.layoutdemo;

import android.os.Bundle;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * 绝对布局
 * 
 * @author kjh08490
 * 
 */
public class AbsoluteLayoutActivity extends PicassoSampleActivity {
	/**
	 * AbsoluteLayout是绝对位置布局。在此布局中的子元素的android:layout_x和android:layout_y属性将生效，
	 * 用于描述该子元素的坐标位置 。屏幕左上角为坐标原点（0,0），第一个0代表横坐标，向右移动此值增大，第二个0代表纵坐标，向下移动，此值增大。
	 * 在此布局中的子元素可以相互重叠 。在实际开发中，通常不采用此布局格式，因为它的界面代码过于刚性，以至于有可能不能很好的适配各种终端。
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_absolutelayout);
	}
}
