package com.siit.layoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * ²¼¾Ö½²½â
 * 
 * @author kjh08490
 * 
 */
public class LayoutDemoActivity extends PicassoSampleActivity implements
		OnClickListener {

	private Button btn_xian;
	private Button btn_xiang;
	private Button btn_ceng;
	private Button btn_jue;
	private Button btn_biao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_demo_activity);
		btn_xian = (Button) findViewById(R.id.btn_xian);
		btn_xiang = (Button) findViewById(R.id.btn_xiang);
		btn_ceng = (Button) findViewById(R.id.btn_ceng);
		btn_jue = (Button) findViewById(R.id.btn_jue);
		btn_biao = (Button) findViewById(R.id.btn_biao);
		btn_xian.setOnClickListener(this);
		btn_xiang.setOnClickListener(this);
		btn_ceng.setOnClickListener(this);
		btn_jue.setOnClickListener(this);
		btn_biao.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btn_xian:
			intent.setClass(this, LinearLayoutActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_xiang:
			intent.setClass(this, RelativeLayoutActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_ceng:
			intent.setClass(this, FrameLayoutActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_jue:
			intent.setClass(this, AbsoluteLayoutActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_biao:
			intent.setClass(this, TableLayoutActivity.class);
			startActivity(intent);
			break;
		}
	}
}
