package com.siit.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;
import com.siit.servicedemo.LocalService.LocalBinder;

public class ServiceDemoActivity extends PicassoSampleActivity implements
		OnClickListener {
	Button buttonOn, buttonOff;
	Button buttonOn2, buttonOff2;
	Button button_on3, button_local, button_local_off, button_message,button_message_action,
			button_message_off;
	String TAG = "ServiceActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_layout);

		buttonOn = (Button) findViewById(R.id.button_on);
		buttonOff = (Button) findViewById(R.id.button_off);
		button_on3 = (Button) findViewById(R.id.button_on3);
		button_local = (Button) findViewById(R.id.button_local);
		button_local_off = (Button) findViewById(R.id.button_local_off);
		button_message = (Button) findViewById(R.id.button_message);
		button_message_off = (Button) findViewById(R.id.button_message_off);
		button_message_action = (Button) findViewById(R.id.button_message_action);

		buttonOn.setOnClickListener(this);
		buttonOff.setOnClickListener(this);
		buttonOn2 = (Button) findViewById(R.id.button_on2);
		buttonOff2 = (Button) findViewById(R.id.button_off2);

		buttonOn2.setOnClickListener(this);
		buttonOff2.setOnClickListener(this);
		button_on3.setOnClickListener(this);
		button_local.setOnClickListener(this);
		button_local_off.setOnClickListener(this);
		button_message.setOnClickListener(this);
		button_message_off.setOnClickListener(this);
		button_message_action.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.button_on):
			Log.v(TAG, "ActivitystartService");
			startService(new Intent(this, PlayService.class));
			break;
		case (R.id.button_off):
			Log.v(TAG, "ActivitystopService");
			stopService(new Intent(this, PlayService.class));
			break;
		case (R.id.button_on2):
			startService2();
			break;
		case (R.id.button_off2):
			stopService2();
			break;
		case (R.id.button_on3):
			/**
			 * ==========================================================IntentService================================================================
			 */
			Intent service = new Intent(this, IntentSer.class);
			startService(service);
			break;
		case (R.id.button_local):
			localBind();
			break;
		case (R.id.button_local_off):
			localUnbind();
			break;
		case (R.id.button_message):
			messageBind();
			break;
		case (R.id.button_message_off):
			messageUnBind();
		case (R.id.button_message_action):
			sayHello();
			break;
		}
	}

	/**
	 * ==========================================================HelloService==========================================================================
	 * The startService() method returns immediately and the Android system
	 * calls the service's onStartCommand() method. If the service is not
	 * already running, the system first calls onCreate(), then calls
	 * onStartCommand().
	 */
	private void startService2() {
		Intent intent = new Intent(this, HelloService.class);
		startService(intent);
	}

	/**
	 * Multiple requests to start the service result in multiple corresponding
	 * calls to the service's onStartCommand(). However, only one request to
	 * stop the service (with stopSelf() or stopService()) is required to stop
	 * it.
	 */
	private void stopService2() {
		Intent intent = new Intent(this, HelloService.class);
		stopService(intent);
	}

	/**
	 * ==========================================================LocalBindService==========================================================================
	 * LocalBindService
	 */
	LocalService mService;
	boolean mBound = false;

	/** Defines callbacks for service binding, passed to bindService() */
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// We've bound to LocalService, cast the IBinder and get
			// LocalService instance
			LocalBinder binder = (LocalBinder) service;
			mService = binder.getService();
			Toast.makeText(ServiceDemoActivity.this,
					"" + mService.getRandomNumber(), Toast.LENGTH_LONG).show();
			mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Toast.makeText(ServiceDemoActivity.this, "ddd", Toast.LENGTH_LONG)
					.show();
			mBound = false;
		}
	};

	private void localBind() {
		Intent intent = new Intent(this, LocalService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	private void localUnbind() {
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}

	/**
	 * ==========================================================MessageService===============================================================================
	 * MessageService:
	 * 通过message桥梁以及IBind进行通信；
	 * IncomingHandler中的Thread的sleep操作可以看出，默认情况下这个service是在主线程中；
	 */
	/** Messenger for communicating with the service. */
	Messenger mmService = null;

	/** Flag indicating whether we have called bind on the service. */
	boolean mmBound;

	/**
	 * Class for interacting with the main interface of the service.
	 */
	private ServiceConnection mmConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			// This is called when the connection with the service has been
			// established, giving us the object we can use to
			// interact with the service. We are communicating with the
			// service using a Messenger, so here we get a client-side
			// representation of that from the raw IBinder object.
			mmService = new Messenger(service);
			mmBound = true;
		}

		public void onServiceDisconnected(ComponentName className) {
			// This is called when the connection with the service has been
			// unexpectedly disconnected -- that is, its process crashed.
			mmService = null;
			mmBound = false;
		}
	};

	public void sayHello() {
		if (!mmBound)
			return;
		// Create and send a message to the service, using a supported 'what'
		// value
		Message msg = Message
				.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
		try {
			mmService.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void messageBind() {
		bindService(new Intent(this, MessengerService.class), mmConnection,
				Context.BIND_AUTO_CREATE);
	}

	private void messageUnBind() {
		if (mmBound) {
			unbindService(mmConnection);
			mmBound = false;
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v(TAG, "ActivityonStop");
	}

	@Override
	protected void onDestroy() {
		Log.v(TAG, "ActivityonDestroy");
		super.onDestroy();

	}
}
