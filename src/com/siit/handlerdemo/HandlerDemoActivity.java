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
 * �������֣��Ƿ�ʹ��handler��post����������������Ƿ������߳���ִ�д���ʹ��post����ʱ��ֱ�ӵ���Thread��Runnable��run����
 * �����д��������߳��н��У���û�п��������Thread��Runnable�µ��̣߳���
 * 
 * ������picasso���õ��˵����ַ�ʽ�����߳�֮��Ľ����ġ��磺�ڴ���վ�������ʵ�ֵġ�
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
	 * ���߳��з�����Ϣ�������̣�
	 */
	final HandlerTest myHandlerOne = new HandlerTest();

	private void testOne() {
		Thread sender = new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 1;
				msg.obj = "���߳��з�����Ϣ��������";
				myHandlerOne.sendMessage(msg);
				// myHandler.sendEmptyMessage( inWhat );
			}
		};
		sender.start();
	}

	/**
	 * �����߳��з���Ϣ��handler
	 */
	Handler myHandler;
	Runnable runnable;

	private void testTwo() {
		myHandler = new Handler(this.getMainLooper(), new Handler.Callback() {
			// ����Ҳ����Ϊ��this.getMainLooper()��new
			// Callback(){}����д��Ĭ��Ϊ�����̵�Looper
			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		runnable = new Runnable() {

			@Override
			public void run() {
				Toast.makeText(HandlerDemoActivity.this, "�����߳��з���Ϣ��handler",
						Toast.LENGTH_LONG).show();
			}

		};
		myHandler.post(runnable);

	}

	/**
	 * HandlerThread�̳�Thread��
	 * HandlerThread���Լ�����Ϣ����(Looper)��һ��HandlerThread��Handler�����ʹ�ã�
	 * Handler����Ϣ����HandlerThread���Լ�����Ϣ���У�
	 * Handler������Ϣ������handlethread�ǲ���Ӱ��UiThread��
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
						// ����̲߳���UIThread�����Բ����Ը���UI;������Ҫ��UIThread������Ϣ��UIThread����Ϣ���н��д���
						Message msgOutToUIThread = new Message();
						msgOutToUIThread.what = 2;
						msgOutToUIThread.obj = "HandlerThread��UIThread�߳��н��з�����Ϣ";
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
	 * Ĭ��Ϊ�����̵�Looper����
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
