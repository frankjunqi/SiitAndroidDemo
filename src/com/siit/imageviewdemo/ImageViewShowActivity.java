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
		 * ����һ�� ���벼��
		 */
		// setContentView(R.layout.activity_main);

		/**
		 * ������������д��
		 */
		ImageView imageView = new ImageView(this);

		// ����ImageView��margin
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 20, 30, 40);
		imageView.setLayoutParams(lp);

		// ������ImageView�ĸ߶ȺͿ��
		// imageView.setLayoutParams(new LayoutParams(100, 100));

		// ����ImageView����Ӧ
		// imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));

		// ����ImageView��image
		imageView.setImageResource(R.drawable.ic_launcher);
		// imageView.setBackgroundResource(R.drawable.ic_launcher);

		// ����ImageView��Padding
		imageView.setPadding(100, 100, 10, 10);

		// ���ÿؼ�Ӧ����ε�����С���ƶ���ͼ��ƥ�����ImageView�Ĵ�С��
		// ע�⣺�����ScaleType����һ��ö�٣��鿴Դ���룻�����ȥ�˽�һ��ö�ٵ��ô���
		imageView.setScaleType(ScaleType.FIT_CENTER);

		setContentView(imageView);

	}
}
