package com.siit.okhttpdemo;

import java.io.IOException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.dialogdemo.LoadingDialog;
import com.siit.picassodemo.R;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class OkhttpDemoActivity extends PicassoSampleActivity implements
		OnClickListener {
	private static final String GETURL = "https://api.github.com/repos/square/okhttp/contributors";
	private Button btn_get;
	private Button btn_post;
	private Button btn_entityback;

	private TextView tv_backdate;

	private LoadingDialog loadingDialog;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (loadingDialog.isShowing()) {
				loadingDialog.dismiss();
			}
			switch (msg.what) {
			case 1:
				tv_backdate.setText(msg.obj.toString());
				break;
			case 2:
				tv_backdate.setText(msg.obj.toString());
				break;
			case 3:
				tv_backdate.setText(msg.obj.toString());
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.okhtttp_layout);
		loadingDialog = new LoadingDialog(this);
		loadingDialog.setLoadingText("数据加载中...");
		btn_get = (Button) findViewById(R.id.btn_get);
		btn_post = (Button) findViewById(R.id.btn_post);
		btn_entityback = (Button) findViewById(R.id.btn_entityback);
		tv_backdate = (TextView) findViewById(R.id.tv_backdate);
		tv_backdate.setText("......");

		btn_get.setOnClickListener(this);
		btn_post.setOnClickListener(this);
		btn_entityback.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		tv_backdate.setText("......");
		if (!loadingDialog.isShowing()) {
			loadingDialog.showdialog();
		}
		switch (v.getId()) {
		case R.id.btn_get:
			new Thread() {
				@Override
				public void run() {
					try {
						Message msg = new Message();
						msg.what = 1;
						msg.obj = testGetRequest(GETURL);
						mHandler.sendMessage(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			break;
		case R.id.btn_post:
			new Thread() {
				@Override
				public void run() {
					Message msg = new Message();
					msg.what = 2;
					msg.obj = testPostRequest();
					mHandler.sendMessage(msg);
				}
			}.start();
			break;
		case R.id.btn_entityback:
			new Thread() {
				@Override
				public void run() {
					Message msg = new Message();
					msg.what = 3;
					msg.obj = testOkHttpContributors();
					mHandler.sendMessage(msg);
				}
			}.start();

			break;
		}
	}

	/**
	 * Get请求
	 */
	private String testGetRequest(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	/**
	 * Post请求
	 */
	private String testPostRequest() {
		String json = bowlingJson("Jesse", "Jake");
		String response = "无数据";
		try {
			response = postRequest("http://www.roundsapp.com/post", json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	public static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");

	private String postRequest(String url, String json) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	private String bowlingJson(String player1, String player2) {
		return "{'winCondition':'HIGH_SCORE'," + "'name':'Bowling',"
				+ "'round':4," + "'lastSaved':1367702411696,"
				+ "'dateStarted':1367702378785," + "'players':[" + "{'name':'"
				+ player1
				+ "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
				+ "{'name':'" + player2
				+ "','history':[6,10,5,10,10],'color':-48060,'total':41}"
				+ "]}";
	}

	/**
	 * 测试OkHttpContributors
	 * 
	 * @param tv
	 */
	private String testOkHttpContributors() {
		OkHttpContributors okHttpContributors = new OkHttpContributors();
		try {
			return okHttpContributors.excuteHttpContribtors();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "无结果";
	}

}
