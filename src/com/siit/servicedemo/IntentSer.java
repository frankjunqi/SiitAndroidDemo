package com.siit.servicedemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class IntentSer extends IntentService {
	private final static String TAG = "main";
	private String url_path = "http://ww2.sinaimg.cn/bmiddle/9dc6852bjw1e8gk397jt9j20c8085dg6.jpg";

	public IntentSer() {
		super("IntentSer");
	}

	@Override
	public void onCreate() {
		Log.e(TAG, "Service is Created");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.e(TAG, "Service is started");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "Service is Destroyed");
		super.onDestroy();
	}

	/**
	 * 耗时操作
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.e(TAG, "HandleIntent is execute");
		try {
			// 在设备应用目录下创建一个文件
			File file = new File(this.getFilesDir(), "weibo.jpg");
			FileOutputStream fos = new FileOutputStream(file);
			// 获取网络图片的输入流
			InputStream inputStream = new URL(url_path).openStream();
			// 把网络图片输入流写入文件的输出流中
			byte[] date = new byte[1024];
			int len = -1;
			while ((len = inputStream.read(date)) != -1) {
				fos.write(date, 0, len);
			}

			fos.close();
			inputStream.close();
			Log.e(TAG, "The file download is complete");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}