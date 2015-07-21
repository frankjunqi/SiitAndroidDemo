package com.siit.layoutdemo;

import android.os.Bundle;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * 相对布局
 * 
 * 
 */
public class RelativeLayoutActivity extends PicassoSampleActivity {

	/**
	 * android:layout_toLeftOf —— 组件位于引用组件的左方
		android:layout_toRightOf —— 组件位于引用组件的右方
		android:layout_above —— 组件位于引用组件的上方
		android:layout_below —— 组件位于引用组件的下方
		android:layout_alignParentLeft —— 组件是否对齐父组件的左端
		android:layout_alignParentRight —— 组件是否齐其父组件的右端
		android:layout_alignParentTop —— 组件是否对齐父组件的顶部
		android:layout_alignParentBottom —— 组件是否对齐父组件的底部
		android:layout_centerInParent —— 组件是否相对于父组件居中
		android:layout_centerHorizontal —— 组件是否横向居中
		android:layout_centerVertical —— 组件是否垂直居中
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_relativelayout);
	}
}
