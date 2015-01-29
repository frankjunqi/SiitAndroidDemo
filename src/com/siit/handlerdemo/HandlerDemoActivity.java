package com.siit.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * 以上两种，是否使用handler的post来启动，差别在与是否开启新线程来执行处理。使用post方法时，直接调用Thread或Runnable的run方法
 * ，所有处理都在主线程中进行，并没有开启定义的Thread或Runnable新的线程！！
 * 
 * 这里在picasso中用到了第三种方式进行线程之间的交互的。如：内存快照就是这样实现的。
 * 
 * @author kjh08490
 * 
 */
public class HandlerDemoActivity extends PicassoSampleActivity implements
		OnClickListener {

	private Button btn_one;
	private Button btn_two;
	private Button btn_three;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handledemo_layout);
		btn_one = (Button) findViewById(R.id.btn_one);
		btn_two = (Button) findViewById(R.id.btn_two);
		btn_three = (Button) findViewById(R.id.btn_three);
		btn_one.setOnClickListener(this);
		btn_two.setOnClickListener(this);
		btn_three.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_one:
			testOne();
			break;
		case R.id.btn_two:
			testTwo();
			break;
		case R.id.btn_three:
			testThree();
			break;
		}
	}

	/**
	 * 在线程中发送信息到主进程：
	 */
	final HandlerTest myHandlerOne = new HandlerTest();

	private void testOne() {
		Thread sender = new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 1;
				msg.obj = "在线程中发送信息到主进程";
				myHandlerOne.sendMessage(msg);
				// myHandler.sendEmptyMessage( inWhat );
			}
		};
		sender.start();
	}

	/**
	 * 在主线程中发信息到handler
	 */
	Handler myHandler;
	Runnable runnable;

	private void testTwo() {
		myHandler = new Handler(this.getMainLooper(), new Handler.Callback() {
			// 参数也可以为（this.getMainLooper()，new
			// Callback(){}）不写则默认为主进程的Looper
			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		runnable = new Runnable() {

			@Override
			public void run() {
				Toast.makeText(HandlerDemoActivity.this, "在主线程中发信息到handler",
						Toast.LENGTH_LONG).show();
			}

		};
		myHandler.post(runnable);

	}

	/**
	 * HandlerThread继承Thread，
	 * HandlerThread有自己的消息队列(Looper)，一般HandlerThread和Handler类配合使用，
	 * Handler将消息发往HandlerThread的自己的消息队列，
	 * Handler处理消息。这中handlethread是不会影响UiThread。
	 */
	private void testThree() {
		HandlerThread uIhandlerThread = new HandlerThread("HandlerThread Frank");
		uIhandlerThread.start();
		// Handler UIhandler = new Handler(uIhandlerThread.getLooper());
		Handler uIhandler = new Handler(uIhandlerThread.getLooper(),
				new Handler.Callback() {
					public boolean handleMessage(Message msg) {
						Bundle b = msg.getData();
						int age = b.getInt("age");
						String name = b.getString("name");
						Toast.makeText(
								HandlerDemoActivity.this,
								"Handler current thread--->"
										+ Thread.currentThread().getName()
										+ "\nage is " + age + ", name is"
										+ name, Toast.LENGTH_LONG).show();
						// Note:
						// 这个线程不是UIThread，所以不可以跟新UI;所以需要向UIThread发送消息让UIThread的消息队列进行处理
						Message msgOutToUIThread = new Message();
						msgOutToUIThread.what = 2;
						msgOutToUIThread.obj = "HandlerThread向UIThread线程中进行发送消息";
						myHandlerOne.sendMessage(msgOutToUIThread);
						return true;
					}
				});
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putString("name", "Frank");
		bundle.putInt("age", 25);
		msg.setData(bundle);
		uIhandler.sendMessage(msg);
	}

	/**
	 * 默认为主进程的Looper队列
	 * 
	 * @author kjh08490
	 * 
	 */
	class HandlerTest extends Handler {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				Toast.makeText(HandlerDemoActivity.this, msg.obj.toString(),
						Toast.LENGTH_LONG).show();
				break;
			case 2:
				Toast.makeText(HandlerDemoActivity.this, msg.obj.toString(),
						Toast.LENGTH_LONG).show();
				break;

			}
		}

	}

}
