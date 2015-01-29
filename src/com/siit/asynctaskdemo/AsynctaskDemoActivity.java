package com.siit.asynctaskdemo;

import java.io.IOException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.dialogdemo.LoadingDialog;
import com.siit.picassodemo.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class AsynctaskDemoActivity extends PicassoSampleActivity {
	private Button button;
	private ProgressBar progressBar;
	private TextView textView;

	private Button btn_request;
	private TextView tv_network;

	private LoadingDialog loadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask_demo);

		loadingDialog = new LoadingDialog(this);
		loadingDialog.setLoadingText("加载中...");
		button = (Button) findViewById(R.id.button03);
		btn_request = (Button) findViewById(R.id.btn_request);
		progressBar = (ProgressBar) findViewById(R.id.progressBar02);
		textView = (TextView) findViewById(R.id.textView01);
		tv_network = (TextView) findViewById(R.id.tv_network);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ProgressBarAsyncTask asyncTask = new ProgressBarAsyncTask(
						textView, progressBar);
				asyncTask.execute(1000);
			}
		});

		btn_request.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkAsyncTask networkAsynctask = new NetworkAsyncTask();
				networkAsynctask
						.execute("http://www.apache.org/licenses/LICENSE-2.0");
			}
		});
	}

	private String requestNetwork(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	class NetworkAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			// 在执行耗时任务之前调用
			loadingDialog.showdialog();
		}

		@Override
		protected String doInBackground(String... params) {
			// 耗时任务ing
			try {
				return requestNetwork(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "无结果";
		}

		@Override
		protected void onPostExecute(String result) {
			// 耗时任务结束后调用 
			loadingDialog.dismiss();
			tv_network.setText(result);
		}

	}

}
