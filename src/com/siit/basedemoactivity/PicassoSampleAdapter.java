package com.siit.basedemoactivity;

import java.util.Random;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.siit.activitylifecycle.ActivityA;
import com.siit.asynctaskdemo.AsynctaskDemoActivity;
import com.siit.broadcasedemo.BroadcastDemoActivity;
import com.siit.contactsdemo.SampleContactsActivity;
import com.siit.dialogdemo.DialogDemoActivity;
import com.siit.fragmentdemo.MainActivity;
import com.siit.gallerydemo.SampleGalleryActivity;
import com.siit.gridviewdemo.SampleGridViewActivity;
import com.siit.handlerdemo.HandlerDemoActivity;
import com.siit.imageviewdemo.ImageViewShowActivity;
import com.siit.layoutdemo.LayoutDemoActivity;
import com.siit.listviewdemo.SampleListDetailActivity;
import com.siit.menudemo.MenuDemoActivity;
import com.siit.notificationAndtoastdemo.NotificationAndToastDemoActivity;
import com.siit.okhttpdemo.OkhttpDemoActivity;
import com.siit.picassodemo.R;
import com.siit.picassoutil.Picasso;
import com.siit.servicedemo.ServiceDemoActivity;
import com.siit.sharepreferencesdemo.SharedPreferencesDemoActivity;
import com.siit.util.Data;

final class PicassoSampleAdapter extends BaseAdapter {

	private static final int NOTIFICATION_ID = 666;

	enum Sample {
		IMAGE_VIEW("ImageView Demo", ImageViewShowActivity.class), LAYOUT(
				"Layout Demo", LayoutDemoActivity.class), DIALOG("Dialog Demo",
				DialogDemoActivity.class), MENU("Menu Demo",
				MenuDemoActivity.class), NOTIFICATIONANDTOST(
				"Notification&Toast Demo",
				NotificationAndToastDemoActivity.class), SHAREDPREFERENCES(
				"SharedPreferences Demo", SharedPreferencesDemoActivity.class), ACTIVITYLIFECYCLE(
				"Activity Lifecycle Demo", ActivityA.class), FRAGEMENT(
				"Fragment Demo", MainActivity.class), HANDLER("Handler Demo",
				HandlerDemoActivity.class), BROADCAST("Broadcast Demo",
				BroadcastDemoActivity.class), SERVICE("Service Demo",
				ServiceDemoActivity.class), OKHTTP("Okhttp Demo",
				OkhttpDemoActivity.class), ASYNCTASK("Asynctask Demo",
				AsynctaskDemoActivity.class), LIST_DETAIL("ListView Demo",
				SampleListDetailActivity.class), GRID_VIEW("GridView Demo",
				SampleGridViewActivity.class), GALLERY(
				"Load from Gallery Demo", SampleGalleryActivity.class), CONTACTS(
				"Contacts Photos Demo", SampleContactsActivity.class), SHOW_NOTIFICATION(
				"Notification Demo", null) {
			@Override
			public void launch(Activity activity) {
				RemoteViews remoteViews = new RemoteViews(
						activity.getPackageName(), R.layout.notification_view);

				Intent intent = new Intent(activity,
						SampleGridViewActivity.class);

				NotificationCompat.Builder builder = new NotificationCompat.Builder(
						activity)
						.setSmallIcon(R.drawable.icon)
						.setContentIntent(
								PendingIntent.getActivity(activity, -1, intent,
										0)).setContent(remoteViews);

				Notification notification = builder.getNotification();
				// Bug in NotificationCompat that does not set the content.
				if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
					notification.contentView = remoteViews;
				}

				NotificationManager notificationManager = (NotificationManager) activity
						.getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.notify(NOTIFICATION_ID, notification);

				// Now load an image for this notification.
				Picasso.with(activity)
						//
						.load(Data.URLS[new Random().nextInt(Data.URLS.length)]) //
						.resizeDimen(R.dimen.notification_icon_width_height,
								R.dimen.notification_icon_width_height) //
						.into(remoteViews, R.id.photo, NOTIFICATION_ID,
								notification);
			}
		};

		private final Class<? extends Activity> activityClass;
		private final String name;

		Sample(String name, Class<? extends Activity> activityClass) {
			this.activityClass = activityClass;
			this.name = name;
		}

		public void launch(Activity activity) {
			activity.startActivity(new Intent(activity, activityClass));
			activity.finish();
		}
	}

	private final LayoutInflater inflater;

	public PicassoSampleAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return Sample.values().length;
	}

	@Override
	public Sample getItem(int position) {
		return Sample.values()[position];

	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) convertView;
		if (view == null) {
			view = (TextView) inflater.inflate(
					R.layout.picasso_sample_activity_item, parent, false);
		}

		view.setText(getItem(position).name);

		return view;
	}
}
