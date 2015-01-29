package com.siit.layoutdemo;

import android.os.Bundle;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * 表格布局和绝对布局
 * 
 * @author kjh08490
 * 
 */
public class TableLayoutActivity extends PicassoSampleActivity {
	/**
	 * TableRow是LinearLayout的子类，它的android:orientation属性值恒为horizontal，并且它的android
	 * :layout_width和android:layout_height属性值恒为MATCH_PARENT和WRAP_CONTENT。
	 * 所以它的子元素都是横向排列 ，并且宽高一致的。这样的设计使得每个TableRow里的子元素都相当于表格中的单元格一样。在TableRow中，
	 * 单元格可以为空，但是不能跨列。
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tablelayout);
	}
}
