/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.siit.appwidgetproviderdemo;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.siit.picassodemo.R;
import com.siit.picassoutil.Picasso;
import com.siit.util.Data;
import com.siit.util.GrayscaleTransformation;

public class SampleWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(final Context context,
			AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		RemoteViews updateViews = new RemoteViews(context.getPackageName(),
				R.layout.sample_widget);
		// Load image for all appWidgetIds.
		Picasso picasso = Picasso.with(context);
		picasso.load(Data.URLS[new Random().nextInt(Data.URLS.length)]) //
				// .placeholder(R.drawable.placeholder) //
				.error(R.drawable.error) //
				.transform(new GrayscaleTransformation(picasso)) //
				.into(updateViews, R.id.image, appWidgetIds);
		Toast.makeText(context, "clicked it", Toast.LENGTH_SHORT).show();
		Intent actClick = new Intent(
				"android.appwidget.action.APPWIDGET_UPDATE");
		PendingIntent pending = PendingIntent.getBroadcast(context, 0,
				actClick, 0);
		updateViews.setOnClickPendingIntent(R.id.image, pending);
	}

	/**
	 * 接收窗口小部件点击时发送的广播
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Log.i("WIGET", "onReceive : action = " + intent.getAction());
		// 这里判断是自己的action，做自己的事情，比如小工具被点击了要干啥，这里是做来一个动画效果
		if (intent.getAction().equals(
				"android.appwidget.action.APPWIDGET_UPDATE")) {
			Toast.makeText(context, "接收窗口小部件点击时发送的广播", Toast.LENGTH_SHORT)
					.show();
		}
	}

}
