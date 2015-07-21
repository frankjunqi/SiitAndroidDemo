package com.siit.broadcasedemo;

import static android.content.Intent.ACTION_AIRPLANE_MODE_CHANGED;
import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

public class BroadcastDemoActivity extends PicassoSampleActivity implements
		OnClickListener {

	private Button btn_register;
	private Button btn_unregister;
	private Button btn_sentregister;
	private NetworkBroadcastReceiver mNetworkBroadcastReceiver;

	private boolean isQIDONG = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast_layout);
		mNetworkBroadcastReceiver = new NetworkBroadcastReceiver(this);
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_unregister = (Button) findViewById(R.id.btn_unregister);
		btn_sentregister = (Button) findViewById(R.id.btn_sentregister);
		btn_register.setOnClickListener(this);
		btn_unregister.setOnClickListener(this);
		btn_sentregister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			mNetworkBroadcastReceiver.register();
			isQIDONG = true;
			btn_register.setClickable(false);
			break;
		case R.id.btn_unregister:
			if (isQIDONG) {
				isQIDONG = false;
				mNetworkBroadcastReceiver.unregister();
				btn_register.setClickable(true);
			}
			break;
		case R.id.btn_sentregister:
			sendBoradCastTest();
			break;
		}
	}

	private void sendBoradCastTest() {
		Intent mIntent = new Intent();
		mIntent.setAction("TestBroad");
		sendBroadcast(mIntent);
	}

	/**
	 * 11-21 16:04:59.650: E/ActivityThread(2157): Activity
	 * com.siit.broadcasedemo.BroadcastDemoActivity has leaked IntentReceiver
	 * com.siit.broadcasedemo.BroadcastDemoActivity$NetworkBroadcastReceiver@42f
	 * b48d8 that was originally registered here. Are you missing a call to
	 * unregisterReceiver()?
	 */
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		if (mNetworkBroadcastReceiver != null) {

		}
	}

	/**
	 * 广播的使用
	 * 
	 * 
	 */
	class NetworkBroadcastReceiver extends BroadcastReceiver {
		final String EXTRA_AIRPLANE_STATE = "state";
		private Context mContext;

		NetworkBroadcastReceiver(Context mContext) {
			this.mContext = mContext;
		}

		void register() {
			IntentFilter filter = new IntentFilter();
			filter.addAction(ACTION_AIRPLANE_MODE_CHANGED);
			filter.addAction(CONNECTIVITY_ACTION);
			filter.addAction("TestBroad");
			registerReceiver(this, filter);
		}

		void unregister() {
			unregisterReceiver(this);
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			// On some versions of Android this may be called with a null
			// Intent,
			// also without extras (getExtras() == null), in such case we use
			// defaults.
			if (intent == null) {
				return;
			}
			final String action = intent.getAction();
			if (ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
				if (!intent.hasExtra(EXTRA_AIRPLANE_STATE)) {
					return; // No airplane state, ignore it. Should we query
							// Utils.isAirplaneModeOn?
				}
				Toast.makeText(mContext, "ACTION_AIRPLANE_MODE_CHANGED",
						Toast.LENGTH_LONG).show();
			} else if (CONNECTIVITY_ACTION.equals(action)) {
				ConnectivityManager connectivityManager = (ConnectivityManager) mContext
						.getSystemService(CONNECTIVITY_SERVICE);
				connectivityManager.getActiveNetworkInfo();
				Toast.makeText(mContext, "CONNECTIVITY_ACTION",
						Toast.LENGTH_LONG).show();
			} else if ("TestBroad".equals(action)) {
				Toast.makeText(mContext, "TestBroad", Toast.LENGTH_LONG).show();
			}
		}
	}

}
