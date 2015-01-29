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
		// ������������Ľӿ�
		SharedPreferences sharedPreference;
		// ��ʼ����������
		sharedPreference = this.getSharedPreferences("spconfig", MODE_PRIVATE);
		if (sharedPreference.getBoolean("issetup", false)) {
			tv_show.setText(sharedPreference.getString("name", ""));
		}

		// ����������������Ľӿ�
		Editor edit = sharedPreference.edit();
		edit.putString("name", "test sharedpreference");
		edit.putBoolean("issetup", true);
		// ��ձ༭��
		// edit.clear();
		// ���������༭��(Editor)���б༭ͬһ��sharedPreferenceʱ,���һ���ύ�Ľ�����Ч
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
				SharedPreferencesKeys.LOGINNAME, "�ļ���Ϊnull");
		String userName = SharedPreferencesUtils.getInstance().getString(
				SharedPreferencesKeys.USERNAME, "�ļ���Ϊnull");
		String mobile = SharedPreferencesUtils.getInstance().getString(
				SharedPreferencesKeys.MOBILE, "�ļ���Ϊnull");
		String qq = SharedPreferencesUtils.getInstance().getString(
				SharedPreferencesKeys.QQ, "�ļ���Ϊnull");
		tv_show.setText("��¼��:" + loginName + "\n�ǳ�:" + userName + "\n�ֻ�����:"
				+ mobile + "\nqq:" + qq);
		;
	}

}
