package com.siit.util;

import android.app.Application;

public class PicassoApplication extends Application {

	private static PicassoApplication instance;

	public static PicassoApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
}
