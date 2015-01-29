package com.siit.sharepreferencesdemo;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

public class SharedPreferencesDemoActivity extends PicassoSampleActivity
		implements OnClickListener {

	private Button btn_submit;
	private Button btn_show;
	private TextView tv_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences_demo);
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_show = (Button) findViewById(R.id.btn_show);
		tv_show = (TextView) findViewById(R.id.tv_show);
		btn_submit.setOnClickListener(this);
		btn_show.setOnClickListener(this);
	}

	private void testSharedPreference() {
		// 用来保存参数的接口
		SharedPreferences sharedPreference;
		// 初始化参数配置
		sharedPreference = this.getSharedPreferences("spconfig", MODE_PRIVATE);
		if (sharedPreference.getBoolean("issetup", false)) {
			tv_show.setText(sharedPreference.getString("name", ""));
		}

		// 定义用来保存参数的接口
		Editor edit = sharedPreference.edit();
		edit.putString("name", "test sharedpreference");
		edit.putBoolean("issetup", true);
		// 清空编辑器
		// edit.clear();
		// 当有两个编辑器(Editor)进行编辑同一个sharedPreference时,最后一个提交的将会生效
		edit.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit:
			dateSubmit();
			break;
		case R.id.btn_show:
			getDate();
			break;
		}
	}

	private void dateSubmit() {
		SharedPreferencesUtils.getInstance().putString(
				SharedPreferencesKeys.LOGINNAME, "SIIT");
		SharedPreferencesUtils.getInstance().putString(
				SharedPreferencesKeys.USERNAME, "FRANK");
		SharedPreferencesUtils.getInstance().putString(
				SharedPreferencesKeys.MOBILE, "18550195586");
		SharedPreferencesUtils.getInstance().putString(
				SharedPreferencesKeys.QQ, "794545236");
	}

	private void getDate() {
		String loginName = SharedPreferencesUtils.getInstance().getString(
				SharedPreferencesKeys.LOGINNAME, "文件中为null");
		String userName = SharedPreferencesUtils.getInstance().getString(
				SharedPreferencesKeys.USERNAME, "文件中为null");
		String mobile = SharedPreferencesUtils.getInstance().getString(
				SharedPreferencesKeys.MOBILE, "文件中为null");
		String qq = SharedPreferencesUtils.getInstance().getString(
				SharedPreferencesKeys.QQ, "文件中为null");
		tv_show.setText("登录名:" + loginName + "\n昵称:" + userName + "\n手机号码:"
				+ mobile + "\nqq:" + qq);
		;
	}

}
