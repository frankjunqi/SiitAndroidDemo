package com.siit.util;

import com.siit.picassoutil.Picasso;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MyBaseActivity extends FragmentActivity {

	public ImageLoader mImageLoader;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		init();
	}

	// 初始化ImageLoader对象
	private void init() {
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Picasso.with(this).cancelTag(this);
	}

}
