package com.siit.layoutdemo;

import android.os.Bundle;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * 层布局
 * 
 * @author kjh08490
 * 
 */
public class FrameLayoutActivity extends PicassoSampleActivity {

	/**
	 * 在这个布局中，整个界面被当成一块空白备用区域，所有的子元素都不能被指定放置的位置，它们统统放于这块区域的左上角，
	 * 并且后面的子元素直接覆盖在前面的子元素之上，将前面的子元素部分和全部遮挡。
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_framelayout);
	}
}
