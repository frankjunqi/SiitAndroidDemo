package com.siit.imageviewdemo;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;
import com.siit.picassodemo.R.drawable;

import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class ImageViewShowActivity extends PicassoSampleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * 方法一： 导入布局
		 */
		// setContentView(R.layout.activity_main);

		/**
		 * 方法二：代码写入
		 */
		ImageView imageView = new ImageView(this);

		// 设置ImageView的margin
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 20, 30, 40);
		imageView.setLayoutParams(lp);

		// 简单设置ImageView的高度和宽度
		// imageView.setLayoutParams(new LayoutParams(100, 100));

		// 设置ImageView自适应
		// imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));

		// 设置ImageView的image
		imageView.setImageResource(R.drawable.ic_launcher);
		// imageView.setBackgroundResource(R.drawable.ic_launcher);

		// 设置ImageView的Padding
		imageView.setPadding(100, 100, 10, 10);

		// 设置控件应该如何调整大小或移动的图像匹配这个ImageView的大小。
		// 注意：这里的ScaleType的是一个枚举，查看源代码；（大家去了解一下枚举的用处）
		imageView.setScaleType(ScaleType.FIT_CENTER);

		setContentView(imageView);

	}
}
