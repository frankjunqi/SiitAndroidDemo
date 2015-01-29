package com.siit.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.siit.picassodemo.R;

public class PlayService extends Service {
	String TAG = "ServiceActivity";
	MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "Play Service Created", Toast.LENGTH_LONG).show();
		Log.v(TAG, "ServiceonCreate");
		mediaPlayer = MediaPlayer.create(this, R.raw.abc);
		/*
		 * 要用MediaPlayer来创建，不能用MediaPlayer的对象来创建 // 不用带后缀 mediaPlayer = new
		 * MediaPlayer(); mediaPlayer.create(this, R.raw.test);
		 */

		/*
		 * try { mediaPlayer.setDataSource("/sdcard/music/lost times.mp3");
		 * mediaPlayer.prepare();
		 * 
		 * 
		 * //方法二，从网上的链接获取歌曲 try { mediaPlayer.setDataSource(
		 * "http://www.yousss.com/uploadfile/mp3/2007-11/20071129134414713.mp3"
		 * );
		 */

		// mediaPlayer.setLooping(true);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		/*
		 * //可以在OnCreate里面创建与音乐的链接，也可以在OnStart里面创建 mediaPlayer =
		 * MediaPlayer.create(this, R.raw.test);
		 */
		Toast.makeText(this, "Play Service onStart", Toast.LENGTH_LONG).show();
		Log.v(TAG, "ServiceonStart");
		mediaPlayer.start();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "Play Service Stopped", Toast.LENGTH_LONG).show();
		Log.v(TAG, "ServiconDestroy");
		mediaPlayer.stop();
	}

}