package com.siit.notificationAndtoastdemo;

import java.util.Random;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.gridviewdemo.SampleGridViewActivity;
import com.siit.picassodemo.R;
import com.siit.picassoutil.Picasso;
import com.siit.util.Data;

public class NotificationAndToastDemoActivity extends PicassoSampleActivity
		implements OnClickListener {
	private static final int NOTIFICATION_ID = 666;
	private Button btn_notification;
	private Button btn_default_notification;
	private Button btn_cancle_notification;
	private Button btn_toast;
	private Button btn_custom_toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_toast_demo);
		btn_notification = (Button) findViewById(R.id.btn_notification);
		btn_default_notification = (Button) findViewById(R.id.btn_default_notification);
		btn_cancle_notification = (Button) findViewById(R.id.btn_cancle_notification);
		btn_toast = (Button) findViewById(R.id.btn_toast);
		btn_custom_toast = (Button) findViewById(R.id.btn_custom_toast);
		btn_notification.setOnClickListener(this);
		btn_cancle_notification.setOnClickListener(this);
		btn_default_notification.setOnClickListener(this);
		btn_toast.setOnClickListener(this);
		btn_custom_toast.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_notification:
			showNotification();
			break;
		case R.id.btn_default_notification:
			showDefaultNotification();
			break;
		case R.id.btn_cancle_notification:
			removeNotification();
			break;
		case R.id.btn_toast:
			showToast();
		case R.id.btn_custom_toast:
			showCustomToast();
			break;

		}
	}

	/**
	 * ��ʾ�Զ���Notification
	 */
	private void showNotification() {
		RemoteViews remoteViews = new RemoteViews(getPackageName(),
				R.layout.notification_view);

		Intent intent = new Intent(this, SampleGridViewActivity.class);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this)
				.setSmallIcon(R.drawable.icon)
				.setContentIntent(
						PendingIntent.getActivity(this, -1, intent, 0))
				.setContent(remoteViews);

		Notification notification = builder.getNotification();
		// Bug in NotificationCompat that does not set the content.
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
			notification.contentView = remoteViews;
		}
		notification.flags = Notification.FLAG_NO_CLEAR;

		NotificationManager notificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, notification);

		// Now load an image for this notification.
		Picasso.with(this)
				//
				.load(Data.URLS[new Random().nextInt(Data.URLS.length)])
				//
				.resizeDimen(R.dimen.notification_icon_width_height,
						R.dimen.notification_icon_width_height) //
				.into(remoteViews, R.id.photo, NOTIFICATION_ID, notification);

	}

	/**
	 *  Ĭ����ʾ�ĵ�Notification
	 */
	private void showDefaultNotification() {
		// ����Notication�ĸ�������
		CharSequence title = "i am new";
		int icon = R.drawable.icon;
		long when = System.currentTimeMillis();
		Notification noti = new Notification(icon, title, when + 10000);
		noti.flags = Notification.FLAG_INSISTENT;

		// ����һ��֪ͨ
		Notification mNotification = new Notification();

		// ��������ֵ
		mNotification.icon = R.drawable.icon;
		mNotification.tickerText = "NotificationTest";
		mNotification.when = System.currentTimeMillis(); // ����������֪ͨ

		// �������Ĺ��캯��,����ֵ����
		// Notification mNotification = = new
		// Notification(R.drawable.icon,"NotificationTest",
		// System.currentTimeMillis()));

		// �������Ч��
		mNotification.defaults |= Notification.DEFAULT_SOUND;

		// �����,������֪��Ҫ�����Ȩ�� : Virbate Permission
		// mNotification.defaults |= Notification.DEFAULT_VIBRATE ;

		// ���״̬��־

		// FLAG_AUTO_CANCEL ��֪ͨ�ܱ�״̬���������ť�������
		// FLAG_NO_CLEAR ��֪ͨ�ܱ�״̬���������ť�������
		// FLAG_ONGOING_EVENT ֪ͨ��������������
		// FLAG_INSISTENT ֪ͨ������Ч��һֱ����
		mNotification.flags = Notification.FLAG_INSISTENT;

		// ����֪ͨ��ʾΪĬ��View
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent("android.settings.SETTINGS"), 0);
		mNotification.setLatestEventInfo(this, "֪ͨ���ͣ�Ĭ��View", "һ���Ӵ��������",
				contentIntent);

		// ����setLatestEventInfo����,��������û�App�����쳣
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// ע���֪ͨ
		// �����NOTIFICATION_ID��֪ͨ�Ѵ��ڣ�����ʾ����֪ͨ�������Ϣ ������tickerText ��
		mNotificationManager.notify(2, mNotification);

	}

	/**
	 * �Ƴ�����Cancle��Notification
	 */
	private void removeNotification() {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// ȡ����ֻ�ǵ�ǰContext��Notification
		mNotificationManager.cancel(2);
	}

	/**
	 * ��ʾToast
	 */
	private void showToast() {
		Toast.makeText(this, "show toast ...", Toast.LENGTH_LONG).show();
	}

	/**
	 * ��ʾ�Զ����Toast
	 */
	private void showCustomToast() {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.custom,
				(ViewGroup) findViewById(R.id.llToast));
		ImageView image = (ImageView) layout.findViewById(R.id.tvImageToast);
		image.setImageResource(R.drawable.icon);
		TextView title = (TextView) layout.findViewById(R.id.tvTitleToast);
		title.setText("Attention");
		TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
		text.setText("��ȫ�Զ���Toast");
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}

}
